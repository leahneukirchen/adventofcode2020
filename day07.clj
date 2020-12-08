(defn parse [l]
  (let [words (clojure.string/split l #" ")
        x (str (words 0) " " (words 1))
        y (map (fn [[n a b _]] [(Integer/parseInt n) (str a " " b)])
                (partition 4 (drop 4 words)))]
    {x y}))

(def data
  (->> (slurp "day07")
       (clojure.string/split-lines)
       (map parse)
       (into {})))

(defn trav [b]
  (or (= b "shiny gold")
      (some trav (map second (data b)))))

(dec (count (filter trav (keys d)))) ; => 278

(defn sumup [b]
  (inc (apply + (map (fn [[n x]] (* n (sumup x))) (data b)))))

(dec (sumup "shiny gold")) ; => 45157
