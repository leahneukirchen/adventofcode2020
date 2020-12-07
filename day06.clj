(def data
  (clojure.string/split (slurp "day06") #"\n\n"))

(->> data
     (map #(->> %
                (remove #{\newline})
                set
                count))
     (apply +)) ; => 6612

(->> data
     (map #(->> %
                clojure.string/split-lines
                (map set)
                (reduce clojure.set/intersection)
                count))
     (apply +)) ; => 3268
