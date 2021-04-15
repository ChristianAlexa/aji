(ns aji.components.GameSidebar.GameSidebar
  (:require [aji.components.GameSidebar.ControlPanel.ControlPanel :refer [ControlPanel]]
            [aji.components.GameSidebar.InfoPanel.InfoPanel :refer [InfoPanel]]))

(defn GameSidebar
  "GameSidebar is a container for game panel containers."
  []
  [:div {:id "gameSidebar"}
   [ControlPanel]
   [InfoPanel]])