(ns fwpd.core
  (:gen-class))

(def filename "suspects.csv")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})
(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))


(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))


(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(defn get-Names
  [records]
  (map :name records))

(defn append
  [record records]
  (conj records record))

(defn validate
  [record keymap]
  (every? identity (map record keymap)))

(defn csvify
  [records]
  (clojure.string/join "\n" (map #(clojure.string/join "," (map % vamp-keys)) records)))
;; (defn )