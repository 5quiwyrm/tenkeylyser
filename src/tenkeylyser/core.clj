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
        thms (cs/calchmscore ex/hyper d)
        tdst (cs/totaldist ex/hyper d)
        talt (cs/totalalt ex/hyper d)]
    (println "Total heat map score:" thms)
    (println "Average heat map score:" (->> (d :count) (/ thms)))
    (println "Total distance score:" tdst)
    (println "Average distance score:" (->> (d :count) (dec) (/ talt)))
    (printf "Alt percentage: %.2f%%\n" (->> (d :count) (dec) (/ talt) (* 100)))))

