(def data
  (->> (slurp "day01")
       (clojure.string/split-lines)
       (map #(Integer/parseInt %))))

(first (for [a data
             b data
             :when (= 2020 (+ a b))]
         (* a b))) ; => 805731

(first (for [a data
             b data
             c data
             :when (= 2020 (+ a b c))]
         (* a b c))) ; => 192684960
