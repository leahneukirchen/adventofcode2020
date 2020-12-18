(let [data (slurp "day16")
      [a b c] (clojure.string/split data #"\n\n")]
  (def ranges a)
  (def mine b)
  (def nearby c))

(def line-ranges
  (->> ranges
       clojure.string/split-lines
       (map (fn [line]
              (->> line
                   (re-seq #"(\d+)-(\d+)")
                   (map (fn [[_ f t]]
                          (range (Integer/parseInt f)
                                 (inc (Integer/parseInt t)))))
                   flatten)))))

(def all-in-ranges
  (->> line-ranges
       flatten
       set))

(def nearby-tickets
  (->> nearby
       clojure.string/split-lines
       rest
       (map (fn [s] (clojure.string/split s #",")))
       (map (partial map #(Integer/parseInt %)))))

(reduce + (remove all-in-range (flatten nearby-tickets))) ; => 24021

(def my-ticket
  (->> mine
       clojure.string/split-lines
       second
       (#(clojure.string/split % #","))
       (map #(Integer/parseInt %))
       vec))

(def valid-tickets
  (->> nearby-tickets
       (filter (fn [t] (empty? (remove all-in-range t) )))))

(defn valid-positions [line-range]
  (let [s (set line-range)]
    (filter (fn [i]
              (every? (fn [ticket]
                        (s (nth ticket i)))
                      valid-tickets))
            (range (count line-ranges)))))

(->> line-ranges
     (map valid-positions)
     (sort-by count)
     (cons ())
     (partition 2 1) 
     (map (fn [[x y]] (first (remove (set x) y))))
    
     (drop 6)  ;; ???
     (take 6)

     (map my-ticket)
     (apply *)) ; => 1289178686687
