(ns tenkeylyser.core
  (:gen-class)
  (:require
   [tenkeylyser.gendata :as gd]
   [tenkeylyser.calcstats :as cs]))

(defn -main
  "MAIN FUNCTION"
  [& args]
  (let [d (gd/readdata "e200")
        hms (cs/calchmscore cs/hyper d)]
    (println "Heat map score:" hms)))

; (defn -main
;   "I don't do a whole lot ... yet."
;   [& args]
;   (println "Hello, World!"))
