(ns com.github.christianalexa.aji.components.game-sidebar
  (:require [com.github.christianalexa.aji.components.control-panel :refer [ControlPanel]]
            [com.github.christianalexa.aji.components.info-panel :refer [InfoPanel]]
            ;; [com.github.christianalexa.aji.components.debug-panel :refer [DebugPanel]]
            ))

(defn GameSidebar
  "GameSidebar is a container for game panel containers."
  []
  [:div {:id "gameSidebar"}
  ;;  (when cfg/debug?
  ;;    [DebugPanel])
   [:img.mb-2 {:src "img/aji-logo.png"}]
   [ControlPanel]
   [InfoPanel]])