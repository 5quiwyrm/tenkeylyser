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

(def helpmsg
  (slurp "./src/tenkeylyser/helpmsg.txt"))

(defn -main
  "MAIN FUNCTION"
  [& args]
  (case (first args)
    "view"
      (if (< 2 (count args))
        (let [layoutname (second args)
              corpusname (second (rest args))
              layout (rw/readlayout layoutname)
              corpus (rw/readdata corpusname)]
          (op/printlayoutstats layout corpus))
        (throw (Exception. "Please provide a layout and a corpus!")))
    "gendata"
      (if (< 1 (count args))
        (let [corpusname (second args)]
          (-> corpusname (gd/parsecorpus) (rw/writedata)))
        (throw (Exception. "Please provide a corpus name!")))
    (println helpmsg)))

