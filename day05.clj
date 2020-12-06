(def data
  (->> (slurp "day05")
       (clojure.string/split-lines)
       (map (fn [x] (reduce #(+ (* 2 %1) %2)
                            (map {\F 0, \B 1, \L 0, \R 1} x))))))

(apply max data) ; => 818
(apply min (remove (set data) (map inc data))) ; => 559
