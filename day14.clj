(def data
  (->> (slurp "day14")
       (clojure.string/split-lines)))

(defn part1 [mem mask code]
  (if (seq code)
    (if-let [[_ new-mask] (re-matches #"mask = (.*)" (first code))]
      (recur mem
             [(Long/parseLong (apply str (replace {\X \0} new-mask)) 2)
              (Long/parseLong (apply str (replace {\1 \0, \0 \1, \X 0} new-mask)) 2)]
             (rest code))
      (if-let [[_ addr value] (re-matches #"mem\[(\d+)\] = (\d+)" (first code))]
        (recur (assoc mem
                      (Integer/parseInt addr)
                      (bit-and (bit-or (Integer/parseInt value) (first mask))
                               (bit-not (second mask))))
               mask
               (rest code))))
    mem))

(apply + (vals (part1 {} [0 0] data))) ; => 7997531787333

(defn part2-mem [mask prefix n m]
  (if (seq mask)
    (case (first mask)
      \0 (part2-mem (rest mask)
                    (+ (if (bit-test m (dec (count mask))) 1 0)
                       (* 2 prefix))
                    n m)
      \1 (part2-mem (rest mask) (inc (* 2 prefix)) n m)
      \X (concat (part2-mem (rest mask) (* 2 prefix) n m)
                 (part2-mem (rest mask) (inc (* 2 prefix)) n m)))
    [[prefix n]]))

(defn part2 [mem mask code]
  (if (seq code)
    (if-let [[_ new-mask] (re-matches #"mask = (.*)" (first code))]
      (recur mem new-mask (rest code))
      (if-let [[_ addr value] (re-matches #"mem\[(\d+)\] = (\d+)" (first code))]
        (recur (into mem
                     (part2-mem mask 0
                                (Integer/parseInt value)
                                (Integer/parseInt addr)))
               mask
               (rest code))))
    mem))

(apply + (vals (part2 {} [0 0] data))) ; => 3564822193820
