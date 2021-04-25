(ns com.github.christianalexa.aji.pages.page-layout
  (:require [com.github.christianalexa.aji.pages.game-page :refer [GamePage]]))

(defn PageLayout
  "PageLayout is the root page container."
  []
  [:div {:id "pageLayout"}
   [GamePage]])