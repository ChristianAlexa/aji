(ns com.github.christianalexa.aji.components.dropdown
  (:require [re-frame.core :as rf]
            [com.github.christianalexa.aji.puzzles.patterns :as puzzles]))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
(rf/reg-event-db
 ::toggle-dropdown-active?
 (fn [db [_ _]]
   (update db :dropdown-active? not)))

(rf/reg-event-db
 ::turn-on-puzzle-mode
 (fn [db [_ _]]
   (assoc db :puzzle-mode? true)))

(rf/reg-event-db
 ::set-max-num-moves
 (fn [db [_ max-num-moves]]
   (assoc db :max-num-moves max-num-moves)))

(defn dropdown-item-handler
  [pattern-key]
  (let [pattern-val (get puzzles/patterns pattern-key)]
    (rf/dispatch [:board/seed-board pattern-key])
    (rf/dispatch [::toggle-dropdown-active?])
    (rf/dispatch [::turn-on-puzzle-mode])
    (rf/dispatch [::set-max-num-moves (:max-num-moves pattern-val)])))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn DropdownItem
  [idx puzzle-option]
  (let [pattern-key (:pattern-key puzzle-option)
        pattern-description (:pattern-description puzzle-option)]
    [:a.dropdown-item {:key idx
                       :href "#"
                       :on-click #(dropdown-item-handler pattern-key)} pattern-description]))
(defn Dropdown
  [options active? selected-option]
  [:div.dropdown {:class (when active? "is-active")}
   [:div.dropdown-trigger
    [:button.button {:aria-haspopup "true"
                     :aria-controls "dropdown-menu"
                     :on-click #(rf/dispatch [::toggle-dropdown-active?])}
     [:span {:style {:width "200px"}} (if (nil? selected-option)
                                        "Puzzles"
                                        selected-option)]
     [:span.icon.is-small
      [:i.fas.fa-angle-down {:aria-hidden "true"}]]]]
   [:div.dropdown-menu {:id "dropdown-menu" :role "menu"}
    [:div.dropdown-content
     (map-indexed DropdownItem options)]]])
