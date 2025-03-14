(ns tenkeylyser.calcstats)

; Each layout will be defined as a map with entries :left and :right for each hand.
; Each entry in the maps will take the form of <character, map>
; The key will be an array of int[2]s.
; Heat map scores are defined arbitrarily, but the auto-generator treats the heatmap score of a character as the sum of the locations.
; The heat map will be a function that takes in a vector of coordinates and spits out the heatmap score.

(defn hmscore [character layout]
  (let [hm {[-2 1]  5.0 [-1 1]  4.0 [0 1]  3.0
            [-2 0]  3.0 [-1 0]  2.0 [0 0]  1.0
            [-2 -1] 4.0 [-1 -1] 3.0 [0 -1] 2.0}
        locs (if (nil? ((layout :left) character))
               (if (nil? ((layout :right) character))
                 nil
                 ((layout :right) character))
               ((layout :left) character))]
    (reduce +
            (for [l locs] (hm l)))))

(def hyper {:name "hyper"
            :left {\e [[0 0]]
                   \, [[0 0] [0 -1]]
                   \. [[0 0] [0 1]]
                   \? [[0 0] [-1 0]]

                   \o [[-1 0]]
                   \k [[-1 0] [0 0]]

                   \i [[0 -1]]
                   \; [[0 -1] [-1 -1]]
                   \' [[0 -1] [0 0]]

                   \g [[-1 -1]]
                   \" [[-1 -1] [0 -1]]
                   \z [[-1 -1] [-1 0]]

                   \a [[0 1]]
                   \j [[0 1] [-1 1]]
                   \! [[0 1] [0 0]]

                   \u [[-1 0]]
                   \x [[-1 0] [0 0]]

                   \space [[-2 0]]}
            :right {\n [[0 1]]
                    \b [[0 1] [-1 1]]
                    \d [[0 1] [0 0]]

                    \l [[-1 1]]
                    \y [[-1 1] [-1 0]]
                    \@ [[-1 1] [0 1]]

                    \t [[0 0]]
                    \m [[0 0] [0 1]]
                    \p [[0 0] [-1 0]]
                    \w [[0 0] [0 -1]]

                    \s [[-1 0]]
                    \c [[-1 0] [0 0]]
                    \- [[-1 0] [-1 1]]
                    \q [[-1 0] [-1 -1]]

                    \h [[0 -1]]
                    \v [[0 -1] [0 0]]
                    \f [[0 -1] [-1 -1]]

                    \r [[-1 -1]]
                    \/ [[-1 -1] [-1 0]]
                    \# [[-1 -1] [0.5 -1]]

                    \space [[-2 0]]}})

(defn calchmscore [viewlayout data]
  (assert (not (nil? (data :monograms))))
  (let [monograms (data :monograms)]
    (reduce +
            (for [i (keys monograms) f (vals monograms)]
              (* (hmscore (first i) viewlayout) f)))))

(def layoutfileext ".akl")

(defn readlayout [name]
  (->> name (#(str "./layouts/" % layoutfileext)) (slurp) (read-string)))

(defn writelayout [layout]
  (->> layout (str) (spit (str "./layouts/" (layout :name) layoutfileext))))