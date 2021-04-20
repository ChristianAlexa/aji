(ns aji.components.debug-panel
  (:require [re-frame.core :as rf]
            [aji.config :as cfg]))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS

;; Set the incoming liberty count for a newly placed stone.
;; Example:
;; You place stone (S) on a middle intersection which is surrounded by 3 neighboring stones.
;; Stone S has a liberty count of 1 because 4 minus 3.
(when cfg/debug?
  (rf/reg-sub
   ::num-neighbors-with-stone
   (fn [db [_ coord-name]]
     (let [neighbors-coords  (vals (get-in db [:active-board coord-name :neighbors]))
           neighbors-vals (map #(get-in db [:active-board %]) neighbors-coords)
           neighbors-with-stones (map #(:stone %) neighbors-vals)]
       (count (remove nil? neighbors-with-stones)))))

  (rf/reg-sub
   ::num-liberties-at-coord
   (fn [db [_ coord-name]]
     (-> db
         (get-in [:active-board coord-name])
         :liberties)))

  (rf/reg-sub
   ::stone
   (fn [db [_ coord-name]]
     (-> db
         (get-in [:active-board coord-name])
         :stone)))

  (rf/reg-sub
   ::selected-puzzle
   (fn [db [_ _]]
     (:selected-puzzle db))))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn DebugPanel
  []
  (let [num-top-corner-neighbors @(rf/subscribe [::num-neighbors-with-stone "1-A"])
        num-top-corner-liberties @(rf/subscribe [::num-liberties-at-coord "1-A"])
        stone @(rf/subscribe [::stone "1-A"])
        selected-puzzle @(rf/subscribe [::selected-puzzle])]
    [:div.has-background-warning
     [:p "DEV DEBUG DATA BELOW:"]
     [:p {:style {:color "red" :font-size "20px"}} "1-A neighbor count: " num-top-corner-neighbors]
     [:p {:style {:color "red" :font-size "20px"}} "1-A liberty count: " num-top-corner-liberties]
     [:p {:style {:color "red" :font-size "20px"}} "1-A stone: " stone]
     [:p {:style {:color "red" :font-size "20px"}} "Puzzle: " selected-puzzle]]))