(ns aoc-clj.2015.day15
  (:require [clojure.string :as str]
            [aoc-clj.utils.core :as u]))

(defn parse-line
  [line]
  (let [[ingredient props] (str/split line #": ")
        pairs (map str/trim (str/split props #","))
        data (mapv (fn [x]
                     (let [[_ v] (str/split x #" ")]
                       (read-string v))) pairs)]
    [ingredient data]))

(defn parse
  [input]
  (into {} (mapv parse-line input)))

(def day15-input (parse (u/puzzle-input "2015/day15-input.txt")))
(def max-teaspoons 100)

(defn score
  [props amounts]
  (let [foo (map (fn [[_ v1] v2]
                   (mapv (partial * v2) v1)) props amounts)
        bar (apply (partial map +) foo)
        baz (map #(if (neg? %) 0 %) bar)]
    (reduce * (take 4 baz))))

(defn score-with-500cal
  [props amounts]
  (let [foo (map (fn [[_ v1] v2]
                   (mapv (partial * v2) v1)) props amounts)
        bar (apply (partial map +) foo)
        baz (map #(if (neg? %) 0 %) bar)]
    (if (not= 500 (nth baz 4))
      0
      (reduce * (take 4 baz)))))

(defn all-options
  [total vars]
  (if (= 2 vars)
    (if (zero? total)
      [(repeat vars 0)]
      (map #(list (- total %) %) (range (inc total))))
    (mapcat (fn [x]
              (mapv #(concat [(- total x)] %)
                    (all-options x (dec vars))))
            (range (inc total)))))

(defn find-max-score
  [score-fn input]
  (let [vars (count (keys input))
        opts (all-options max-teaspoons vars)]
    (apply max-key (partial score-fn input) opts)))

(defn day15-part1-soln
  []
  (let [best-combo (find-max-score score day15-input)]
    (score day15-input best-combo)))

(defn day15-part2-soln
  []
  (let [best-combo (find-max-score score-with-500cal day15-input)]
    (score day15-input best-combo)))