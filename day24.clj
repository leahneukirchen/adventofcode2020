(def data
  (->> (slurp "day24")
       (clojure.string/split-lines)))

;     nw ne        1,-1   1,0
;    w  .  e     0,-1  0,0  0,1
;     sw se        -1,0  -1,1

(def dirs
  {"e"  [0 1]
   "se" [-1 1]
   "sw" [-1 0]
   "w"  [0 -1]
   "nw" [1 -1]
   "ne" [1 0]})

(defn pos [l]
  (->> l
       (re-seq #"e|se|sw|w|nw|ne")
       (map dirs)
       (apply (partial mapv +))))

(def black
  (set
   (map first (filter (fn [[k v]] (odd? v)) (frequencies (map pos data))))))

(count black)
;; => 386


(defn all-neighbours [cell]
  (for [offset (vals dirs)]
    (map + offset cell)))

(defn alive-neighbours [cells cell]
  (filter cells (all-neighbours cell)))

(defn dead-neighbours [cells cell]
  (filter (complement cells) (all-neighbours cell)))

(defn regulate [cells]
  (filter #(#{1 2} (count (alive-neighbours cells %))) cells))

(defn dead-neighbour-cells [cells]
  (reduce clojure.set/union (map (partial dead-neighbours cells) cells)))

(defn reproduce [cells]
  (filter #(= 2 (count (alive-neighbours cells %))) (dead-neighbour-cells cells)))

(defn tick [cells] 
  (clojure.set/union (set (reproduce cells)) (set (regulate cells))))

(->> black
     (iterate tick)
     (#(nth % 100))
     count)
;; => 4214
