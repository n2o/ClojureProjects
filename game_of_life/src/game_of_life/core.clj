(ns game-of-life.core
  (:gen-class))
(use 'clojure.pprint)
(use '[clojure.string :only (join split)])


;; Converting numbers

(Integer/toString 19 2)
(Integer/parseInt "10011" 2)

;; Main Program
(def rule-number 107)

(defn next [rule-number [b1 b2 b3]]
  (def rule (cl-format nil "~8,'0b" rule-number))
  (def pos (Integer/parseInt (str b1 b2 b3) 2))
  (get rule (- 7 pos)))

(defn line [rule-number line]
  (def formatted (vec (flatten (vector (last line) line (first line)))))
  (def formlength (count formatted))
  (vec 
   (for [i (range (- formlength 2))] 
       (next rule-number (subvec formatted i (+ i 3))))))

(defn compute [rule-number number-of-lines input]
  (loop [n number-of-lines current input]
        (print current)
        (if (> n 1)
            (recur (dec n) (line rule-number current))
        )
  )
)



