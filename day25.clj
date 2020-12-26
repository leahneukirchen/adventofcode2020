(def data
  (->> (slurp "day25")
       clojure.string/split-lines
       (map read-string)))

; (def data '(5764801 17807724))

(defn gen [m]
  (->> 1
       (iterate #(mod (* % m) 20201227))
       (map-indexed vector)))

(def iters
  (some #(if (= (second %) (first data))
           (first %))
        (gen 7)))

(second (nth (gen (second data)) iters))
;; => 5414549

(defn m* [a b]
  (mod (* a b) 20201227))

(loop [pub 1 key1 1 key2 1]
  (cond
    (= pub (first data)) key2
    (= pub (second data)) key1
    :else (recur (m* pub 7) (m* key1 (first data)) (m* key2 (second data)))))
;; => 5414549
