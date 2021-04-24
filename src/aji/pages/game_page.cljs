(ns aji.pages.game-page
  (:require [aji.components.board :refer [Board]]
            [aji.components.game-sidebar :refer [GameSidebar]]))

(defn GamePage
  "GamePage contains all game components."
  []
  [:div.columns {:id "gamePage"
                 :style {:margin "0 auto"}}
   [:div.column.is-one-quarter {:style {:maxWidth "625px" :minWidth "400px"}}
    [GameSidebar]]
   [:div.column {:style {:minWidth "625px" :maxWidth "625px"}}
    [Board]]])