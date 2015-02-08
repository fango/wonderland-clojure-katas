(ns alphabet-cipher.coder
  (:require [clojure.string :as string]))

(def codebook "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz")
(count (distinct codebook))

(defn -secret
  "take first n chars by repeating k many times as necessary"
  [n k]
  (take n (flatten (repeat (seq k)))))

(-secret 5 "ab")

(defn index-a [c]
  (- (int c) (int \a))) ; CLJ
  ;(- (.charCodeAt c) (.charCodeAt \a))) ; CLJS

(defn -encode [yes secret message]
  (let [n (count message)
        secret (-secret n secret)]
  (for [i (range n)
      :let [m (index-a (nth message i))
            s (index-a (nth secret i))
            k (if yes (+ s m) (- (+ m 26) s))]]
        (nth codebook k))))

(defn encode [secret message]
  (string/join (-encode true secret message)))

(defn decode [secret message]
  (string/join (-encode false secret message)))

