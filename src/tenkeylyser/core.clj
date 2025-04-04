(ns tenkeylyser.core
  (:gen-class)
  (:require
   [tenkeylyser.calcstats :as cs]
   [tenkeylyser.customise :as cu]
   [tenkeylyser.examples :as ex]
   [tenkeylyser.gendata :as gd]
   [tenkeylyser.layouttools :as lt]
   [tenkeylyser.output :as op]
   [tenkeylyser.readwrite :as rw]
   [clojure.tools.namespace.repl :as rpl]))

(defn rl [] (rpl/refresh))

(defn -main
  "MAIN FUNCTION"
  [& args]
  (op/printlayout (rw/readlayout "hyper") (rw/readdata "e200")))

