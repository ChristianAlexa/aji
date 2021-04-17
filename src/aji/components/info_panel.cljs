(ns aji.components.info-panel
  (:require
   [aji.components.move-history-panel :refer [MoveHistoryPanel]]
   [aji.components.message-panel :refer [MessagePanel]]))

(defn InfoPanel
  "InfoPanel renders game info."
  []
  [:div {:id "infoPanel"}
   [MessagePanel]
   [MoveHistoryPanel]])