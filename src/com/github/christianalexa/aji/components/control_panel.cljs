(ns com.github.christianalexa.aji.components.control-panel
  (:require [re-frame.core :as rf]
            [com.github.christianalexa.aji.components.dropdown :refer [Dropdown]]))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
(rf/reg-event-db
 ::turn-off-puzzle-mode
 (fn [db _]
   (assoc db :puzzle-mode? false)))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::dropdown-active?
 (fn [db _]
   (:dropdown-active? db)))

(rf/reg-sub
 ::puzzle-description
 (fn [db _]
   (:selected-puzzle db)))

(rf/reg-event-db
 ::reset-max-num-moves
 (fn [db _]
   (assoc db :max-num-moves nil)))

(defn reset-button-handler
  []
  (rf/dispatch [:board/seed-board "EMPTY"])
  (rf/dispatch [::turn-off-puzzle-mode])
  (rf/dispatch [::reset-max-num-moves]))

;; -----------------------------------------------------------------------------
;; VIEWS

(defn ResetButton
  []
  [:button.button.is-small.ml-4
   {:on-click reset-button-handler
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
