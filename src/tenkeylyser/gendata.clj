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
     :trigrams   (ngrams 3)}))

