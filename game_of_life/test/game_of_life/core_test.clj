(ns game-of-life.core-test
    (:use midje.sweet)
    (:require [game-of-life.core :refer :all]))
 
(fact (game-of-life.core/next 107 [1 1 0]) => \1)

(fact (game-of-life.core/line 107 [1 1 0 0]) => [\1 \1 \0 \1])