(ns com.github.christianalexa.aji.components.message-panel
  (:require [re-frame.core :as rf]
            [clojure.string :as str]
            [com.github.christianalexa.aji.puzzles.patterns :as puzzles]))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::curr-move-history
 (fn [db _]
   (get-in db [:active-board :move-history])))

(rf/reg-sub
 ::curr-turn
 (fn [db _]
   (:curr-turn db)))

(rf/reg-sub
 ::curr-turn-formatted
 :<- [::curr-turn]
 (fn [curr-turn _]
   (if (str/includes? curr-turn "WHITE")
     "White"
     "Black")))

(rf/reg-sub
 ::winning-move
 (fn [db _]
   (:winning-move db)))

(rf/reg-sub
 ::puzzle-mode
 (fn [db _]
   (:puzzle-mode? db)))

(rf/reg-sub
 ::goal-message
 :<- [:aji/selected-puzzle]
 (fn [selected-puzzle _]
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
        puzzle-mode? @(rf/subscribe [::puzzle-mode])
        last-coord-played (:coord-name (first (vals (last move-history))))
        goal-message @(rf/subscribe [::goal-message])
        empty-board? (= "EMPTY" selected-puzzle)
        correct-move? (and (= winning-move last-coord-played) puzzle-mode?)
        incorrect-move? (and (not (= winning-move last-coord-played)) (not (nil? last-coord-played)) puzzle-mode?)]
    [:div.panel {:id "messagePanel"}
     [:p.panel-heading "Messages"]
     [:ul
      (cond
        empty-board? [:li.panel-block "Choose a puzzle!"]
        correct-move? [:li.panel-block {:style {:backgroundColor "#A2FDA3" :color "black" :fontWeight "bold"}} "Correct!"]
        incorrect-move? [:li.panel-block {:style {:backgroundColor "#ED6143" :color "black" :fontWeight "bold"}} "Incorrect!"]
        :else [:li.panel-block curr-turn-formatted " to play. " goal-message])]]))

