(ns four-clojure.core)
(use 'clojure.repl)

;;;; 4Clojure

;; Flipping out #46 
(defn flip [f]
  (fn [& args]
    (apply f (reverse args))))

;; Rotate Sequence #44
(defn rotate-seq [n s]
  (let [split-pos (mod n (count s))
        [fst snd] (split-at split-pos s)]
    (concat snd fst)))

;; Reverse Interleave #43
(defn rev-interleave [input n]
  (apply map list (partition n input)))

;; Split by Type #50
(defn split-by-type [coll]
  (vals (group-by type coll)))

;; Count Occurences #55
(defn count-occ [coll]
  (let [counted (for [[k v] (group-by identity coll)]
                  [k (count v)])]
    (apply merge (map #(hash-map (first %) (second %)) counted))))

;; Find Distinct Items #56
(defn find-distinct [coll]
  (reduce
   (fn [in-vec from-coll]
     (if (some #{from-coll} in-vec)
       in-vec
       (conj in-vec from-coll)))
   [] coll))

;; Function Composition #58
(defn func-comp [& fs]
  (let [fs     (reverse fs)
        f      (first fs)
        restfs (rest fs)]
    (fn [& args]
      (reduce (fn [x f] (f x)) (apply f args) restfs))))

;; Juxtaposition #59
(defn juuuxt [& fs]
  (fn [& args]
    (vec (for [f fs] (apply f args)))))

;; Partition a Sequence #54
(defn part-seq [n coll]
  (when (<= n (count coll))
    (cons (take n coll) (part-seq n (drop n coll)))))

;; Word Sorting #70
(defn word-sort [string]
  (sort-by
   #(clojure.string/lower-case %)
   (re-seq #"\w+" string)))

;; Filter Perfect Squares #74
(defn perfect-squares [string]
  (->> (re-seq #"\d+" string)
       (map #(Integer/parseInt %))
       (filter
        (fn [i]
          (let [sq (int (Math/sqrt i))]
            (= (* sq sq) i))))
       (interpose ",")
       (apply str)
       (println)))

;; To Tree, or not to Tree #95
(defn is-tree? [tree]
  (if (= (count tree) 3)
    (let [[root left right] tree]
      (if-not (or (= false left) (= false right))
        (cond
         (and (nil? left) (nil? right)) true
         (nil?  left) (is-tree? right)
         (nil? right) (is-tree? left)
         :else (and (is-tree? left) (is-tree? right)))
        false))
    false))
(is-tree? [1 [2 [3 [4 false nil] nil] nil] nil])


;; Analyze a Tic-Tac-Toe Board #73
(def field [[:x :x :x]
            [:x :e :e]
            [:x :x :x]])

(defn win [field]
  (let [[[a b c]
         [d e f]
         [g h i]] field]
    (cond
     (and (= a b c) (not (= a :e))) a
     (and (= d e f) (not (= d :e))) d
     (and (= g h i) (not (= g :e))) g
     (and (= a d g) (not (= a :e))) a
     (and (= b e h) (not (= b :e))) b
     (and (= c f i) (not (= c :e))) c
     (and (= a e i) (not (= a :e))) a
     (and (= c e g) (not (= c :e))) c)))
(win field)

