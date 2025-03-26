(ns tenkeylyser.calcstats)

; Each layout will be defined as a map with entries :left and :right for each hand.
; Each entry in the maps will take the form of <character, map>
; The key will be an array of int[2]s.
; Heat map scores are defined arbitrarily, but the auto-generator treats the heatmap score of a character as the sum of the locations.
; The heat map will be a function that takes in a vector of coordinates and spits out the heatmap score.

(defn getloc [character layout]
  (if (nil? ((layout :left) character))
    (if (nil? ((layout :right) character))
      nil
      ((layout :right) character))
    ((layout :left) character)))

(defn gethand [character layout]
  (if (nil? ((layout :left) character))
    (if (nil? ((layout :right) character))
      nil
      :right)
    :left))

(defn hmscore [character layout]
  (let [hm {[-2 1]  5.0 [-1 1]  4.0 [0 1]  3.0
            [-2 0]  3.0 [-1 0]  2.0 [0 0]  1.0
            [-2 -1] 4.0 [-1 -1] 3.0 [0 -1] 2.0}
        locs (getloc character layout)]
    (reduce +
            (for [l locs] (hm l)))))

(defn totalhmscore [viewlayout data]
  (assert (not (nil? (data :monograms))))
  (let [monograms (data :monograms)]
    (reduce +
            (for [i monograms]
              (* (hmscore (first (first i)) viewlayout) (second i))))))

(defn bigramdist [bigram layout]
  (assert (= 2 (count bigram)))
  (if (not=
       (gethand (first bigram) layout)
       (gethand (second bigram) layout))
    0
    (let [loc1 (getloc (first bigram) layout)
          loc2 (getloc (second bigram) layout)
          locs (apply conj loc1 (for [i loc2] i))]
      (reduce + (for [i (range (dec (count locs)))]
                  (let [curr (nth locs i)
                        next (nth locs (inc i))
                        sq (fn [x] (* x x))
                        diffx (sq (- (nth curr 0) (nth next 0)))
                        diffy (sq (- (nth curr 1) (nth next 1)))]
                    (Math/sqrt (+ diffx diffy))))))))

(defn totaldist [viewlayout data]
  (assert (not (nil? (data :bigrams))))
  (let [bigrams (data :bigrams)]
    (reduce +
            (for [i bigrams]
              (* (bigramdist (first i) viewlayout) (second i))))))

(defn alt? [bigram layout]
  (assert (= 2 (count bigram)))
  (if (not=
       (gethand (first  bigram) layout)
       (gethand (second bigram) layout))
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
        (last  (getloc (first bigram)  layout))
        (first (getloc (second bigram) layout)))
       (not= (first bigram) (second bigram)))
    1
    0))

(defn totalswipetap [viewlayout data]
  (assert (not (nil? (data :bigrams))))
  (let [bigrams (data :bigrams)]
    (reduce +
            (for [i bigrams]
              (* (swipetap? (first i) viewlayout) (second i))))))
