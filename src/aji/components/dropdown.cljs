(ns aji.components.dropdown
  (:require [re-frame.core :as rf]))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
(rf/reg-event-db
 ::toggle-dropdown-active?
 (fn [db [_ _]]
   (update db :dropdown-active? not)))

(defn dropdown-item-handler
  [pattern]
  (rf/dispatch [:board/seed-board pattern])
  (rf/dispatch [::toggle-dropdown-active?]))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn DropdownItem
  [idx pattern]
  [:a.dropdown-item {:key idx
                     :href "#"
                     :on-click #(dropdown-item-handler pattern)} pattern])
(defn Dropdown
  [items active?]
  [:div.dropdown {:class (when active? "is-active")}
   [:div.dropdown-trigger
    [:button.button {:aria-haspopup "true"
                     :aria-controls "dropdown-menu"
                     :on-click #(rf/dispatch [::toggle-dropdown-active?])}
     [:span "Puzzles"]
     [:span.icon.is-small
      [:i.fas.fa-angle-down {:aria-hidden "true"}]]]]
   [:div.dropdown-menu {:id "dropdown-menu" :role "menu"}
    [:div.dropdown-content
     (map-indexed DropdownItem items)]]])
