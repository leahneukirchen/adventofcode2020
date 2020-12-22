(def data
  (->> (slurp "day21")
       clojure.string/split-lines
       (map #(clojure.string/split % #"\(contains "))
       (map (fn [[f a]]
              [(clojure.string/split f #" ")
               (clojure.string/split a #"[,)]+ *")]))))

(def food
  (->> data
       (map (fn [[f a]]
              (for [sa a]
                {sa [(set f)]})))
       (apply concat)
       (apply merge-with concat)))

(def recipes
  (->> data
       (map first)
       flatten))

(defn find-allergens [confirmed]
  (->> (for [[k v] food
             :when (not (confirmed k))
             ]
         (let [i (reduce clojure.set/intersection v)]
           (if (= 1 (count i))
             [k (first i)]
             (let [d (clojure.set/difference i (set (vals confirmed)))]
               (if (= 1 (count d))
                 [k (first d)]
                 nil)))))
       (remove nil?)
       (into {})
       (merge confirmed)))

(defn fix [l]
  (reduce #(if (= %1 %2) (reduced %1) %2) l))

(def allergens
  (->> {}
       (iterate find-allergens)
       fix))

(count
 (filter (->> allergens vals set complement) recipes))
;; => 2627

(->> allergens
     keys
     sort
     (map allergens)
     (clojure.string/join ","))
;; => "hn,dgsdtj,kpksf,sjcvsr,bstzgn,kmmqmv,vkdxfj,bsfqgb"
