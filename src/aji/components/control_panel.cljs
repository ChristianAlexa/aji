(ns aji.components.control-panel
  (:require [re-frame.core :as rf]
            [aji.components.dropdown :refer [Dropdown]]))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::dropdown-active?
 (fn [db [_ _]]
   (:dropdown-active? db)))

(rf/reg-sub
 ::puzzle-description
 (fn [db [_ _]]
   (:selected-puzzle db)))

;; -----------------------------------------------------------------------------
;; VIEWS

(defn ResetButton
  []
  [:button.button.is-small.ml-4
   {:on-click #(rf/dispatch [:board/seed-board "EMPTY"])
    :style {:fontSize "13px"
            :border 0
            :backgroundColor "orange"}} "Reset"])

(defn ButtonGroup
  "ButtonGroup renders a group of buttons to change board size."
  []
  (let [dropdown-active? @(rf/subscribe [::dropdown-active?])
        selected-puzzle-description @(rf/subscribe [:aji/selected-puzzle-description])
        puzzle-options @(rf/subscribe [:aji/all-puzzle-options])]
    [:div {:id "buttonGroup"}
     [Dropdown puzzle-options dropdown-active? selected-puzzle-description]
     [ResetButton]]))

(defn ControlPanel
  "ControlPanel renders panels that allows a player to interact with the active game."
  []

  [:div.panel {:id "controlPanel"}
   [:p.panel-heading "Board Controls"]
   [:ul
    [:li.panel-block
     [ButtonGroup]]]])