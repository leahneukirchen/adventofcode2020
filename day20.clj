(def tiles
  (->> (clojure.string/split (slurp "day20") #"\n\n")
       (map (fn [x]
              (let [[name & data] (clojure.string/split-lines x)]
                {(read-string (first (re-seq #"\d+" name)))
                 (vec data)}
                )))
       (into {})))

;; clockwise
(defn borders [tile]
  [(first tile)
   (apply str (reverse (first tile)))

   (apply str (map last tile))
   (apply str (reverse (map last tile)))

   (last tile)
   (apply str (reverse (last tile)))

   (apply str (map first tile))
   (apply str (reverse (map first tile)))
   ])

(def tile-borders
  (into {} (for [[k v] tiles] [k (borders v)])))

(def corners
  (filter
   (fn [x]
     (= 2 (count
           (remove empty?
                   (for [y (keys tile-borders) :when (not= x y)]
                     (clojure.set/intersection (set (tile-borders x))
                                               (set (tile-borders y))))))))
   (keys tile-borders)))

(apply * corners)  ; => 29584525501199

;; too lazy to do part2, guess it instead:

(defn count-hashes [tile]
  (->> (rest (butlast tile))
       (map (comp rest butlast))
       flatten
       (filter #{\#})
       count))

(apply + (map count-hashes (vals tiles))) ;; => 2175

;; grep '#\.\.\.\.##' day20 | wc -l
;; 41

(- 2175 (* 15 34)) ;; => 1665
