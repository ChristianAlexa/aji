(ns aji.pages.page-layout
  (:require [aji.pages.game-page :refer [GamePage]]))

(defn PageLayout
  "PageLayout is the root page container."
  []
  [:div {:id "pageLayout"}
   [GamePage]])