(ns aji.components.Navbar.Navbar)

(defn Navbar
  "Navbar renders at the top of the page."
  []
  [:div.hero.is-primary.has-text-centered {:id "navbar"}
   [:div.hero-body {:style {:backgroundColor "#202225"}}
    [:p.title {:style {:color "#FCC38C"}} "AJI"]
    [:p.subtitle {:style {:color "#A2FDA3"}} "Find your potential"]]])