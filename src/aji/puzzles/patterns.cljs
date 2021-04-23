(ns aji.puzzles.patterns)

(def PATTERNS
  {"PUZZLE_PATTERN_1" {:pattern {"2-C" {:stone "BLACK"
                                        :liberties 3
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "3-D" {:stone "BLACK"
                                        :liberties 3
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "4-C" {:stone "BLACK"
                                        :liberties 3
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "3-C" {:stone "WHITE"
                                        :liberties 1
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}}
                       :winning-move "3-B"
                       :goal-message "Capture white!"
                       :color-to-move "TURN_BLACK"}
   "PUZZLE_PATTERN_2" {:pattern {"4-I" {:stone "BLACK"
                                        :liberties 3
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "5-I" {:stone "WHITE"
                                        :liberties 4
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "5-J" {:stone "BLACK"
                                        :liberties 3
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "5-H" {:stone "WHITE"
                                        :liberties 1
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "6-I" {:stone "BLACK"
                                        :liberties 2
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "6-H" {:stone "BLACK"
                                        :liberties 3
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}
                                 "5-G" {:stone "BLACK"
                                        :liberties 1
                                        :position "POSITION_MIDDLE"
                                        :neighbors nil}}
                       :winning-move "4-H"
                       :goal-message "Save white!"
                       :color-to-move "TURN_WHITE"}})