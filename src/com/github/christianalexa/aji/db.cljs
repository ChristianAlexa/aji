(ns com.github.christianalexa.aji.db)

(def default-db
  {:name "aji"
   :board-size 19
   :curr-turn "TURN_BLACK"
   :selected-puzzle nil
   :dropdown-active? false
   :winning-move nil
   :puzzle-mode? false
   :max-num-moves nil
   :active-board {:curr-move-num 0
                  :move-history {}
                  :white-captured-stones 0
                  :black-captured-stones 0}})


;; TODO - schema lib needed
