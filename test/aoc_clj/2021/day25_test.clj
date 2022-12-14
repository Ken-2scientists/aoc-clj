(ns aoc-clj.2021.day25-test
  (:require [clojure.test :refer [deftest testing is]]
            [aoc-clj.utils.grid.mapgrid :as mapgrid]
            [aoc-clj.2021.day25 :as t]))

(def day25-sample
  (mapgrid/ascii->MapGrid2D
   t/charmap
   ["v...>>.vv>"
    ".vv>>.vv.."
    ">>.>v>...v"
    ">>v>>.>.v."
    "v>v.vv.v.."
    ">.>>..v..."
    ".vv..>.>v."
    "v.v..>>v.v"
    "....v..v.>"]
   :down true))

(deftest steps-until-done-changing
  (testing "Computes the number of steps until no more changes occur"
    (is (= 58 (first (t/evolve-until-stop day25-sample))))))

;; FIXME: 2021.day25 solution is too slow
;; https://github.com/Ken-2scientists/aoc-clj/issues/9
(deftest ^:slow day25-part1-soln
  (testing "Reproduces the answer for day25, part1"
    (is (= 429 (t/day25-part1-soln)))))

