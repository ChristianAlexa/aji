(ns aji.components.control-panel
  (:require [re-frame.core :as rf]
            [aji.components.dropdown :refer [Dropdown]]))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::dropdown-active?
 (fn [db [_ _]]
   (:dropdown-active? db)))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn ButtonGroup
  "ButtonGroup renders a group of buttons to change board size."
  []
  (let [dropdown-active? @(rf/subscribe [::dropdown-active?])]
    [:div {:id "buttonGroup"}
     [Dropdown ["PUZZLE_PATTERN_1" "PUZZLE_PATTERN_2"] dropdown-active?]
     [:button.button.is-danger.is-small.ml-4.mb-4
      {:on-click #(rf/dispatch [:board/seed-board "EMPTY"])} "Reset"]]))

(defn ControlPanel
  "ControlPanel renders panels that allows a player to interact with the active game."
  []
  [:div.mb-4.mt-4 {:id "controlPanel"}
   [ButtonGroup]])