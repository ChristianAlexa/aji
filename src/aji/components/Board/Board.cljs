(ns aji.components.Board.Board
  (:require
   [re-frame.core :as rf]
   [aji.components.Board.utils :as utils]
   [aji.config :as cfg]))

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
         new-intersection (assoc curr-intersection :stone stone-color)
         neighbors-coords (vals (:neighbors curr-intersection))
         neighbors-vals (map #(get-in db [:active-board %]) neighbors-coords)
         neighbors-vals-dec-libs (map #(assoc % :liberties (dec (:liberties %))) neighbors-vals)
         curr-move-num (get-in db [:active-board :curr-move-num])
         last-move-played {(inc curr-move-num) {:coord-name coord-name :color stone-color}}
         curr-move-history (get-in db [:active-board :move-history])
         new-move-history (conj curr-move-history last-move-played)]
     (-> db
         (assoc-in [:active-board (nth neighbors-coords 0)] (nth neighbors-vals-dec-libs 0))
         (assoc-in [:active-board (nth neighbors-coords 1)] (nth neighbors-vals-dec-libs 1))
         (assoc-in [:active-board (nth neighbors-coords 2)] (nth neighbors-vals-dec-libs 2))
         (assoc-in [:active-board (nth neighbors-coords 3)] (nth neighbors-vals-dec-libs 3))
         (assoc-in [:active-board coord-name] new-intersection)
         (assoc-in [:active-board :curr-move-num] (inc curr-move-num))
         (assoc-in [:active-board :move-history] new-move-history)
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
     (count (remove nil? neighbors-with-stones)))))

(rf/reg-sub
 ::num-liberties-at-coord
 (when cfg/debug?
   (fn [db [_ coord-name]]
     (-> db
         (get-in [:active-board coord-name])
         :liberties))))

(defn position->img
  [position]
  (case position
    "POSITION_CORNER_TOP_LEFT" {:path "img/CORNER_INTERSECTION.png"}
    "POSITION_CORNER_TOP_RIGHT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(90deg)"}
    "POSITION_CORNER_BOTTOM_LEFT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(-90deg)"}
    "POSITION_CORNER_BOTTOM_RIGHT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(180deg)"}
    "POSITION_SIDE_TOP" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(0deg)"}
    "POSITION_SIDE_BOTTOM" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(180deg)"}
    "POSITION_SIDE_LEFT" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(-90deg)"}
    "POSITION_SIDE_RIGHT" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(90deg)"}
    "POSITION_MIDDLE" {:path "img/MIDDLE_INTERSECTION.png"}
    {:path "img/MIDDLE_INTERSECTION.png"}))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn Intersection
  "Intersection renders a single point, the smallest atomic unit on the board."
  [idx coord-name]
  (let [intersection @(rf/subscribe [::intersection coord-name])
        position (:position intersection)
        empty-img (position->img position)
        black-stone-img "img/BLACK_STONE.png"
        white-stone-img "img/WHITE_STONE.png"]
    [:div
     {:key coord-name
      :style {:display "inline-block"}}
     [:img {:src (cond
                   (and (= "WHITE" (:stone intersection)) (pos? (:liberties intersection))) white-stone-img
                   (and (= "BLACK" (:stone intersection)) (pos? (:liberties intersection))) black-stone-img
                   :else (:path empty-img))
            :style (when (nil? (:stone intersection)) {:transform (:rotate empty-img)})
            :on-click #(rf/dispatch [::play-move coord-name])}]]))

(defn Row
  "Row renders a row of intersections."
  [idx row]
  [:li {:key idx
        :style {:font-size 0}}
   (doall (map-indexed Intersection row))])

(defn Board
  "Board renders the game board."
  []
  (let [new-board @(rf/subscribe [::active-board])
        num-top-corner-neighbors @(rf/subscribe [::num-neighbors-with-stone "1-A"])
        oneA-liberties @(rf/subscribe [::num-liberties-at-coord "1-A"])]
    [:div.m-4 {:id "board"
               :style {:font-size "30px"}}
     [:ul {:id "allRows" :style {:listStyleType "none" :white-space "no wrap"}}
      (doall (map-indexed Row new-board))]
     [:p "DEV DEBUG DATA BELOW:"]
     [:p {:style {:color "red" :font-size "20px"}} "1-A neighbor count: " num-top-corner-neighbors]
     [:p {:style {:color "red" :font-size "20px"}} "1-A liberty count: " oneA-liberties]]))