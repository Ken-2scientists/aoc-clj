(ns aoc-clj.2018.day01-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-clj.2018.day01 :as t]))

(deftest day01-part1-soln
  (testing "Reproduces the answer for day01, part1"
    (is (= 543 (t/day01-part1-soln)))))

(deftest day01-part2-soln
  (testing "Reproduces the answer for day01, part2"
    (is (= 621 (t/day01-part2-soln)))))