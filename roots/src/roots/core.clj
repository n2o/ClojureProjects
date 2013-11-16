(ns roots.core
  (:gen-class))

(defn interval-sqrt [n eps]
	(loop [a 0 b n]
		(def mid (/ (+ a b) 2))
		(if (< (- b a) eps)
			a
			(if (< (* mid mid) n)
				(recur mid b)
				(recur a mid)))))