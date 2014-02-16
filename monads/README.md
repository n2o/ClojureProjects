# Simple Monads in Clojure


## Description

This project shows the usage of the two monads Sequence- and Maybe-Monad.

Here are two short examples:

1. Make the Addition of 3 numbers nil-safe
2. Get all solutions for a modified Fizzbuzz-Game automatically packed into a list

### Preparation

1. Include algo.monads to your [project.clj](project.clj).
2. Include algo.monads to your core.clj:

```clojure
(ns monads.core
  (:use clojure.algo.monads))
```

### Maybe Monad
#### Nil-Safe adding numbers
The aim is to make the addition of three numbers nil-safe. With the maybe monad it is really easy and can be done within a few lines of code:

```clojure
(defn maybe-add-three [x y z]
  (domonad maybe-m [a x
                    b y
                    c z]
           (+ a b c)))
```

Now we can use the function in the REPL and get the following output:

```clojure
(maybe-add-three 1 2 3)
;; => 6

(maybe-add-three 1 2 nil)
;; => nil

(+ 1 2 nil)
;; => NullPointerException
```

That's really easy and cool! Monads safe many lines of code, even in this small example.

### Sequence-Monad
#### Modified Fizzbuzz with all solutions
The rules:

Take a list of numbers.
* If the number mod 3 = 0 then print :fizz
* If the number mod 5 = 0 then print :buzz

Now implement the variant of the game:
* If the number mod 15 = 0 then print either :fizz or :buzz

(original: print :fizzbuzz, but that case is not interesting

Now we can take a sequence of numbers and return a new sequence with :fizz or :buzz replaced for a number.

We will use the sequence-monad to get all the possible solutions for one specific input:

```clojure
;; Monadic function. Takes a normal value and returns a monadic value
(defn fizzbuzz-mfn [num]
  (cond
   (zero? (mod num 15)) [:fizz :buzz]
   (zero? (mod num 3)) [:fizz]
   (zero? (mod num 5)) [:buzz]
   (number? num) [num]))
```

Feeds the sequence monad with our fizzbuzz-mfn. Use monad map m-map which takes a monadic function and a value and returns a monadic value.

```clojure
(defn fizzbuzz-m [coll]
  (domonad sequence-m [val (m-map fizzbuzz-mfn coll)]
           val))
```

So we have a choicepoint with the two 15s. They can be either :fizz or :buzz. The sequence monad gives us all solutions concatenated into a list of lists. When you call the function `fizzbuzz-m` in the REPL, the sample output will be:

```clojure
(fizzbuzz-m [1 2 3 4 15 14 15])
;; => ((1 2 :fizz 4 :fizz 14 :fizz)
;;     (1 2 :fizz 4 :fizz 14 :buzz)
;;     (1 2 :fizz 4 :buzz 14 :fizz)
;;     (1 2 :fizz 4 :buzz 14 :buzz))
```

That's it! The whole code can be found as usual under [src/monads/core.clj](src/monads/core.clj).
