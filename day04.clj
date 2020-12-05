(defn parse [s]
  (into {} (map #(vec (drop 1 %)) (re-seq #"(\w+):(\S+)" s))))

(def data
  (map parse (clojure.string/split (slurp "day04") #"\n\n")))

(defn valid-keys? [r]
  (empty? (clojure.set/difference
           #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"}
           (set (keys r)))))

(defn valid-height? [s]
  (if-let [[_ n u] (re-matches #"([0-9]+)(cm|in)" s)]
    (or (and (= u "cm") (<= 150 (Integer/parseInt n) 193))
        (and (= u "in") (<= 59 (Integer/parseInt n) 76)))
    false))

(defn valid? [r]
  (and (valid-keys? r)
       (<= 1920 (Integer/parseInt (r "byr")) 2002)
       (<= 2010 (Integer/parseInt (r "iyr")) 2020)
       (<= 2020 (Integer/parseInt (r "eyr")) 2030)
       (valid-height? (r "hgt"))
       (re-matches #"#[0-9a-f]{6}" (r "hcl"))
       (#{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} (r "ecl"))
       (re-matches #"[0-9]{9}" (r "pid"))))

(->> data
     (filter valid-keys?)
     (count)) ; => 228

(->> data
     (filter valid?)
     (count)) ; => 175
