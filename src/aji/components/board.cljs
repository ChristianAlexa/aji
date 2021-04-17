(ns aji.components.board
  (:require
   [re-frame.core :as rf]
   [aji.go.validation :as validation]))

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

(def MAX_BOARD_SIZE
  "MAX_BOARD_SIZE is for a 19x19 standard board."
  19)

(defn get-neighbor-coord
  "get-neighbor-coord returns the neighbor-coord relative to the given coord intersection."
  [direction coord]
  (let [coord-num  (js/parseInt (re-find #"\d+" coord))
        coord-letter (last coord)]
    (case direction
      "NORTH" (when (pos? (dec coord-num))
                (str (dec coord-num) "-" coord-letter))
      "EAST" (when (< (.indexOf letters-vec coord-letter) MAX_BOARD_SIZE)
               (str coord-num "-" (nth letters-vec (inc (.indexOf letters-vec coord-letter)))))
      "SOUTH" (when (<= (inc coord-num) MAX_BOARD_SIZE)
                (str (inc coord-num) "-" coord-letter))
      "WEST" (when-not (neg? (dec (.indexOf letters-vec coord-letter)))
               (str coord-num "-" (nth letters-vec (dec (.indexOf letters-vec coord-letter))))))))

(defn get-neighbors-coords
  [coord]
  {:north (get-neighbor-coord "NORTH" coord)
   :east (get-neighbor-coord "EAST" coord)
   :south (get-neighbor-coord "SOUTH" coord)
   :west (get-neighbor-coord "WEST" coord)})

;; TODO 
;; - getting a return of 20 rows of intersection when expecting 19
;; - each map value needs to be paired with its coord
;; - the combined map needs move history and curr move num
;; - this data structure assoc to the app-db
(defn seed-active-board
  "seed-active-board creates the key and values for all 19x19 coords along with the move history and curr move num in app-db."
  []
  (let [board-with-coords (create-board 19)]
    (map (fn [row]
           (map (fn [coord]
                  (cond

                ;; CORNERS
                    (= coord "1-A") {:stone nil :liberties 2 :position "POSITION_CORNER_TOP_LEFT" :neighbors (get-neighbors-coords coord)}
                    (= coord "1-T") {:stone nil :liberties 2 :position "POSITION_CORNER_TOP_RIGHT" :neighbors (get-neighbors-coords coord)}
                    (= coord "19-A") {:stone nil :liberties 2 :position "POSITION_CORNER_BOTTOM_LEFT" :neighbors (get-neighbors-coords coord)}
                    (= coord "19-T") {:stone nil :liberties 2 :position "POSITION_CORNER_BOTTOM_RIGHT" :neighbors (get-neighbors-coords coord)}

                ;; TOP SIDE
                    (= 1 (js/parseInt (re-find #"\d+" coord))) {:stone nil :liberties 3 :position "POSITION_SIDE_TOP" :neighbors (get-neighbors-coords coord)}

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

;; -----------------------------------------------------------------------------
;; EVENT HANDLERS
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
                                                     (let [new-num-liberties (dec (:liberties neighbor))]
                                                       (if (and (zero? new-num-liberties)
                                                                (not= stone-color (:stone neighbor)))
                                                         (-> neighbor
                                                             (assoc :liberties new-num-liberties)
                                                             (assoc :stone nil))
                                                         (assoc neighbor :liberties new-num-liberties))))
                                                   neighbors-vals)
         curr-move-num (get-in db [:active-board :curr-move-num])
         last-move-played {(inc curr-move-num) {:coord-name coord-name :color stone-color}}
         curr-move-history (get-in db [:active-board :move-history])
         new-move-history (conj curr-move-history last-move-played)
         legal-move? (validation/legal-move? db coord-name)]
     (when legal-move?
       (-> db
           (assoc-in [:active-board (nth neighbors-coords 0)] (nth neighbors-vals-with-decremented-libs 0))
           (assoc-in [:active-board (nth neighbors-coords 1)] (nth neighbors-vals-with-decremented-libs 1))
           (assoc-in [:active-board (nth neighbors-coords 2)] (nth neighbors-vals-with-decremented-libs 2))
           (assoc-in [:active-board (nth neighbors-coords 3)] (nth neighbors-vals-with-decremented-libs 3))
           (assoc-in [:active-board coord-name] new-intersection)
           (assoc-in [:active-board :curr-move-num] (inc curr-move-num))
           (assoc-in [:active-board :move-history] new-move-history)
           (assoc :curr-turn next-turn))))))

;; dec libs
;; if 0 , set stone to nil

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
  [idx coord-name]
  (let [intersection @(rf/subscribe [::intersection coord-name])
        position (:position intersection)
        empty-img (position->img position)
        black-stone-img "img/BLACK_STONE.png"
        white-stone-img "img/WHITE_STONE.png"]
    [:div
     {:key coord-name
      :style {:display "inline-block"}}
     [:img {:src (cond
                   (= "WHITE" (:stone intersection)) white-stone-img
                   (= "BLACK" (:stone intersection)) black-stone-img
                   :else (:path empty-img))
            :style (when (nil? (:stone intersection)) {:transform (:rotate empty-img)})
            :on-click #(rf/dispatch [::play-move coord-name])}]]))

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
    [:div.m-4 {:id "board" :style {:font-size "30px"}}
     [:ul {:id "allRows" :style {:listStyleType "none" :white-space "no wrap"}}
      (doall (map-indexed Row new-board))]]))