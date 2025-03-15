(ns tenkeylyser.readwrite)

(def layoutfileext ".akl")

(defn readlayout [name]
  (->> name (#(str "./layouts/" % layoutfileext)) (slurp) (read-string)))

(defn writelayout [layout]
  (->> layout (str) (spit (str "./layouts/" (layout :name) layoutfileext))))