(def data
  (->> (slurp "day12")
       (clojure.string/split-lines)
       (map (juxt first (comp read-string #(subs % 1))))
       vec))

(defn rotl [[dx dy] deg]
  (case deg
     90 [(- dy) dx]
    180 [(- dx) (- dy)]
    270 [dy (- dx)]))

(defn step1 [[x y dx dy] [ins n]]
  (case ins
    \N [x (+ y n) dx dy]
    \S [x (- y n) dx dy]
    \E [(+ x n) y dx dy]
    \W [(- x n) y dx dy]
    \L (concat [x y] (rotl [dx dy] n))
    \R (concat [x y] (rotl [dx dy] (- 360 n)))    
    \F [(+ x (* n dx)) (+ y (* n dy)) dx dy]))

(apply + (map #(Math/abs %) (take 2 (reduce step1 [0 0 1 0] data)))) ; => 1294

(defn step2 [[x y wx wy] [ins n]]
  (case ins
    \N [x y wx (+ wy n)]
    \S [x y wx (- wy n)]
    \E [x y (+ wx n) wy]
    \W [x y (- wx n) wy]
    \L (concat [x y] (rotl [wx wy] n))
    \R (concat [x y] (rotl [wx wy] (- 360 n)))
    \F [(+ x (* n wx)) (+ y (* n wy)) wx wy]))

(apply + (map #(Math/abs %) (take 2 (reduce step2 [0 0 10 1] data)))) ; => 20592
