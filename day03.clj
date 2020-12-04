(def data
  (->> (slurp "day03")
       (clojure.string/split-lines))

(defn slop [step]
  (->> (range)
       (map #(mapv (partial * %) step))
       (take-while #(< (first %) (count data)))
       (map (fn [[x y]]
              (nth (cycle (nth data x)) y)))
       (filter #{\#})
       (count)))

(slop [1 3]) ; => 270
(apply * (map slop [[1 1] [1 3] [1 5] [1 7] [2 1]])) ; => 2122848000
