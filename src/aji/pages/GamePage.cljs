(ns aji.pages.GamePage
  (:require [aji.components.Board.Board :refer [Board]]
            [aji.components.GameSidebar.GameSidebar :refer [GameSidebar]]))

(defn GamePage
  "GamePage contains all game components."
  []
  [:div.columns {:id "gamePage"}
   [:div.column.is-one-quarter
    [GameSidebar]]
   [:div.column
    [Board]]])