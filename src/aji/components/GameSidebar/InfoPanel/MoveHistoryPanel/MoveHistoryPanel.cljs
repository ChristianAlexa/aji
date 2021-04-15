(ns aji.components.GameSidebar.InfoPanel.MoveHistoryPanel.MoveHistoryPanel
  (:require [re-frame.core :as rf]))


;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::move-history
 (fn [db [_ _]]
   (get-in db [:active-board :move-history])))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn MoveHistoryDetail
  "MoveHistoryDetail renders information about a single move."
  [move]
  (let [move-num (first move)
        coord-name (:coord-name (second move))
        player-color (:color (second move))]
    [:li.panel-block {:key move-num}
     "Move " move-num ": " player-color " played " coord-name]))

(defn MoveHistoryPanel
  "MoveHistoryPanel displays a list of the moves played in the active game."
  []
  (let [move-history @(rf/subscribe [::move-history])]
    [:div.panel.is-info {:id "moveHistoryPanel"}
     [:p.panel-heading "Move History"]
     [:div
      [:ul
       (map MoveHistoryDetail move-history)]]]))