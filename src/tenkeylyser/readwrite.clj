(ns tenkeylyser.readwrite
  (:require
    [clojure.pprint :as pp]
    [clojure.java.io :as io]))

(def layoutfileext ".akl")

(defn readlayout [name]
  (->> name (#(str "./layouts/" % layoutfileext)) (slurp) (read-string)))

(defn writelayout [layout]
  (pp/pprint layout (io/writer (str "./layouts/" (layout :name) layoutfileext))))

(defn writedata
  ; This takes in a map of ngram data, and writes it to a file
  ; in the ./data/ directory with the same name, and file extension .ngram
  [data]
  (assert (not (nil? (data :name))))
  (let [filename (str "./data/" (data :name) ".ngram")]
    (pp/pprint data (io/writer filename))))

(defn readdata
  ; This takes a file name and looks for a file in the data directory.
  ; Then it reads from that file, and evals that file.
  ; Assuming all goes well, the file should contain n-gram data
  ; for the corpora of the name of the file.
  [corpusname]
  (->> corpusname (#(str "./data/" % ".ngram")) (slurp) (read-string)))

