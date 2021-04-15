(ns aji.components.GameSidebar.InfoPanel.MessagePanel.MessagePanel
  (:require [re-frame.core :as rf]
            [clojure.string :as str]))


;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::curr-move-history
 (fn [db [_ _]]
   (get-in db [:active-board :move-history])))

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

;; -----------------------------------------------------------------------------
;; VIEWS
(defn MessagePanel
  "MessagePanel displays messages to the player about the active game."
  []
  (let [move-history @(rf/subscribe [::curr-move-history])
        curr-turn-formatted @(rf/subscribe [::curr-turn-formatted])]
    [:div.panel.is-info {:id "messagePanel"}
     [:p.panel-heading "Messages"]
     [:ul
      (when (zero? (count move-history))
        [:li.panel-block "Play a move!"])
      [:li.panel-block curr-turn-formatted " to play!"]]]))
