(def data [5 2 8 16 18 0 1])

(defn doit [n]
  (letfn [(rec [lasta lastb last i]
            (if (<= i n)
              (let [new-last (if (and (lasta last) (lastb last))
                               (- (lastb last) (lasta last))
                               0)]
                (recur (assoc lasta new-last (lastb new-last))
                       (assoc lastb new-last i)
                       new-last
                       (inc i)))
              last))]
    (rec {}
         (into {} (map-indexed #(vector %2 %1) data))
         (last data)
         (count data))))

(doit 2019)  ; => 517
(doit 30000000)  ; => 3303657 slow
