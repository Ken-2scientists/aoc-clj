(ns aoc-clj.2015.day19
  (:require [clojure.string :as str]
            [aoc-clj.utils.core :as u]))

(defn parse-replacement
  [line]
  (str/split line #" => "))

(defn parse
  [input]
  (let [[replacements molecule] (str/split (str/join "\n" input) #"\n\n")]
    {:replacements (mapv parse-replacement (str/split replacements #"\n"))
     :molecule molecule}))

(def day19-input (parse (u/puzzle-input "2015/day19-input.txt")))

(defn indicesOf
  [s pattern]
  (loop [i 0 idxs []]
    (if (neg? i)
      idxs
      (let [nxt (.indexOf s pattern i)]
        (if (neg? nxt)
          (recur nxt idxs)
          (recur (inc nxt) (conj idxs nxt)))))))

(defn replacement
  [new s len idx]
  (str (subs s 0 idx) new (subs s (+ idx len))))

(defn single-replacements
  [[old new] s]
  (map (partial replacement new s (count old)) (indicesOf s old)))

(defn distinct-molecules
  [{:keys [replacements molecule]}]
  (distinct (mapcat #(single-replacements % molecule) replacements)))

(defn ordered-replacements
  [{:keys [replacements] :as input}]
  (assoc input :replacements (map reverse (sort-by (comp count second) > replacements))))


(defn de-fabricate
  [{:keys [replacements molecule] :as input}]
  (assoc input :molecule
         (if (= "e" molecule)
           "e"
           (first (mapcat #(single-replacements % molecule) replacements)))))

(defn fabrication-steps
  [input]
  (map :molecule
       (take-while #(not= "e" (:molecule %))
                   (iterate de-fabricate (ordered-replacements input)))))

(defn day19-part1-soln
  []
  (count (distinct-molecules day19-input)))

(defn day19-part2-soln
  []
  (count (fabrication-steps day19-input)))