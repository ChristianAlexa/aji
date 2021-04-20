(ns aji.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [aji.events :as events]
   [aji.views :as views]
   [aji.config :as config]))

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
