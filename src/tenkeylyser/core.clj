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
        tdst (cs/totaldist ex/hyper d)]
    (println "Total heat map score:" thms)
    (println "Average heat map score:" (/ thms (d :count)))
    (println "Total distance score:" tdst)
    (println "Average distance score:" (/ tdst (dec (d :count))))))

