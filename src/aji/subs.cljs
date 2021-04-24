(ns aji.subs
  (:require
   [re-frame.core :as rf]
   [aji.puzzles.patterns :as puzzles]))

(rf/reg-sub
 ::name
 (fn [db]
   (:name db)))

(rf/reg-sub
 :aji/selected-puzzle
 (fn [db _]
   (:selected-puzzle db)))

(rf/reg-sub
 :aji/selected-puzzle-val
 :<- [:aji/selected-puzzle]
 (fn [selected-puzzle-key _]
   (get puzzles/patterns selected-puzzle-key)))

(rf/reg-sub
 :aji/selected-puzzle-description
 :<- [:aji/selected-puzzle-val]
 (fn [selected-puzzle-val [_ _]]
   (:puzzle-description selected-puzzle-val)))

(rf/reg-sub
 :aji/puzzle-key->puzzle-val
 (fn [_ [_ puzzle-key]]
   (get puzzles/patterns puzzle-key)))

;; expected puzzle-options data shape
;;  [{:pattern-key "PUZZLE_PATTERN_1"
;;    :pattern-description "foo"}
;;   {:pattern-key "PUZZLE_PATTERN_2"
;;    :pattern-description "bar"}]
(rf/reg-sub
 :aji/all-puzzle-options
 (fn [_ _]
   (let [puzzle-pattern-keys (into [] (keys puzzles/patterns))
         puzzle-pattern-vals (map (fn [pattern-key]
                                    @(rf/subscribe [:aji/puzzle-key->puzzle-val pattern-key]))
                                  puzzle-pattern-keys)
         puzzle-pattern-descriptions (mapv #(:puzzle-description %) puzzle-pattern-vals)
         puzzle-pattern-options (zipmap puzzle-pattern-keys puzzle-pattern-descriptions)]

     ;; build a list of option-maps and cast to vector
     (into [] (map (fn [option]
                     (let [option-map {}]
                       (-> option-map
                           (assoc :pattern-key (first option))
                           (assoc :pattern-description (second option)))))
                   puzzle-pattern-options)))))
