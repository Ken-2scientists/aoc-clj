(ns aoc-clj.utils.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn puzzle-input
  "Load a puzzle input (from the resources directory in your project) as
   a seq, with each line in the input as an element in the seq"
  [filename]
  (->> filename
       io/resource
       io/reader
       line-seq))

(defn str->vec
  "Convert a string of space- or comma-separated list of values into a
   vector of those values"
  [s]
  (read-string (str "[" s "]")))

(defn firstv
  "Treats the first element of a seq as a string representing a vector
   of values"
  [xs]
  (str->vec (first xs)))

(defn split-at-blankline
  "Splits a seq of lines (e.g. produced by `puzzle-input`) into
   a collection of chunks, each chunk with a seq of lines"
  [input]
  (let [chunks (-> (str/join "\n" input)
                   (str/split #"\n\n"))]
    (map #(str/split % #"\n") chunks)))

(defn fmap
  "Applies the function f to the values of the map m"
  [f m]
  (zipmap (keys m) (map f (vals m))))

(defn kmap
  "Applies the function f to the keys of the map m"
  [f m]
  (zipmap (map f (keys m)) (vals m)))

(defn max-val
  "Returns the key in a map m for which the value is the greatest.
   If there are multiple such keys, the first is returned"
  [m]
  (key (apply max-key val m)))

(defn min-val
  "Returns the key in a map m for which the value is the least.
   If there are multiple such keys, the first is returned"
  [m]
  (key (apply min-key val m)))

(defn without-keys
  "Returns a map with only the entries in map whose key isn't in keyseq"
  [map keyseq]
  (select-keys map (filter (complement (set keyseq)) (keys map))))

(defn invert-map
  "Swap keys and values for map m. Unlikely to do what you want if there
   isn't a 1-1 mapping"
  [m]
  (zipmap (vals m) (keys m)))

(defn keys-in
  "Returns a sequence of all key paths in a nested map `m` using DFS walk.
   
   Adapted from https://dnaeon.github.io/clojure-map-ks-paths/, viewed on
   2022-12-10"
  [m]
  (letfn [(children [node]
            (let [v (get-in m node)]
              (if (map? v)
                (map #(conj node %) (keys v))
                [])))
          (branch? [node] (-> (children node) seq boolean))]
    (->> (keys m)
         (map vector)
         (mapcat #(tree-seq branch? children %)))))


(defn rotate
  "Rotate the collection by n. Positive values of n rotate to the left,
   meaning that values are taken from the beginning of coll and moved to
   the end.  Negative values of n rotate to the right, meaning values
   are taken from the end of coll and moved to the front"
  [n coll]
  (let [size (count coll)]
    (take size (drop (mod n size) (cycle coll)))))

(defn rotations
  "Return all rotations of a collection in order. The first rotation
   is the collection itself, the second is the collection rotated by
   1, the next by 2, and so on up to n-1."
  [coll]
  (map #(rotate % coll) (range (count coll))))

(defn index-of
  "Find the index position of x within coll. Only returns the
   first match, even when there are multiple matches. Returns nil
   if x is not found"
  [x coll]
  (ffirst (filter #(= x (second %)) (map-indexed vector coll))))

(defn count-if
  "Find the count of items in coll that satisfy predicate pred"
  [coll pred]
  (count (filter pred coll)))

(defn rev-range
  "A shorthand for a reverse range, i.e. one that counts down.
   Logically equivalent to `(reverse (range end))` or
   `(reverse (range start end))`, so it follows that `end` is
   excluded while `start` is included in the seq"
  ([end]
   (range (dec end) -1 -1))
  ([start end]
   (range (dec end) (dec start) -1)))

(defn transpose
  "Transpose a vector of vectors, i.e., v[i,j] -> v[j,i]"
  [v]
  (apply mapv vector v))

(defn converge
  "Like `iterate`, returns a lazy sequence of x, (f x), (f (f x)) etc.,
   until there are no longer any changes in continued applications of the
   function to the output of the previous invocation."
  [f x]
  (concat (list x)
          (->> (iterate f x)
               (partition 2 1)
               (take-while #(not= (first %) (second %)))
               (map second))))

(defn equals?
  "Returns a predicate function of one argument that returns true when
   that argument equals `x`"
  [x]
  (partial = x))