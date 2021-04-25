(ns com.github.christianalexa.aji.views
  (:require [com.github.christianalexa.aji.pages.page-layout :refer [PageLayout]]))

(defn main-panel []
  [:div {:id "pageLayout"}
   [PageLayout]])
