(defn parse [l]
  (let [words (clojure.string/split l #" ")]
    [(first words) (Integer/parseInt (second words))]))

(def data
  (->> (slurp "day08")
       (clojure.string/split-lines)
       (map parse)
       vec))

(defn run [p ip acc seen]
  (if (or (seen ip) (>= ip (count p)))
    [ip acc]
    (let [seen (conj seen ip)
          [op arg] (p ip)]
      (case op
        "nop" (recur p (inc ip)   acc         seen)
        "acc" (recur p (inc ip)   (+ acc arg) seen)
        "jmp" (recur p (+ ip arg) acc         seen)))))

(second (run data 0 0 #{})) ; => 1451

(some (fn [n]
        (let [[ip acc] (run (update-in data [n 0]
                                       {"nop" "jmp" ,"jmp" "nop", "acc" "acc"})
                         0 0 #{})]
          (if (= ip (count data))
            acc)))
      (range (count data))) ; => 1160
