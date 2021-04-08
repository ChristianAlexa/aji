(ns aji.views
  (:require
   [re-frame.core :as re-frame]
   [aji.subs :as subs]
   [board.board :refer [Board]]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]
     [Board]]))

