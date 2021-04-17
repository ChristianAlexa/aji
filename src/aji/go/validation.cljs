(ns aji.go.validation)

(defn empty-intersection?
  [db coord-name]
  (empty? (:stone (get-in db [:active-board coord-name]))))

(defn available-liberties?
  [db coord-name]
  (-> db
      (get-in [:active-board coord-name])
      :liberties
      pos?))

;; TODO friendly neightbor should check to see if the neighbors have available liberties
;; for themselves or if there friendly group has a positive liberty count.
(defn friendly-neighbor?
  [db coord-name]
  (let [coord-stone-color (if (= "TURN_WHITE" (:curr-turn db)) "WHITE" "BLACK")
        neighbors-coords  (vals (get-in db [:active-board coord-name :neighbors]))
        neighbors-vals (map #(get-in db [:active-board %]) neighbors-coords)
        neighbors-stone-vals (map #(:stone %) neighbors-vals)]
    (some #(= coord-stone-color %) (remove nil? neighbors-stone-vals))))

(defn legal-move?
  [db coord-name]
  (and (empty-intersection? db coord-name)
       (or (available-liberties? db coord-name) (friendly-neighbor? db coord-name))))
