(ns blatt01.core
  (:gen-class))
  (use '[clojure.math.numeric-tower])
  (use '[clojure.math.combinatorics])

;; Aufgabe 1.1a
(defn seq1a [] 
  (range -100 101 -1))

;; 1.1b
(defn seq1b []
  (filter even? (range 1001)))
(defn seq1bb []
  (range 0 1001 2))

;; 1.1c
(defn seq1c []
  (filter (fn [i] (integer? (sqrt i))) (range 1001)))
(def seq1cc 
     (map (fn [x] (* x x)) (range 32)))
(def seq1ccc
     (take-while #(< % 1001) (map #(* % %) (iterate inc 0))))
;; #(+ 3 %) ist das gleiche wie (fn [x] (+ 3 x))

;; 1.1d
(defn seq1d []
  (map (fn [i] [i, (inc (* i i))]) (range 1001)))
(def seq1dd
     (for [y (range 1 1000)] [y (inc #(* % %))]))

;; 1.1e
(defn gettuples []
  (filter (fn [x] (apply < x)) (selections (range 10) 3)))
(defn seq1e []
  (map (fn [x] (concat x (reverse (take 2 x)))) (gettuples)))
(def seq1ee 
     (for [x (range 10), y (range 10), z (range 10) :when (< x y z)] [x y z y x]))

;; 1.2a
(defn experiment1 []
  (def doors (shuffle [:ziege :ziege :auto]))
  (def choice (rand-int 3))
  (= (doors choice) :ziege))
(defn experiment11 []
  (not= (rand-int 3) (rand-int 3)))

;; 1.2b
(defn experiment2 []
  (def doors (shuffle [:ziege :ziege :auto]))
  (def choice (rand-int 3))
  (if (> (rand 1) 0.5)
    (= (doors choice) :ziege)
    (= (doors choice) :auto)
  )
)
(defn experiment2 [] (= 1 (rand-int 2)))

;; 1.2c
(defn run [n f]
  (loop [n n win 0 loose 0]
    (if (zero? n) 
      (str "{false " loose ", true " win "}")
      (if (f) 
        (recur (dec n) (inc win) loose)
        (recur (dec n) win (inc loose))
      )
    )
  )
)
(defn runn [n f]
  (frequencies (repeatedly n f)))

;; 1.3a
(defn proper-divisors [n]
  "Returns a list of proper divisors of n"
  (filter #(zero? (mod n %)) (range 1 (+ 1 (/ n 2)))))
(defn perfect? [n]
  (= (apply + (proper-divisors n)) n))

;; 1.3b
(defn next-perfect [n]
  (loop [n (inc n)]
    (if (perfect? n)
      (str "Next perfect number: " n)
      (recur (inc n))
    )
  )
)
(defn next-perfectt [n]
  (first (filter perfect? (iterate inc n))))

;; 1.4a
(def fib-seq
  (lazy-cat [0 1] (map +' (rest fib-seq) fib-seq)))
(defn fib [n]
  (take n fib-seq))

;; 1.5a
(defn remove-duplicates [x]
  (distinct x))





;; Für Shortcuts
(defn remove-once [pred coll]
  "Removes one item from a sequence"
  ((fn inner [coll]
     (lazy-seq
      (when-let [[x & xs] (seq coll)]
        (if (pred x)
          xs
          (cons x (inner xs))))))
   coll))