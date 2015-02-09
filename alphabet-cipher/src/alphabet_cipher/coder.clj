(ns alphabet-cipher.coder
  (:require [clojure.string :as string]))

(def codebook "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz")

(defn index-a [c]
  (- (int c) (int \a))) ; CLJ
;(- (.charCodeAt c) (.charCodeAt \a))) ; CLJS

(defn codec [encode secret message]
  (let [n (count message)
        secret (take n (cycle secret))]
    (for [i (range n)
          :let [m (index-a (nth message i))
                s (index-a (nth secret i))
                k (if encode (+ s m) (- (+ m 26) s))]]
      (nth codebook k))))

(defn encode [secret message]
  (string/join (codec true secret message)))

(defn decode [secret message]
  (string/join (codec false secret message)))

