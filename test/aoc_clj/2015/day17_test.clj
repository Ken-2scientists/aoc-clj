(ns aoc-clj.2015.day17-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-clj.2015.day17 :as t]))

(def day17-sample [20 15 10 5 5])

(deftest combinations-test
  (testing "Identifies the correct combinations, allowing for dupes"
    (is (= '((20 5) (20 5) (15 10) (15 5 5)) (t/combinations 25 day17-sample)))))

(deftest min-combinations-test
  (testing "Identifies the correct minimal combinations"
    (is (= '((20 5) (20 5) (15 10)) (t/minimal-combinations 25 day17-sample)))))

;; FIXME: 2015.day17 solution is slow
;; https://github.com/Ken-2scientists/aoc-clj/issues/6
(deftest ^:slow day17-part1-soln
  (testing "Reproduces the answer for day17, part1"
    (is (= 654 (t/day17-part1-soln)))))

(deftest ^:slow day17-part2-soln
  (testing "Reproduces the answer for day17, part2"
    (is (= 57 (t/day17-part2-soln)))))