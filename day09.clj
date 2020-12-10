(def data
  (->> (slurp "day09")
       (clojure.string/split-lines)
       (map read-string)
       vec))

(defn is-sum [n & ms]
  (some #{n} (for [x ms y ms]
               (+ x y))))

(def part1
  (some #(if-not (apply is-sum (reverse %))
           (last %))
        (partition 26 1 data)))
part1 ; => 138879426

(some (fn [xs]
        (let [s (take-while #(<= % part1) (reductions + xs))]
          (if (some #{part1} s)
            (apply + (apply (juxt min max) (take (count s) xs))))))
      (iterate rest data)) ; => 23761694
