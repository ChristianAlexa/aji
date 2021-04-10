(ns aji.db)

(def default-db
  {:name "aji"
   :board-size 9
   :board {"1-A" {:stone nil :liberties 4}
           "1-B" {:stone nil :liberties 4}
           "1-C" {:stone nil :liberties 4}
           "1-D" {:stone nil :liberties 4}
           "1-E" {:stone nil :liberties 4}
           "1-F" {:stone nil :liberties 4}
           "1-G" {:stone nil :liberties 4}
           "1-H" {:stone nil :liberties 4}
           "1-I" {:stone nil :liberties 4}
           "1-J" {:stone nil :liberties 4}
           "1-K" {:stone nil :liberties 4}
           "1-L" {:stone nil :liberties 4}
           "1-M" {:stone nil :liberties 4}
           "1-N" {:stone nil :liberties 4}
           "1-O" {:stone nil :liberties 4}
           "1-P" {:stone nil :liberties 4}
           "1-Q" {:stone nil :liberties 4}
           "1-R" {:stone nil :liberties 4}
           "1-S" {:stone nil :liberties 4}
           "1-T" {:stone nil :liberties 4}

           "2-A" {:stone nil :liberties 4}
           "2-B" {:stone nil :liberties 4}
           "2-C" {:stone nil :liberties 4}
           "2-D" {:stone nil :liberties 4}
           "2-E" {:stone nil :liberties 4}
           "2-F" {:stone nil :liberties 4}
           "2-G" {:stone nil :liberties 4}
           "2-H" {:stone nil :liberties 4}
           "2-I" {:stone nil :liberties 4}
           "2-J" {:stone nil :liberties 4}
           "2-K" {:stone nil :liberties 4}
           "2-L" {:stone nil :liberties 4}
           "2-M" {:stone nil :liberties 4}
           "2-N" {:stone nil :liberties 4}
           "2-O" {:stone nil :liberties 4}
           "2-P" {:stone nil :liberties 4}
           "2-Q" {:stone nil :liberties 4}
           "2-R" {:stone nil :liberties 4}
           "2-S" {:stone nil :liberties 4}
           "2-T" {:stone nil :liberties 4}

           "3-A" {:stone nil :liberties 4}
           "3-B" {:stone nil :liberties 4}
           "3-C" {:stone nil :liberties 4}
           "3-D" {:stone nil :liberties 4}
           "3-E" {:stone nil :liberties 4}
           "3-F" {:stone nil :liberties 4}
           "3-G" {:stone nil :liberties 4}
           "3-H" {:stone nil :liberties 4}
           "3-I" {:stone nil :liberties 4}
           "3-J" {:stone nil :liberties 4}
           "3-K" {:stone nil :liberties 4}
           "3-L" {:stone nil :liberties 4}
           "3-M" {:stone nil :liberties 4}
           "3-N" {:stone nil :liberties 4}
           "3-O" {:stone nil :liberties 4}
           "3-P" {:stone nil :liberties 4}
           "3-Q" {:stone nil :liberties 4}
           "3-R" {:stone nil :liberties 4}
           "3-S" {:stone nil :liberties 4}
           "3-T" {:stone nil :liberties 4}

          ;;  [4 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [4 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [4 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [5 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [5 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [5 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [6 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [6 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [6 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [7 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [7 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [7 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [8 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [8 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [8 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [9 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [9 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [9 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [10 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [10 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [10 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [11 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [11 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [11 "T"] {:stone nil :liberties 2 :position "POS_CORNER"}

          ;;  [12 "A"] {:stone nil :liberties 3 :position "POS_SIDE"}
          ;;  [12 "B"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "C"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "D"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "E"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "F"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "G"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "H"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "I"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "J"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "K"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "L"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "M"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "N"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "O"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "P"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "Q"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "R"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "S"] {:stone nil :liberties 4 :position "POS_MIDDLE"}
          ;;  [12 "T"] {:stone nil :liberties 3 :position "POS_SIDE"}

          ;;  [13 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [13 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [13 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [14 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [14 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [14 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [15 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [15 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [15 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [16 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [16 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [16 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [17 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [17 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [17 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [18 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [18 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [18 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}

          ;;  [19 "A"] {:stone nil :liberties 2 :position "POS_CORNER"}
          ;;  [19 "B"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "C"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "D"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "E"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "F"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "G"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "H"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "I"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "J"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "K"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "L"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "M"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "N"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "O"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "P"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "Q"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "R"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "S"] {:stone nil :liberties 4 :position "POS_SIDE"}
          ;;  [19 "T"] {:stone nil :liberties 4 :position "POS_CORNER"}
           }})

