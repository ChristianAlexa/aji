(ns aji.components.control-panel
  (:require [re-frame.core :as rf]))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
(rf/reg-event-db
 ::set-board-size
 (fn [db [_ board-size]]
   (-> db
       (assoc :board-size board-size)
       (assoc :active-board (:blank-board db))
       (assoc :curr-turn "TURN_BLACK"))))

(defn ButtonGroup
  "ButtonGroup renders a group of buttons to change board size."
  []
  [:div {:id "buttonGroup"}
   [:button.button.is-danger.is-medium
    {:on-click #(rf/dispatch [::set-board-size 19])} "New Game"]])

;; -----------------------------------------------------------------------------
;; VIEWS
(defn ControlPanel
  "ControlPanel renders panels that allows a player to interact with the active game."
  []
  [:div.mb-4.mt-4 {:id "controlPanel"}
   [ButtonGroup]])