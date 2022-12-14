(ns aoc-clj.2015.day16-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-clj.2015.day16 :as t]))

(deftest day16-part1-soln
  (testing "Reproduces the answer for day16, part1"
    (is (= 213 (t/day16-part1-soln)))))

(deftest day16-part2-soln
  (testing "Reproduces the answer for day16, part2"
    (is (= 323 (t/day16-part2-soln)))))