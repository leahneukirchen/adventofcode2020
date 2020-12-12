(def data
  (->> (slurp "day10")
       (clojure.string/split-lines)
       (map read-string)
       vec))

(def diff
  (->> data
       (#(cons 0 %))
       (#(cons (+ 3 (reduce max %)) %))
       sort
       (#(map - (rest %) %))))

(->> diff
     frequencies
     (#(map % [1 3]))
     (apply *)) ; => 1820

(->> diff
     (partition-by identity)
     (filter #(= (first %) 1))
     (map count)
     (map #(inc (* (dec %) (/ % 2))))   ; http://oeis.org/A000124
     (apply *)) ; => 3454189699072N

;; => (1 3 1 1 1 3 1 1 3 1 3 3)
;;      1     4      2    1
;;
;; => (1 1 1 1 3 1 1 1 1 3 3 1 1 1 3 1 1 3 3 1 1 1 1 3 1 3 3 1 1 1 1 3)
;;        7         7          4      2         7      1        7
;; 19208: 2 2 2 7 7 7 7
