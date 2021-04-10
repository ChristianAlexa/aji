(ns board.board
  (:require [re-frame.core :as rf]
            [clojure.string :as str]))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
(rf/reg-event-db
 ::set-board-size
 (fn [db [_ board-size]]
   (assoc db :board-size board-size)))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::get-starting-board
 (fn [db [_ _]]
   (:board db)))

(rf/reg-sub
 ::get-board-size
 (fn [db [_ _]]
   (:board-size db)))

(rf/reg-sub
 ::get-all-rows
 (fn [db [_ board-size]]
   (map (fn [row-num]
          (->> db
               :board
               keys
               (filter #(str/includes? % (str row-num "-")))
               (select-keys (:board db))
               (sort-by first)
               (take board-size)))
        (range 1 (inc board-size)))))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn Intersection
  "Intersection renders a single point, the smallest atomic unit on the board."
  [coord-name]
  [:li
   {:style {:border "1px solid black"
            :backgroundColor "#F9D17C"
            :height "25px"
            :width "25px"
            :text-align "center"
            :display "inline-block"}} coord-name])

(defn Row
  "Row renders a row of intersections."
  [row]
  [:li {:key (random-uuid)}
   (map (fn [r]
          (let [coord-name (first r)]
            [:<> {:key (random-uuid)}
             [Intersection coord-name]])) row)])

(defn ButtonGroup
  []
  [:<>
   [:button {:on-click #(rf/dispatch [::set-board-size 3])} "3x3"]
   [:button {:on-click #(rf/dispatch [::set-board-size 9])} "9x9"]
   [:button {:on-click #(rf/dispatch [::set-board-size 19])} "19x19"]])

(defn Board
  "Board renders the game board (i.e. all the rows of intersections)."
  []
  (let [board-size @(rf/subscribe [::get-board-size])
        all-rows @(rf/subscribe [::get-all-rows board-size])]
    [:div {:id "board"}
     [ButtonGroup]
     [:ul {:id "allRows" :style {:listStyleType "none"}}
      (map #(Row %) all-rows)]]))
