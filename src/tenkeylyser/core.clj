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
    (printf "Total heat map score: %.2f\n" thms)
    (printf "Average heat map score: %.2f\n" (->> (d :count) (/ thms)))
    (printf "Total distance score: %.2f\n" tdst)
    (printf "Average distance score: %.2f\n" (->> (d :count) (dec) (/ talt)))
    (printf "Alt percentage: %.2f%%\n" (->> (d :count) (dec) (/ talt) (* 100)))
    (printf "Swipe-tap / Tap-swipe: %.2f%%\n" (->> (d :count) (dec) (/ tswt) (* 100)))
    (printf "Repeated swipes: %.2f%%\n", (->> (d :count) (dec) (/ trt) (* 100)))))

