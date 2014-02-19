# Simple Monads in Clojure


## Description

This project shows the usage of the two monads Sequence- and Maybe-Monad.

Here are two short examples:

1. Make the Addition of 3 numbers nil-safe
2. Get all solutions for a modified Fizzbuzz-Game automatically packed into a list
3. What about lifting?

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


# What about Lifting?
There is a way to lift a normal function to work with a monad. This process is called *lift*. It takes a normal function, which would take a number of values and returns a value and converts it to a function, which takes a fixed number of monadic values and returns a monadic value. 

It is easier than it sounds. Let's take the example from above and lift the normal addition. Therefore we need to fix the number of arguments which our new function needs. 
So let's say we want to get a nil-safe addition of 3 numbers by using the maybe-monad and the function `m-lift`, we just need to write:

```clojure
(def nil-safe-add 
  (with-monad maybe-m (m-lift 3 +)))
```

where `3` is the number of arguments the nil-safe-add will take and the function `+` which needs to be lifted.

This is short hand for `maybe-add-three` defined above. The new function `nil-safe-add` does exactly the same calculation.

It now can be used as known:

```clojure
(nil-safe-add 1 2 3)
;; => 6

(nil-safe-add 1 2 nil)
;; => nil

(+ 1 2 nil)
;; => error
```