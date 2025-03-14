(ns tenkeylyser.gendata)

(defn parsecorpus
  ; This takes in a file name and looks for a file in the corpora directory.
  ; Then it writes the bigram data to a file in the data directory.
  ; The data file will have the same name as the corpus, but will be a custom format.
  ; {:tag int}
  [corpusname]
  (let [contents (->> corpusname (#(str "./corpora/" % ".txt"))
                      (slurp)
                      (filter #(not= % \return)) ; Resolving annoying CRLF/LF issues
                      (apply str))
        ngrams (fn [size]
                 (->>
                  (for [i (-> contents (count) (- size) (range))]
                    (subs contents i (+ i size)))
                  (frequencies)
                  (#(update-vals % float))))]
    {:name      corpusname
     :count     (count contents)
     :monograms (ngrams 1)
     :bigrams   (ngrams 2)
     :trigram   (ngrams 3)}))

(defn writedata
  ; This takes in a map of ngram data, and writes it to a file
  ; in the ./data/ directory with the same name, and file extension .ngram
  [data]
  (assert (not (nil? (data :name))))
  (let [filename (str "./data/" (data :name) ".ngram")]
    (->> data (str) (spit filename))))

(defn readdata
  ; This takes a file name and looks for a file in the data directory.
  ; Then it reads from that file, and evals that file.
  ; Assuming all goes well, the file should contain n-gram data
  ; for the corpora of the name of the file.
  [corpusname]
  (->> corpusname (#(str "./data/" % ".ngram")) (slurp) (read-string)))
