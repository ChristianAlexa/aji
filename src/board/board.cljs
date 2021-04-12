(ns board.board
  (:require [re-frame.core :as rf]
            [board.utils :as utils]
            [clojure.string :as str]))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
(rf/reg-event-db
 ::set-board-size
 (fn [db [_ board-size]]
   (-> db
       (assoc :board-size board-size)
       (assoc :active-board (:blank-board db))
       (assoc :curr-turn "TURN_BLACK"))))

(rf/reg-event-db
 ::play-move
 (fn [db [_id coord-name]]
   (let [curr-turn (:curr-turn db)
         next-turn (if (= "TURN_WHITE" curr-turn) "TURN_BLACK" "TURN_WHITE")
         stone-color (if (= "TURN_WHITE" curr-turn) "WHITE" "BLACK")
         curr-intersection (get-in db [:active-board coord-name])
         new-intersection (assoc curr-intersection :stone stone-color)]
     (-> db
         (assoc-in [:active-board coord-name] new-intersection)
         (assoc :curr-turn next-turn)))))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::board-size
 (fn [db [_ _]]
   (:board-size db)))

(rf/reg-sub
 ::intersection
 (fn [db [_ coord-name]]
   (get-in db [:active-board coord-name])))

(rf/reg-sub
 ::active-board
 :<- [::board-size]
 (fn [board-size [_ _]]
   (utils/create-board board-size)))

(rf/reg-sub
 ::curr-turn
 (fn [db [_ _]]
   (:curr-turn db)))

(rf/reg-sub
 ::curr-turn-formatted
 :<- [::curr-turn]
 (fn [curr-turn [_ _]]
   (if (str/includes? curr-turn "WHITE")
     "White"
     "Black")))

;; Set the incoming liberty count for a newly placed stone.
;; Example: 
;; You place stone (S) on a middle intersection which is surrounded by 3 neighboring stones.
;; Stone S has a liberty count of 1 because 4 minus 3.
(rf/reg-sub
 ::num-neighbors-with-stone
 (fn [db [_ coord-name]]
   (let [neighbors-coords  (vals (get-in db [:active-board coord-name :neighbors]))
         neighbors-vals (map #(get-in db [:active-board %]) neighbors-coords)
         neighbors-with-stones (map #(:stone %) neighbors-vals)]
     (count (filter identity neighbors-with-stones)))))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn Intersection
  "Intersection renders a single point, the smallest atomic unit on the board."
  [coord-name]
  (let [intersection @(rf/subscribe [::intersection coord-name])
        position (:position intersection)
        empty-img (case position
                    "POSITION_CORNER_TOP_LEFT" {:path "img/CORNER_INTERSECTION.png"}
                    "POSITION_CORNER_TOP_RIGHT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(90deg)"}
                    "POSITION_CORNER_BOTTOM_LEFT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(-90deg)"}
                    "POSITION_CORNER_BOTTOM_RIGHT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(180deg)"}
                    "POSITION_SIDE_TOP" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(0deg)"}
                    "POSITION_SIDE_BOTTOM" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(180deg)"}
                    "POSITION_SIDE_LEFT" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(-90deg)"}
                    "POSITION_SIDE_RIGHT" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(90deg)"}
                    "POSITION_MIDDLE" {:path "img/MIDDLE_INTERSECTION.png"}
                    {:path "img/MIDDLE_INTERSECTION.png"})
        black-stone-img "img/BLACK_STONE.png"
        white-stone-img "img/WHITE_STONE.png"]
    [:li
     {:style {:display "inline-block"}}
     [:img {:src (case (:stone intersection)
                   "WHITE" white-stone-img
                   "BLACK" black-stone-img
                   (:path empty-img))
            :style (when (nil? (:stone intersection)) {:transform (:rotate empty-img)})
            :on-click #(rf/dispatch [::play-move coord-name])}]]))

(defn Row
  "Row renders a row of intersections."
  [idx row]
  [:li {:key idx
        :style {:font-size 0}} (map (fn [coord-name] [Intersection coord-name]) row)])

(defn ButtonGroup
  "ButtonGroup renders a group of buttons to change board size."
  []
  [:<>
  ;;  [:button {:on-click #(rf/dispatch [::set-board-size 9])} "9x9"]
  ;;  [:button {:on-click #(rf/dispatch [::set-board-size 13])} "13x13"]
   [:button {:on-click #(rf/dispatch [::set-board-size 19])} "19x19"]])

(defn Board
  "Board renders the game board."
  []
  (let [board-size @(rf/subscribe [::board-size])
        new-board @(rf/subscribe [::active-board])
        curr-turn-formatted @(rf/subscribe [::curr-turn-formatted])
        num-top-corner-neighbors @(rf/subscribe [::num-neighbors-with-stone "1-A"])]
    [:div {:id "board"}
     [ButtonGroup]
     [:p "1-A neighbor count: " num-top-corner-neighbors]
     [:p "Board size: " board-size]
     [:p curr-turn-formatted " to play!"]
     [:ul {:id "allRows" :style {:listStyleType "none" :white-space "no wrap"}}
      (map-indexed Row new-board)]]))
