package org.jakedoes.dev
package problems

import org.scalatest.funsuite.AnyFunSuite

class Day06Test extends AnyFunSuite {

    test("part one example input fewer days") {
        val numFish = Day06.simulateLanternfish("day06/example.txt", 18)

        assert(numFish == 26)
    }

    test("part one example input") {
        val numFish = Day06.simulateLanternfish("day06/example.txt", 80)

        assert(numFish == 5934)
    }

    test("part one puzzle input") {
        val numFish = Day06.simulateLanternfish("day06/puzzleInput.txt", 80)

        assert(numFish == 391671)
    }

    test("part two example input") {
        val numFish = Day06.simulateLanternfish("day06/example.txt", 256)

        assert(numFish == 26984457539L)
    }

    test("part two puzzle input") {
        val numFish = Day06.simulateLanternfish("day06/puzzleInput.txt", 256)

        assert(numFish == 1754000560399L)
    }
}
