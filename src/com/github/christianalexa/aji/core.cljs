(ns com.github.christianalexa.aji.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [com.github.christianalexa.aji.events :as events]
   [com.github.christianalexa.aji.views :as views]
   [com.github.christianalexa.aji.subs]
   [com.github.christianalexa.aji.config :as config]))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn init []
  (rf/dispatch-sync [::events/initialize-db])
  (rf/dispatch [:board/seed-board "EMPTY"])
  (dev-setup)
  (mount-root))
