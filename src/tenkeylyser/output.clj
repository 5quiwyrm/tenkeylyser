(ns tenkeylyser.output
  (:require
   [tenkeylyser.calcstats :as cs]))

(defn printlayout
  "Prints a tenkey layout."
  [viewlayout]
  {:name  (viewlayout :name)
   :left  (into {} (map (fn [[key value]] (assoc {} key (into {} value))) (group-by #(first (second %)) (viewlayout :left))))
   :right (into {} (map (fn [[key value]] (assoc {} key (into {} value))) (group-by #(first (second %)) (viewlayout :right))))})

(defn printstats
  "Prints the stats of a given layout on a given corpus."
  [viewlayout data]
  (let [d    data
        thms (cs/totalhmscore  viewlayout data)
        tdst (cs/totaldist     viewlayout data)
        talt (cs/totalalt      viewlayout data)
        tswt (cs/totalswipetap viewlayout data)
        trt  (cs/totalrepswipe viewlayout data)]
    (printf "--------------------------------------------\n")
    (->> thms                              (format "Total heat map score:   %.2f")   (printf "| %-40s |\n"))
    (->> (d :count) (/ thms)               (format "Average heat map score: %.2f")   (printf "| %-40s |\n"))
    (->> tdst                              (format "Total distance score:   %.2f")   (printf "| %-40s |\n"))
    (->> (d :count) (dec) (/ talt)         (format "Average distance score: %.2f")   (printf "| %-40s |\n"))
    (->> (d :count) (dec) (/ talt) (* 100) (format "Alt percentage:         %.2f%%") (printf "| %-40s |\n"))
    (->> (d :count) (dec) (/ tswt) (* 100) (format "Swipe-tap / Tap-swipe:  %.2f%%") (printf "| %-40s |\n"))
    (->> (d :count) (dec) (/ trt)  (* 100) (format "Repeated swipes:        %.2f%%") (printf "| %-40s |\n"))
    (printf "--------------------------------------------\n")))

