(ns aji.pages.PageLayout
  (:require [aji.components.Navbar :refer [Navbar]]
            [aji.pages.GamePage :refer [GamePage]]))

(defn PageLayout
  []
  [:<>
   [Navbar]
   [GamePage]])