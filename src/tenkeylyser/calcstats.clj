(ns tenkeylyser.calcstats
  (:require
    [tenkeylyser.layoutools :as lt]))

; Each layout will be defined as a map with entries :left and :right for each hand.
; Each entry in the maps will take the form of <character, map>
; The key will be an array of int[2]s.
; Heat map scores are defined arbitrarily, but the auto-generator treats the heatmap score of a character as the sum of the locations.
; The heat map will be a function that takes in a vector of coordinates and spits out the heatmap score.

(defn hmscore [character layout]
  (let [hm {[-2 1]  5.0 [-1 1]  4.0 [0 1]  3.0
            [-2 0]  3.0 [-1 0]  2.0 [0 0]  1.0
            [-2 -1] 4.0 [-1 -1] 3.0 [0 -1] 2.0}
        locs (lt/getloc character layout)]
    (reduce +
            (for [l locs] (hm l)))))

(defn totalhmscore [viewlayout data]
  (assert (not (nil? (data :monograms))))
  (let [monograms (data :monograms)]
    (reduce +
            (for [i monograms]
              (* (hmscore (first (first i)) viewlayout) (second i))))))

(defn subvect [v0 v1]
  (if (or (nil? v0) (nil? v1))
    nil
    (map - v1 v0)))

(defn magnvect [v]
  (Math/sqrt (reduce +
                     (map #(* % %) v))))

(defn bigramdist [bigram layout]
  (assert (= 2 (count bigram)))
  (if (not=
       (lt/gethand (first bigram) layout)
       (lt/gethand (second bigram) layout))
    0
    (let [loc1 (lt/getloc (first bigram) layout)
          loc2 (lt/getloc (second bigram) layout)
          locs (apply conj loc1 (for [i loc2] i))]
      (reduce + (for [i (range (dec (count locs)))]
                  (let [curr (nth locs i)
                        next (nth locs (inc i))]
                    (magnvect (subvect curr next))))))))

(defn totaldist [viewlayout data]
  (assert (not (nil? (data :bigrams))))
  (let [bigrams (data :bigrams)]
    (reduce +
            (for [i bigrams]
              (* (bigramdist (first i) viewlayout) (second i))))))

(defn alt? [bigram layout]
  (assert (= 2 (count bigram)))
  (if (not=
       (lt/gethand (first  bigram) layout)
       (lt/gethand (second bigram) layout))
    1
    0))

(defn totalalt [viewlayout data]
  (assert (not (nil? (data :bigrams))))
  (let [bigrams (data :bigrams)]
    (reduce +
            (for [i bigrams]
              (* (alt? (first i) viewlayout) (second i))))))

(defn swipetap? [bigram layout]
  (assert (= 2 (count bigram)))
  (if (and
       (=
        (last  (lt/getloc (first bigram)  layout))
        (first (lt/getloc (second bigram) layout)))
       (not= (first bigram) (second bigram)))
    1
    0))

(defn totalswipetap [viewlayout data]
  (assert (not (nil? (data :bigrams))))
  (let [bigrams (data :bigrams)]
    (reduce +
            (for [i bigrams]
              (* (swipetap? (first i) viewlayout) (second i))))))

(defn repswipe? [bigram layout]
  (assert (= 2 (count bigram)))
  (if (and
       (= (first bigram) (second bigram))
       (> (-> bigram (first) (lt/getloc layout) (count)) 1))
    1
    0))

(defn totalrepswipe [viewlayout data]
  (assert (not (nil? (data :bigrams))))
  (let [bigrams (data :bigrams)]
    (reduce +
            (for [i bigrams]
              (* (repswipe? (first i) viewlayout) (second i))))))
