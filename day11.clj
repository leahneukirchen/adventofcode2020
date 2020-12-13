(def data
  (->> (slurp "day11")
       (clojure.string/split-lines)
       (map vec)
       vec))

(defn in-bound [[x y]]
  (and (< -1 x (count data))
       (< -1 y (count (first data)))))

(defn fix [l]
  (->> l
       (partition 2 1)
       (drop-while (fn [[x y]] (not= x y)))
       ffirst))

(defn gneighbors [ch? [x y]]
  (->> (for [dir (for [dx [-1 0 1]
                       dy [-1 0 1]
                       :when (not= 0 dx dy)]
                   [dx dy])]
         (->> [x y]
              (iterate (partial mapv + dir))
              (drop 1)
              (take-while in-bound)
              (map (fn [p] [p (get-in data p)]))
              (some (fn [[p c]] (if (ch? c) p)))))
       (remove nil?)))

(defn gstep [neighbors ncnt data]
  (vec (for [x (range (count data))]
         (vec (for [y (range (count (first data)))]
                (let [occ (->> (neighbors [x y])
                               (map (partial get-in data))
                               (filter #{\#})
                               count)]
                  (case (get-in data [x y])
                    \. \.
                    \L (if (zero? occ) \# \L)
                    \# (if (>= occ ncnt) \L \#))))))))

(->> data
     (iterate (partial gstep (partial gneighbors #{\L \# \.}) 4))
     fix
     flatten
     (filter #{\#})
     count) ; => 2324

(->> data
     (iterate (partial gstep (partial gneighbors #{\L \#}) 5))
     fix
     flatten
     (filter #{\#})
     count) ; => 2068
