;; TODO put vec2d in this ns
;; (ns board.aji.utils.vectors) 
(ns board.utils)

(defn- vec2d
  "vec2d returns a 2d vector."
  [num-cols num-rows f]
  (mapv (fn [row-idx]
          (mapv (fn [col-idx] (f row-idx col-idx))
                (range (inc num-cols))))
        (range num-rows)))

(def letters-vec
  "letters-vec provides a lookup value for the int y when create-board is invoked."
  ["A" "B" "C" "D" "E" "F" "G" "H" "I" "J" "K" "L" "M" "N" "O" "P" "Q" "R" "S" "T" "U" "V" "W" "X" "Y" "Z"])

(defn create-board
  "create-bord returns a 2d vector with coord-names strings.

   The inner vectors represent rows. 
   A collection of rows makes the entire board.

   example:
   (create-board 3) 
   => 
   [[\"1-A\" \"1-B\" \"1-C\"] 
    [\"2-A\" \"2-B\" \"2-C\"] 
    [\"3-A\" \"3-B\" \"3-C\"]]"
  [board-size]
  (vec2d board-size board-size (fn [x y]
                                 (str (inc x) "-" (nth letters-vec y)))))
