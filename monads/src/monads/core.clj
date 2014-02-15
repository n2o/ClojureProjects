(ns monads.core
  (:use clojure.algo.monads))

;;;; Maybe Monad
(defn maybe-add-three [x y z]
  (domonad maybe-m [a x
                    b y
                    c z]
           (+ a b c)))
(maybe-add-three 1 2 3)
;; => 6
(+ 1 2 nil)
;; => NullPointerException
(maybe-add-three 1 2 nil)
;; => nil

;;;; Fizzbuzz with a Sequence Monad
;; Take a seq of numbers.
;; If the number mod 3 = 0 then print :fizz
;; If the number mod 5 = 0 then print :buzz
;;
;; Now implement the variant of the game:
;; If the number mod 15 = 0 then print either :fizz or :buzz
;;
;; (original: print :fizzbuzz, but that case is not interesting
;;
;; Now I can take a sequence of numbers and return a new sequence
;; with :fizz or :buzz replaced for a number.
;; I will us the sequence-monad to get all the possible solutions for
;; one specific input

;; Monadic function. Takes a normal value and returns a monadic value
(defn fizzbuzz-mfn [num]
  (cond
   (zero? (mod num 15)) [:fizz :buzz]
   (zero? (mod num 3)) [:fizz]
   (zero? (mod num 5)) [:buzz]
   (number? num) [num]))

;; Feeds the sequence monad with our fizzbuzz-mfn. Use monad map m-map
;; which takes a monadic function and a value and returns a monadic
;; value.
(defn fizzbuzz-m [coll]
  (domonad sequence-m [val (m-map fizzbuzz-mfn coll)]
           val))

(fizzbuzz-m [1 2 3 4 15 14 15])
;; => ((1 2 :fizz 4 :fizz 14 :fizz)
;;     (1 2 :fizz 4 :fizz 14 :buzz)
;;     (1 2 :fizz 4 :buzz 14 :fizz)
;;     (1 2 :fizz 4 :buzz 14 :buzz))
