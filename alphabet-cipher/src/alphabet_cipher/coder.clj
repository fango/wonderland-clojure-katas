(ns alphabet-cipher.coder
  (:require [clojure.string :as string]))

(def codebook "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz")
(count (distinct codebook))

(defn take-rep
  "take first n chars by repeating k many times as necessary"
  [n k]
  (take n (flatten (repeat (seq k)))))

(take-rep 5 "ab")

(defn index-a [c]
  (- (int c) (int \a))) ; CLJ
;(- (.charCodeAt c) (.charCodeAt \a))) ; CLJS

(defn codec [encode secret message]
  (let [n (count message)
        secret (take-rep n secret)]
    (for [i (range n)
          :let [m (index-a (nth message i))
                s (index-a (nth secret i))
                k (if encode (+ s m) (- (+ m 26) s))]]
      (nth codebook k))))

(defn encode [secret message]
  (string/join (codec true secret message)))

(defn decode [secret message]
  (string/join (codec false secret message)))

