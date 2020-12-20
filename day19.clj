(let [[r m] (clojure.string/split (slurp "day19") #"\n\n")]
  (def rules (clojure.string/split-lines r))
  (def messages (clojure.string/split-lines m)))

(def rules
  (into {}
        (map (fn [rule]
               (let [[id r] (clojure.string/split rule #": *")
                     rs (clojure.string/split r #" *\| *")
                     rss (map #(clojure.string/split % #" ") rs)
                     rsp (map (partial map #(case %
                                              ("\"a\"" "\"b\"") (second %)
                                              (read-string %))) rss)]
                 {(read-string id) rsp}))
             rules)))

(defn lookup [rules id depth]
  (if (> depth 15)
    "NEVER"
    (if (char? id)
      id
      (let [v (rules id)]
        (str "("
             (clojure.string/join
              "|"
              (map (fn [a]
                     (clojure.string/join
                      (map #(lookup rules % (inc depth)) a))) v))
             ")")))))

(let [pattern (re-pattern (lookup rules 0 0))]
  (count (filter (partial re-matches pattern) messages)))
;; => 126

(let [rules2 (into rules
                   [[8  '((42)    (42 8))]
                    [11 '((42 31) (42 11 31))]])
      pattern (re-pattern (lookup rules2 0 0))]
  (count (filter (partial re-matches pattern) messages)))
;; => 282
