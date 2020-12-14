(let [[a l] (clojure.string/split-lines (slurp "day13"))]
  (def time (read-string a))
  (def buses (map read-string (replace {"x" "0"}
                                       (clojure.string/split l #",")))))

(->> (remove #{0} buses)
     (map #(vector % (- % (mod time %))))
     (apply min-key second)
     (apply *)) ; => 2298

(defn minv "Compute x^-1 mod y naively."
  [x y]
  (some #(if (= 1 (mod (* x %) y)) %) (range 1 y)))

;; using chinese remainder theorem
(let [[a b] (apply map vector
                   (remove (comp zero? second) (map-indexed vector buses)))
      N (apply * b)
      y (map #(/ N %) b)
      z (map minv y b)
      x (map * (map - a) y z)]
  (mod (apply + x) N)) ; => 783685719679632
