(ns aji.views
  (:require [aji.pages.page-layout :refer [PageLayout]]))

(defn main-panel []
  [:div {:id "pageLayout"}
   [PageLayout]])
