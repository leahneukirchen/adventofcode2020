(def data
  (->> (slurp "day17")
       clojure.string/split-lines))

(def cells
  (set
   (remove nil?
           (for [y (range (count data))
                 x (range (count (first data)))]
             (if (= \# (get-in data [y x]))
               (list 0 y x))))))

(def neighbour-offsets
  (let [digits [-1 0 1]]
    (for [x digits
          y digits
          z digits
          :when (not= 0 x y z)]
      [z y x])))

(defn all-neighbours [cell]
  (for [offset neighbour-offsets]
    (map + offset cell)))

(defn alive-neighbours [cells cell]
  (filter cells (all-neighbours cell)))

(defn dead-neighbours [cells cell]
  (filter (complement cells) (all-neighbours cell)))

(defn regulate [cells]
  (filter #(#{2 3} (count (alive-neighbours cells %))) cells))

(defn dead-neighbour-cells [cells]
  (reduce clojure.set/union (map (partial dead-neighbours cells) cells)))

(defn reproduce [cells]
  (filter #(= 3 (count (alive-neighbours cells %))) (dead-neighbour-cells cells)))

(defn tick [cells] 
  (clojure.set/union (set (reproduce cells)) (set (regulate cells))))


(->> cells
     (iterate tick)
     (drop 6)
     first
     count)
;; => 298


(def cells
  (set (map (partial cons 0) cells)))

(def neighbour-offsets
  (let [digits [-1 0 1]]
    (for [x digits
          y digits
          z digits
          w digits
          :when (not= 0 x y z w)] 
      [w z y x])))

(->> cells
     (iterate tick)
     (drop 6)
     first
     count)
;; => 1792
