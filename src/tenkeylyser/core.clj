(ns tenkeylyser.core
  (:gen-class)
  (:require
   [tenkeylyser.calcstats :as cs]
   [tenkeylyser.customise :as cu]
   [tenkeylyser.examples :as ex]
   [tenkeylyser.gendata :as gd]
   [tenkeylyser.layoutools :as lt]
   [tenkeylyser.output :as op]
   [tenkeylyser.readwrite :as rw]
   [clojure.tools.namespace.repl :as rpl]))

(defn rl [] (rpl/refresh))

(defn -main
  "MAIN FUNCTION"
  [& args]
  (if (< 1 (count args))
    (let [layoutname (first args)
          corpusname (second args)
          layout (rw/readlayout layoutname)
          corpus (rw/readdata corpusname)]
      (op/printlayoutstats layout corpus))
    (throw (Exception. "Please provide a layout and a corpus!"))))

