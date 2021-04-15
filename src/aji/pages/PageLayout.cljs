(ns aji.pages.PageLayout
  (:require [aji.components.Navbar.Navbar :refer [Navbar]]
            [aji.pages.GamePage :refer [GamePage]]))

(defn PageLayout
  "PageLayout is the root page container."
  []
  [:<>
   [Navbar]
   [GamePage]])