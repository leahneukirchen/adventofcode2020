(def cards
  (->> (clojure.string/split (slurp "day22") #"\n\n")
       (map clojure.string/split-lines)
       (map rest)
       (map (partial map read-string))))

(defn combat [[t1 & r1 :as p1] [t2 & r2 :as p2]]
  (cond
    (nil? t1) p2
    (nil? t2) p1
    :else
    (if (> t1 t2)
      (recur (concat r1 [t1 t2]) r2)
      (recur r1 (concat r2 [t2 t1])))))

(defn score [cards]
  (->> cards
       reverse
       (cons 0)
       (map-indexed vector)
       (map (partial apply *))
       (apply +)))

(score (apply combat cards))
;; => 33421

(defn recursive-combat [log [t1 & r1 :as p1] [t2 & r2 :as p2]]
  (if (log [p1 p2])
    [p1 []]
    (if (or (empty? p1) (empty? p2))
      [p1 p2]
      (let [log (conj log [p1 p2])]
        (if (and (< t1 (count p1)) (< t2 (count p2)))
          (let [[o1 o2] (recursive-combat #{} (take t1 r1) (take t2 r2))]
            (if (empty? o2)
              (recur log (concat r1 [t1 t2]) r2)
              (recur log r1 (concat r2 [t2 t1]))))
          (if (> t1 t2)
            (recur log (concat r1 [t1 t2]) r2)
            (recur log r1 (concat r2 [t2 t1]))))))))

(->> (apply recursive-combat #{} cards)
     flatten
     (remove nil?)
     score)
;; => 33651, quite slow
