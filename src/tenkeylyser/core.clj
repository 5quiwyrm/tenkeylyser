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
        hms (cs/calchmscore ex/hyper d)]
    (println "Heat map score:" hms)
    (println "Distance score:" (cs/totaldist ex/hyper d))))

