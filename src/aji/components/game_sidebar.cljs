(ns aji.components.game-sidebar
  (:require [aji.components.control-panel :refer [ControlPanel]]
            [aji.components.info-panel :refer [InfoPanel]]
            ;; [aji.components.debug-panel :refer [DebugPanel]]
            ))

(defn GameSidebar
  "GameSidebar is a container for game panel containers."
  []
  [:div {:id "gameSidebar"}
  ;;  (when cfg/debug?
  ;;    [DebugPanel])
   [ControlPanel]
   [InfoPanel]])