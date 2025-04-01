(ns tenkeylyser.core
  (:gen-class)
  (:require
   [tenkeylyser.gendata :as gd]
   [tenkeylyser.examples :as ex]
   [tenkeylyser.readwrite :as rw]
   [tenkeylyser.calcstats :as cs]
   [tenkeylyser.output :as op]
   [clojure.tools.namespace.repl :only (refresh)]))

(defn rl [] (clojure.tools.namespace.repl/refresh))

(defn -main
  "MAIN FUNCTION"
  [& args]
  (op/printstats ex/hyper (rw/readdata "e200")))

