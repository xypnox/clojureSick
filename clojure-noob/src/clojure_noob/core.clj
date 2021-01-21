(ns clojure-noob.core
  (:gen-class))

(defn add100
  [n]
  (+ n 100))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (str "I am a little teapot" (add100 9))))

(defn dec-maker
  [num]
  (fn [x] (- x num)))

(defn mapset
  "Map but it returns set"
  [f [& args]]
  (into #{} (map f args)))

(defn bettermapset
  "Map but returns a set, doesn't use map internally"
  [f [& args]]
  (loop [left (vec args)
         processed #{}]
      (if (empty? left)
          processed
          (let [[first & rem] left]
             (recur rem (conj processed (f first)))))))

;; (def dec9 (dec-maker 9))
;; (println (dec9 10))

(println (bettermapset inc [1 1 2 2]))
