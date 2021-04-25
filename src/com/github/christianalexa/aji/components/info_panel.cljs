(ns com.github.christianalexa.aji.components.info-panel
  (:require
   [com.github.christianalexa.aji.components.move-history-panel :refer [MoveHistoryPanel]]
   [com.github.christianalexa.aji.components.message-panel :refer [MessagePanel]]))

(defn InfoPanel
  "InfoPanel renders game info."
  []
  [:div {:id "infoPanel"}
   [MessagePanel]
   [MoveHistoryPanel]])