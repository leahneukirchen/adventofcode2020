(def data
  (->> "158937462"
       (map str)
       (map read-string)))

(defn split-after [e l]
  (let [[h t] (split-with (partial not= e) l)]
    [(concat h [(first t)]) (rest t)]))

(defn first-smaller [n l]
  (if (< n 1)
    (first-smaller (reduce max l) l)
    (if (some #{n} l)
      n
      (first-smaller (dec n) l))))

(defn step [data]
  (let [[c t1 t2 t3 & r] data
        d (first-smaller (dec c) r)
        [h t] (split-after d r)]
    (concat h [t1 t2 t3] t [c])))

(defn final [data]
  (let [[h t] (split-with (partial not= 1) data)]
    (concat (rest t) h)))

(->> data
     (iterate step)
     (#(nth % 101))
     final
     (apply str))
;; => "69473825"

(def data2
  (concat data (range 10 1000001)))

(defn link-up [l]
  (->> l
       (partition 2 1)
       (reduce #(apply assoc %1 %2)
               (vec (repeat (inc (count l)) nil)))
       (#(assoc %
                (last l) (first l)
                0 (first l)))))

(defn first-smaller2 [val seen]
  (if (seen val)
    (recur (mod (dec val) 1000001) seen)
    val))

(defn step2 [state]
  (let [cur (state 0)
        a (state cur)
        b (state a)
        c (state b)
        n (state c)
        cval (first-smaller2 (dec cur) #{a b c 0})
        ins (state cval)]
    (assoc state
           0 n
           cur n
           cval a
           c ins)))

(->> data2
     link-up
     (iterate step2)
     (#(nth % 10000001))
     (#(* (% 1) (% (% 1)))))
;; => 96604396189
