(ns aji.components.control-panel
  (:require [re-frame.core :as rf]
            [aji.components.modal :refer [Modal]]))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
(rf/reg-event-db
 ::show-modal
 (fn [db [_ _]]
   (assoc db :modal? true)))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::modal?
 (fn [db [_ _]]
   (:modal? db)))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn ButtonGroup
  "ButtonGroup renders a group of buttons to change board size."
  []
  (let [modal? @(rf/subscribe [::modal?])]
    [:div {:id "buttonGroup"}
    ;;  [Modal]
    ;;  (when modal? [Modal])
     [:button.button.is-danger.is-medium.mr-4
      {:on-click #(rf/dispatch [:board/seed-board "EMPTY"])} "Reset Board"]
     [:button.button.is-info.is-medium.mr-4
      {:on-click #(rf/dispatch [:board/seed-board "PUZZLE_PATTERN_1"])} "Puzzle 1"]]))

(defn ControlPanel
  "ControlPanel renders panels that allows a player to interact with the active game."
  []
  [:div.mb-4.mt-4 {:id "controlPanel"}
   [ButtonGroup]])