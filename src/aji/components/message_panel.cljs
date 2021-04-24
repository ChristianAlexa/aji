(ns aji.components.message-panel
  (:require [re-frame.core :as rf]
            [clojure.string :as str]
            [aji.puzzles.patterns :as puzzles]))

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

(rf/reg-sub
 ::winning-move
 (fn [db [_ _]]
   (:winning-move db)))

(rf/reg-sub
 ::goal-message
 :<- [:aji/selected-puzzle]
 (fn [selected-puzzle [_ _]]
   (:goal-message (get puzzles/patterns selected-puzzle))))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn MessagePanel
  "MessagePanel displays messages to the player about the active game."
  []
  (let [selected-puzzle @(rf/subscribe [:aji/selected-puzzle])
        move-history @(rf/subscribe [::curr-move-history])
        curr-turn-formatted @(rf/subscribe [::curr-turn-formatted])
        winning-move @(rf/subscribe [::winning-move])
        last-coord-played (:coord-name (first (vals (last move-history))))
        goal-message @(rf/subscribe [::goal-message])]
    [:div.panel {:id "messagePanel"}
     [:p.panel-heading "Messages"]
     [:ul
      (if (= "EMPTY" selected-puzzle)
        [:li.panel-block "Choose a puzzle!"]
        (if (and (= winning-move last-coord-played) (not (nil? winning-move)))
          [:li.panel-block {:style {:backgroundColor "#A2FDA3" :color "black" :fontWeight "bold"}} "Correct!"]
          [:li.panel-block curr-turn-formatted " to play. " goal-message]))]]))
