(ns com.github.christianalexa.aji.components.move-history-panel
  (:require [re-frame.core :as rf]))


;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::move-history
 (fn [db _]
   (get-in db [:active-board :move-history])))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn MoveHistoryDetail
  "MoveHistoryDetail renders information about a single move."
  [move]
  (let [move-num (first (keys move))
        move-val (first (vals move))]
    [:li.panel-block {:key move-num}
     "Move " move-num ": " (:color move-val) " played " (:coord-name move-val)]))

(defn MoveHistoryPanel
  "MoveHistoryPanel displays a list of the moves played in the active game."
  []
  (let [move-history @(rf/subscribe [::move-history])]
    [:div.panel {:id "moveHistoryPanel"}
     [:p.panel-heading "Move History"]
     [:div
      [:ul
       (when (empty? move-history) [:li.panel-block "No moves played."])
       (map MoveHistoryDetail move-history)]]]))