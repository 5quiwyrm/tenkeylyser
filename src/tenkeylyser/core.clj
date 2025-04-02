(ns tenkeylyser.core
  (:gen-class)
  (:require
   [tenkeylyser.gendata :as gd]
   [tenkeylyser.examples :as ex]
   [tenkeylyser.readwrite :as rw]
   [tenkeylyser.calcstats :as cs]
   [tenkeylyser.output :as op]
   [clojure.tools.namespace.repl :as rpl]))

(defn rl [] (rpl/refresh))

(defn -main
  "MAIN FUNCTION"
  [& args]
  (op/printlayout ex/hyper (rw/readdata "e200")))

