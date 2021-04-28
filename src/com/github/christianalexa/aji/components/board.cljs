(ns com.github.christianalexa.aji.components.board
  (:require
   [re-frame.core :as rf]
   [com.github.christianalexa.aji.go.validation :as validation]
   [clojure.string :as str]
   [com.github.christianalexa.aji.puzzles.patterns :as puzzles]))

;; -----------------------------------------------------------------------------
;; HELPERS
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
  "create-bord returns a 2d vector with coords strings.

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

(def MAX_BOARD_SIZE
  "MAX_BOARD_SIZE is for a 19x19 standard board."
  19)

(defn coord->row-num
  "1-A => 1"
  [coord]
  (js/parseInt (re-find #"\d+" coord) 10))

(defn coord->col-letter
  "1-A => A"
  [coord]
  (last coord))

(defn get-neighbor-coord
  "get-neighbor-coord returns the neighbor-coord string relative to the given coord intersection.
  
   Example: 
   (get-neighbord-coord \"EAST\" \"1-A\")
   => \"1-B\""
  [direction coord]
  (let [coord-num  (coord->row-num coord)
        coord-letter (coord->col-letter coord)]
    (case direction
      "NORTH" (when (pos? (dec coord-num))
                (str (dec coord-num) "-" coord-letter))
      "EAST" (when (< (str/index-of letters-vec coord-letter) MAX_BOARD_SIZE)
               (str coord-num "-" (nth letters-vec (inc (str/index-of letters-vec coord-letter)))))
      "SOUTH" (when (<= (inc coord-num) MAX_BOARD_SIZE)
                (str (inc coord-num) "-" coord-letter))
      "WEST" (when-not (neg? (dec (str/index-of letters-vec coord-letter)))
               (str coord-num "-" (nth letters-vec (dec (str/index-of letters-vec coord-letter))))))))

(defn get-neighbors-coords
  [coord]
  {:north (get-neighbor-coord "NORTH" coord)
   :east (get-neighbor-coord "EAST" coord)
   :south (get-neighbor-coord "SOUTH" coord)
   :west (get-neighbor-coord "WEST" coord)})

(defn initial-intersection-values
  "seed-active-board creates the key and values for all 19x19 coords along with the move history and curr move num in app-db.
   
   The return shape is a collection of rows:
   [ 
     [{} {} {}]      ;; a row of intersection values 
     [{} {} {}]
     [{} {} {}]
   ]"
  []
  (let [board-with-coords (create-board 19)]
    (mapv (fn [row]
            (mapv (fn [coord]
                    (cond

                    ;; CORNERS
                      (= coord "1-A") {:stone nil
                                       :liberties 2
                                       :position "POSITION_CORNER_TOP_LEFT"
                                       :neighbors (get-neighbors-coords coord)}
                      (= coord "1-T") {:stone nil :liberties 2 :position "POSITION_CORNER_TOP_RIGHT" :neighbors (get-neighbors-coords coord)}
                      (= coord "19-A") {:stone nil :liberties 2 :position "POSITION_CORNER_BOTTOM_LEFT" :neighbors (get-neighbors-coords coord)}
                      (= coord "19-T") {:stone nil :liberties 2 :position "POSITION_CORNER_BOTTOM_RIGHT" :neighbors (get-neighbors-coords coord)}

                    ;; TOP SIDE
                      (= 1 (coord->row-num coord)) {:stone nil :liberties 3 :position "POSITION_SIDE_TOP" :neighbors (get-neighbors-coords coord)}

                    ;; LEFT SIDE
                      (= "A" (last coord)) {:stone nil :liberties 3 :position "POSITION_SIDE_LEFT" :neighbors (get-neighbors-coords coord)}

                    ;; RIGHT SIDE
                      (= "T" (last coord)) {:stone nil :liberties 3 :position "POSITION_SIDE_RIGHT" :neighbors (get-neighbors-coords coord)}

                    ;; BOTTOM SIDE
                      (= 19 (js/parseInt (re-find #"\d+" coord))) {:stone nil :liberties 3 :position "POSITION_SIDE_BOTTOM" :neighbors (get-neighbors-coords coord)}

                    ;; MIDDLE
                      :else {:stone nil :liberties 4 :position "POSITION_MIDDLE" :neighbors (get-neighbors-coords coord)}))
                  row))
          board-with-coords)))

(defn initial-active-board
  "initial-active-board returns a map with the shape {\"1-A\" {:stone nil :liberties ... \"1-B\" {:stone nil :liberties ...}}}"
  [db puzzle-id]
  (let [initial-coord-vals-vec (into [] (flatten (initial-intersection-values)))
        initial-coord-keys-vec (into [] (flatten (create-board 19)))
        coords-and-vals-map (zipmap initial-coord-keys-vec initial-coord-vals-vec)
        puzzle (get puzzles/patterns puzzle-id)
        puzzle-pattern (:pattern puzzle)
        puzzle-color-to-move (:color-to-move puzzle)
        puzzle-winning-move (:winning-move puzzle)
        puzzle-curr-turn (if (= "EMPTY" puzzle-id)
                           "TURN_BLACK"
                           puzzle-color-to-move)
        active-board (if (= "EMPTY" puzzle-id)
                       coords-and-vals-map
                       (merge coords-and-vals-map puzzle-pattern))]
    (-> db
        (assoc-in [:active-board :curr-move-num] 0)
        (assoc-in [:active-board :move-history] {})
        (assoc-in [:active-board :white-captured-stones] 0)
        (assoc-in [:active-board :black-captured-stones] 0)
        (assoc :winning-move puzzle-winning-move)
        (assoc :selected-puzzle puzzle-id)
        (assoc :curr-turn puzzle-curr-turn)
        (assoc :active-board active-board))))

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
(rf/reg-event-db
 :board/seed-board
 (fn [db [_ puzzle-id]]
   (initial-active-board db puzzle-id)))

(rf/reg-event-db
 ::set-board-size
 (fn [db [_ board-size]]
   (-> db
       (assoc :board-size board-size)
       (assoc :active-board (:blank-board db))
       (assoc :curr-turn "TURN_BLACK"))))

(rf/reg-event-db
 ::play-move
 (fn [db [_id coord-name]]
   (let [curr-turn (:curr-turn db)
         next-turn (if (= "TURN_WHITE" curr-turn) "TURN_BLACK" "TURN_WHITE")
         stone-color (if (= "TURN_WHITE" curr-turn) "WHITE" "BLACK")
         curr-intersection (get-in db [:active-board coord-name])
         new-intersection (assoc curr-intersection :stone stone-color)
         neighbors-coords (vals (:neighbors curr-intersection))
         neighbors-vals (map #(get-in db [:active-board %]) neighbors-coords)
         neighbors-vals-with-decremented-libs (map (fn [neighbor]
                                                     (let [new-neighbor-liberties (dec (:liberties neighbor))
                                                           enemy-neighbor? (not= stone-color (:stone neighbor))]
                                                       (if (and (zero? new-neighbor-liberties) enemy-neighbor?)
                                                         (-> neighbor
                                                             (assoc :liberties new-neighbor-liberties)

                                                             ;; the stone has been captured
                                                             (assoc :stone nil))
                                                         (assoc neighbor :liberties new-neighbor-liberties))))
                                                   neighbors-vals)
         curr-move-num (get-in db [:active-board :curr-move-num])
         last-move-played {(inc curr-move-num) {:coord-name coord-name :color stone-color}}
         curr-move-history (get-in db [:active-board :move-history])
         new-move-history (conj curr-move-history last-move-played)
         legal-move? (validation/legal-move? db coord-name)
         puzzle-mode? (:puzzle-mode? db)
         moves-left-to-play? (<= (count new-move-history) (:max-num-moves db))]
     (when (and legal-move? puzzle-mode? moves-left-to-play?)
       (-> db
           (assoc-in [:active-board (nth neighbors-coords 0)] (nth neighbors-vals-with-decremented-libs 0)) ; north neighbor
           (assoc-in [:active-board (nth neighbors-coords 1)] (nth neighbors-vals-with-decremented-libs 1)) ; west neighbor
           (assoc-in [:active-board (nth neighbors-coords 2)] (nth neighbors-vals-with-decremented-libs 2)) ; south neighbor
           (assoc-in [:active-board (nth neighbors-coords 3)] (nth neighbors-vals-with-decremented-libs 3)) ; east neighbor
           (assoc-in [:active-board coord-name] new-intersection)
           (assoc-in [:active-board :curr-move-num] (inc curr-move-num))
           (assoc-in [:active-board :move-history] new-move-history)
           (assoc :curr-turn next-turn))))))


;; -----------------------------------------------------------------------------
;; SUBSCRIPTIONS
(rf/reg-sub
 ::board-size
 (fn [db [_ _]]
   (:board-size db)))

(rf/reg-sub
 ::intersection
 (fn [db [_ coord-name]]
   (get-in db [:active-board coord-name])))

(rf/reg-sub
 ::active-board
 :<- [::board-size]
 (fn [board-size [_ _]]
   (create-board board-size)))

(rf/reg-sub
 ::curr-turn
 (fn [db [_ _]]
   (:curr-turn db)))

(rf/reg-sub
 ::friendly-neighbor?
 (fn [db [_ coord-name]]
   (validation/friendly-neighbor? db coord-name)))

(defn position->img
  [position]
  (case position
    "POSITION_CORNER_TOP_LEFT" {:path "img/CORNER_INTERSECTION.png"}
    "POSITION_CORNER_TOP_RIGHT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(90deg)"}
    "POSITION_CORNER_BOTTOM_LEFT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(-90deg)"}
    "POSITION_CORNER_BOTTOM_RIGHT" {:path "img/CORNER_INTERSECTION.png" :rotate "rotate(180deg)"}
    "POSITION_SIDE_TOP" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(0deg)"}
    "POSITION_SIDE_BOTTOM" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(180deg)"}
    "POSITION_SIDE_LEFT" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(-90deg)"}
    "POSITION_SIDE_RIGHT" {:path "img/SIDE_INTERSECTION.png" :rotate "rotate(90deg)"}
    "POSITION_MIDDLE" {:path "img/MIDDLE_INTERSECTION.png"}
    {:path "img/MIDDLE_INTERSECTION.png"}))

;; -----------------------------------------------------------------------------
;; VIEWS
(defn Intersection
  "Intersection renders a single point, the smallest atomic unit on the board."
  [_idx coord]
  (let [intersection @(rf/subscribe [::intersection coord])
        position (:position intersection)
        empty-img (position->img position)
        black-stone-img "img/BLACK_STONE.png"
        white-stone-img "img/WHITE_STONE.png"]
    [:div
     {:key coord
      :style {:display "inline-block"}}
     [:img {:src (cond
                   (= "WHITE" (:stone intersection)) white-stone-img
                   (= "BLACK" (:stone intersection)) black-stone-img
                   :else (:path empty-img))
            :style {:transform (when (nil? (:stone intersection)) (:rotate empty-img))
                    :max-width "30px"}
            :draggable "false"
            :on-click #(rf/dispatch [::play-move coord])}]]))

(defn Row
  "Row renders a row of intersections."
  [idx row]
  [:li {:key idx
        :style {:font-size 0}}
   (doall (map-indexed Intersection row))])

(defn Board
  "Board renders the game board."
  []
  (let [new-board @(rf/subscribe [::active-board])]
    [:div {:id "board" :style {:font-size "30px"}}
     [:ul {:id "allRows" :style {:listStyleType "none" :white-space "no wrap"}}
      (doall (map-indexed Row new-board))]]))