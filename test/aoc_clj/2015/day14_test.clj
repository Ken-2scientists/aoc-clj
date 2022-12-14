(ns aoc-clj.2015.day14-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-clj.2015.day14 :as t]))

(def day14-sample
  (t/parse
   ["Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds."
    "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."]))

(deftest distance-test
  (testing "Finds the distance traveled by each reindeer after 1000 seconds"
    (is (= 1120 (t/distance-at-time 1000 (get day14-sample "Comet"))))
    (is (= 1056 (t/distance-at-time 1000 (get day14-sample "Dancer"))))))

(deftest points-test
  (testing "Finds the points scored by each reindeer after 1000 seconds"
    (is (= [312 689] (t/points-at-time 1000 day14-sample)))))

(deftest day14-part1-soln
  (testing "Reproduces the answer for day14, part1"
    (is (= 2660 (t/day14-part1-soln)))))

(deftest day14-part2-soln
  (testing "Reproduces the answer for day14, part2"
    (is (= 1256 (t/day14-part2-soln)))))