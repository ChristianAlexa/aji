(ns board.board
  (:require [re-frame.core :as rf]))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
;; (rf/reg-event-db
;;  ::init-board
;;  (fn db [_ _]))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::get-starting-board
 (fn [db [_ _]]
   (:board db)))

;; -----------------------------------------------------------------------------
;; VIEWS

(defn Intersection
  [coord]
  (let [last-intersection (second coord)]
    [:<>
     [:li  {:style {:border "1px solid black"
                    :backgroundColor "#F9D17C"
                    :height "100px"
                    :width "100px"
                    :text-align "center"
                    :display "inline-block"}} coord]
     (when (= "c" last-intersection)
       [:br])]))

(defn build-all-rows
  [initial-board]
  (let [row1 (into [] (filter #(= "1" (first %)) (keys initial-board)))
        row2 (into [] (filter #(= "2" (first %)) (keys initial-board)))
        row3 (into [] (filter #(= "3" (first %)) (keys initial-board)))
        all-rows []]
    (-> all-rows
        (conj  (into [] (sort (select-keys initial-board row1))))
        (conj  (into [] (sort (select-keys initial-board row2))))
        (conj  (into [] (sort (select-keys initial-board row3)))))))

(defn display-row
  [row]
  (map (fn [row] [Intersection (first row)]) row))

(defn Board
  "Board displays the game board."
  []
  (let [initial-board @(rf/subscribe [::get-starting-board])
        all-rows (build-all-rows initial-board)]
    [:div {:id "boardContainer"}
     [:h1 "Go board"]
     [:ul
      (map (fn [row] (display-row row)) all-rows)]]))
