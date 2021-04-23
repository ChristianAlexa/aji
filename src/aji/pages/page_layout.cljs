(ns aji.pages.page-layout
  (:require [aji.components.navbar :refer [Navbar]]
            [aji.pages.game-page :refer [GamePage]]))

(defn PageLayout
  "PageLayout is the root page container."
  []
  [:div {:id "pageLayout"}
   [Navbar]
   [GamePage]])