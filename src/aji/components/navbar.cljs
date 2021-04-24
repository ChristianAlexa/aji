(ns aji.components.navbar)

(defn Navbar
  "Navbar renders at the top of the page."
  []
  [:div.hero.is-primary.mb-4 {:id "navbar"}
   [:div.hero-body.nav {:style {:backgroundColor "#2D2D2D"}}
    [:img {:src "img/aji-logo.png"}]]])