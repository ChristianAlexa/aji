(ns aji.components.GameSidebar.InfoPanel.InfoPanel
  (:require
   [aji.components.GameSidebar.InfoPanel.MoveHistoryPanel.MoveHistoryPanel :refer [MoveHistoryPanel]]
   [aji.components.GameSidebar.InfoPanel.MessagePanel.MessagePanel :refer [MessagePanel]]))

(defn InfoPanel
  "InfoPanel renders game info."
  []
  [:div {:id "infoPanel"}
   [MessagePanel]
   [MoveHistoryPanel]])