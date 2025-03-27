(ns tenkeylyser.core
  (:gen-class)
  (:require
   [tenkeylyser.gendata :as gd]
   [tenkeylyser.examples :as ex]
   [tenkeylyser.readwrite :as rw]
   [tenkeylyser.calcstats :as cs]))

(defn -main
  "MAIN FUNCTION"
  [& args]
  (let [d (rw/readdata "e200")
        thms (cs/totalhmscore ex/hyper d)
        tdst (cs/totaldist ex/hyper d)
        talt (cs/totalalt ex/hyper d)
        tswt (cs/totalswipetap ex/hyper d)
        trt  (cs/totalrepswipe ex/hyper d)]
    (printf "--------------------------------------------\n")
    (->> thms                              (format "Total heat map score:   %.2f")   (printf "| %-40s |\n"))
    (->> (d :count) (/ thms)               (format "Average heat map score: %.2f")   (printf "| %-40s |\n"))
    (->> tdst                              (format "Total distance score:   %.2f")   (printf "| %-40s |\n"))
    (->> (d :count) (dec) (/ talt)         (format "Average distance score: %.2f")   (printf "| %-40s |\n"))
    (->> (d :count) (dec) (/ talt) (* 100) (format "Alt percentage:         %.2f%%") (printf "| %-40s |\n"))
    (->> (d :count) (dec) (/ tswt) (* 100) (format "Swipe-tap / Tap-swipe:  %.2f%%") (printf "| %-40s |\n"))
    (->> (d :count) (dec) (/ trt)  (* 100) (format "Repeated swipes:        %.2f%%") (printf "| %-40s |\n"))
    (printf "--------------------------------------------\n")))

