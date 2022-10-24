(ns W06Final
  (:require [clojure.data.json :as json]))

(defn createListMult
  ([]
   (createListMult 1))
  ([x]
   (if (< x 15)
     (concat (cons x (vector (map #(* % x) (range 1 15)))) (createListMult (inc x)))
     (cons x (vector (map #(* % x) (range 1 15))))
     )
  )
)

(defn createListAdd
  ([]
   (createListAdd 1))
  ([x]
   (if (< x 15)
     (concat (cons x (vector (map #(+ % x) (range 1 15)))) (createListAdd (inc x)))
     (cons x (vector (map #(+ % x) (range 1 15)))))))

(defn createListSub
  ([]
   (createListSub 1))
  ([x]
   (if (< x 15)
     (concat (cons x (vector (filter #(> % -1) (map #(- x %) (range 1 15))) )) (createListSub (inc x)))
     (cons x (vector (filter #(> % -1) (map #(- x %) (range 1 15))))))))

(defn createListDiv
  ([]
   (createListDiv 1))
  ([x]
   (if (< x 15)
     (concat (cons x (vector (filter #(integer? %) (map #(/ x %) (range 1 15))))) (createListDiv (inc x)))
     (cons x (vector (filter #(integer? %) (map #(/ x %) (range 1 15))))))))
  
(defn main [] 
  (
   (spit "Xproblems.json" (with-out-str (json/pprint (apply sorted-map (createListMult)))))
   (spit "Addproblems.json" (with-out-str (json/pprint (apply sorted-map (createListAdd)))))
   (spit "Subproblems.json" (with-out-str (json/pprint (apply sorted-map (createListSub)))))
   (spit "Divproblems.json" (with-out-str (json/pprint (apply sorted-map (createListDiv)))))))