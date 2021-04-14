(ns aji.components.Navbar)

(defn Navbar
  []
  [:div.hero.is-primary
   [:div.hero-body {:style {:backgroundColor "#202225"}}
    [:p.title {:style {:color "#FCC38C"}} "AJI"]
    [:p.subtitle {:style {:color "#A2FDA3"}} "go board"]]])