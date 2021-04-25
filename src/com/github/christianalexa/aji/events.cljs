(ns com.github.christianalexa.aji.events
  (:require
   [re-frame.core :as re-frame]
   [com.github.christianalexa.aji.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
