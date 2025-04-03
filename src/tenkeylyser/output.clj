(ns tenkeylyser.output
  (:require
   [tenkeylyser.calcstats :as cs]
   [clojure.pprint :as pp]))

(defn pprintret [x]
  (do
    (pp/pprint x)
    x))

(defn printlayout
  "Prints a tenkey layout."
  [viewlayout data]
  (let [name (viewlayout :name)
        home nil
        up [0 1]
        down [0 -1]
        in [-1 0]
        out [1 0]
        left (->> (viewlayout :left)
                  (group-by (fn [[key value]] (first value))))
        right (->> (viewlayout :right)
                   (group-by (fn [[key value]] (first value))))
        filterlocs (fn [x l]
                     (into
                      {}
                      (map
                       (fn [[_ value]]
                         (let
                          [a
                           (first
                            (filter
                             (fn [[_ v]]
                               (= l
                                  (cs/subvect
                                   (first v)
                                   (second v))))
                             value))]
                           [(first (second a)) (first a)]))
                       x)))
        ccoerce (fn [ch]
                  (case ch
                    \space \~
                    nil \space
                    ch))
        lefthome  (filterlocs left  home)
        leftup    (filterlocs left  up)
        leftdown  (filterlocs left  down)
        leftin    (filterlocs left  in)
        leftout   (filterlocs left  out)
        righthome (filterlocs right home)
        rightup   (filterlocs right up)
        rightdown (filterlocs right down)
        rightin   (filterlocs right in)
        rightout  (filterlocs right out)]
    (do (printf "Layout: %s\n" (viewlayout :name))
        (printf "
=====================================================
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
|-------|-------|-------|   |-------|-------|-------|
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
|-------|-------|-------|   |-------|-------|-------|
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
| %c %c %c | %c %c %c | %c %c %c |   | %c %c %c | %c %c %c | %c %c %c |
=====================================================

" 
                \space (ccoerce (leftup  [-2 1])) \space
                \space (ccoerce (leftup  [-1 1])) \space
                \space (ccoerce (leftup  [0  1])) \space
                \space (ccoerce (rightup [0  1])) \space
                \space (ccoerce (rightup [-1 1])) \space
                \space (ccoerce (rightup [-2 1])) \space
                (ccoerce (leftin [-2 1])) (ccoerce (lefthome [-2 1])) (ccoerce (leftout [-2 1]))
                (ccoerce (leftin [-1 1])) (ccoerce (lefthome [-1 1])) (ccoerce (leftout [-1 1]))
                (ccoerce (leftin [0  1])) (ccoerce (lefthome [0  1])) (ccoerce (leftout [0  1]))
                (ccoerce (rightin [0  1])) (ccoerce (righthome [0  1])) (ccoerce (rightout [0  1]))
                (ccoerce (rightin [-1 1])) (ccoerce (righthome [-1 1])) (ccoerce (rightout [-1 1]))
                (ccoerce (rightin [-2 1])) (ccoerce (righthome [-2 1])) (ccoerce (rightout [-2 1]))
                \space (ccoerce (leftdown  [-2 1])) \space
                \space (ccoerce (leftdown  [-1 1])) \space
                \space (ccoerce (leftdown  [0  1])) \space
                \space (ccoerce (rightdown [0  1])) \space
                \space (ccoerce (rightdown [-1 1])) \space
                \space (ccoerce (rightdown [-2 1])) \space

                \space (ccoerce (leftup  [-2 0])) \space
                \space (ccoerce (leftup  [-1 0])) \space
                \space (ccoerce (leftup  [0  0])) \space
                \space (ccoerce (rightup [0  0])) \space
                \space (ccoerce (rightup [-1 0])) \space
                \space (ccoerce (rightup [-2 0])) \space
                (ccoerce (leftin [-2 0])) (ccoerce (lefthome [-2 0])) (ccoerce (leftout [-2 0]))
                (ccoerce (leftin [-1 0])) (ccoerce (lefthome [-1 0])) (ccoerce (leftout [-1 0]))
                (ccoerce (leftin [0  0])) (ccoerce (lefthome [0  0])) (ccoerce (leftout [0  0]))
                (ccoerce (rightin [0  0])) (ccoerce (righthome [0  0])) (ccoerce (rightout [0  0]))
                (ccoerce (rightin [-1 0])) (ccoerce (righthome [-1 0])) (ccoerce (rightout [-1 0]))
                (ccoerce (rightin [-2 0])) (ccoerce (righthome [-2 0])) (ccoerce (rightout [-2 0]))
                \space (ccoerce (leftdown  [-2 0])) \space
                \space (ccoerce (leftdown  [-1 0])) \space
                \space (ccoerce (leftdown  [0  0])) \space
                \space (ccoerce (rightdown [0  0])) \space
                \space (ccoerce (rightdown [-1 0])) \space
                \space (ccoerce (rightdown [-2 0])) \space

                \space (ccoerce (leftup  [-2 -1])) \space
                \space (ccoerce (leftup  [-1 -1])) \space
                \space (ccoerce (leftup  [0  -1])) \space
                \space (ccoerce (rightup [0  -1])) \space
                \space (ccoerce (rightup [-1 -1])) \space
                \space (ccoerce (rightup [-2 -1])) \space
                (ccoerce (leftin [-2 -1])) (ccoerce (lefthome [-2 -1])) (ccoerce (leftout [-2 -1]))
                (ccoerce (leftin [-1 -1])) (ccoerce (lefthome [-1 -1])) (ccoerce (leftout [-1 -1]))
                (ccoerce (leftin [0  -1])) (ccoerce (lefthome [0  -1])) (ccoerce (leftout [0  -1]))
                (ccoerce (rightin [0  -1])) (ccoerce (righthome [0  -1])) (ccoerce (rightout [0  -1]))
                (ccoerce (rightin [-1 -1])) (ccoerce (righthome [-1 -1])) (ccoerce (rightout [-1 -1]))
                (ccoerce (rightin [-2 -1])) (ccoerce (righthome [-2 -1])) (ccoerce (rightout [-2 -1]))
                \space (ccoerce (leftdown  [-2 -1])) \space
                \space (ccoerce (leftdown  [-1 -1])) \space
                \space (ccoerce (leftdown  [0  -1])) \space
                \space (ccoerce (rightdown [0  -1])) \space
                \space (ccoerce (rightdown [-1 -1])) \space
                \space (ccoerce (rightdown [-2 -1])) \space)))
   (let [d    data
         thms (cs/totalhmscore  viewlayout data)
         tdst (cs/totaldist     viewlayout data)
         talt (cs/totalalt      viewlayout data)
         tswt (cs/totalswipetap viewlayout data)
         trt  (cs/totalrepswipe viewlayout data)]
     (printf "Corpus: %s\n" (d :name))
     (printf "--------------------------------------------\n")
     (->> thms                              (format "Total heat map score:   %.2f")   (printf "| %-40s |\n"))
     (->> (d :count) (/ thms)               (format "Average heat map score: %.2f")   (printf "| %-40s |\n"))
     (->> tdst                              (format "Total distance score:   %.2f")   (printf "| %-40s |\n"))
     (->> (d :count) (dec) (/ talt)         (format "Average distance score: %.2f")   (printf "| %-40s |\n"))
     (->> (d :count) (dec) (/ talt) (* 100) (format "Alt percentage:         %.2f%%") (printf "| %-40s |\n"))
     (->> (d :count) (dec) (/ tswt) (* 100) (format "Swipe-tap / Tap-swipe:  %.2f%%") (printf "| %-40s |\n"))
     (->> (d :count) (dec) (/ trt)  (* 100) (format "Repeated swipes:        %.2f%%") (printf "| %-40s |\n"))
     (printf "--------------------------------------------\n")))

