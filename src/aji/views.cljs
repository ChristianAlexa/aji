(ns aji.views
  (:require [aji.pages.PageLayout :refer [PageLayout]]))

(defn main-panel []
  [:div {:id "pageLayout"}
   [PageLayout]])
