(ns aji.pages.game-page
  (:require [aji.components.board :refer [Board]]
            [aji.components.game-sidebar :refer [GameSidebar]]))

(defn GamePage
  "GamePage contains all game components."
  []
  [:div.columns {:id "gamePage"}
   [:div.column.is-one-quarter
    [GameSidebar]]
   [:div.column
    [Board]]])