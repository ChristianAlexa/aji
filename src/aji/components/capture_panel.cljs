(ns aji.components.capture-panel
  (:require [re-frame.core :as rf]))

;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::num-white-captured-stones
 (fn [db _]
   (get-in db [:active-board :white-captured-stones])))

(rf/reg-sub
 ::num-black-captured-stones
 (fn [db _]
   (get-in db [:active-board :black-captured-stones])))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn CapturePanel
  []
  (let [white-captured-stones @(rf/subscribe [::num-white-captured-stones])
        black-captured-stones @(rf/subscribe [::num-black-captured-stones])]
    [:div.panel.is-info {:id "capturePanel"}
     [:p.panel-heading "Captures"]
     [:div.panel-block "White stones captured: " white-captured-stones]
     [:div.panel-block "Black stones captured: " black-captured-stones]]))