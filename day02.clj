(def data
  (->> (slurp "day02")
       (clojure.string/split-lines)
       (map #(re-matches #"(\d+)-(\d+) (.): (.*)" %))
       (map (fn [[_ a b c d]]
              [(Integer/parseInt a) (Integer/parseInt b) (first c) d]))))

(defn valid-1? [[a b c d]]
  (<= a (count (filter #{c} d)) b))

(count (filter valid-1? data)) ; => 586

(defn valid-2? [[a b c d]]
  (not= (= c (nth d (- a 1)))
        (= c (nth d (- b 1)))))

(count (filter valid-2? data)) ; => 352
