(ns aji.components.modal)

(defn Modal
  []
  [:div.modal.is-active
   [:div.modal-background
    [:div.modal-content
     [:div.card
      [:div.card-content
       [:div.content "test modal"]]]]]
   [:button.modal-close.is-large {:aria-label "close"}]])