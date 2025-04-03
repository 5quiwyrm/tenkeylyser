(ns tenkeylyser.layoutools)

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

(defn updatename [layout newname]
  {:name newname
   :left (layout :left)
   :right (layout :right)})

(defn swapletters [layout c0 c1]
  (let []))