package org.jakedoes.dev
package day01

import org.scalatest.funsuite.AnyFunSuite

class Day01Test extends AnyFunSuite {

    test("increasing step") {
        val steps = List(0, 1)
        val output = Day01.calculateNumIncreases(steps, 1)

        assert(output == 1)
    }

    test("decreasing step") {
        val steps = List(3, 2)
        val output = Day01.calculateNumIncreases(steps, 1)

        assert(output == 0)
    }

    test("same step") {
        val steps = List(2, 2)
        val output = Day01.calculateNumIncreases(steps, 1)

        assert(output == 0)
    }

    test("multiple steps") {
        val steps = List(2, 3, 4, 2, 6, 4)
        val output = Day01.calculateNumIncreases(steps, 1)

        assert(output == 3)
    }

    test("part one example input") {
        val numIncreases = Day01.depthSweep("day01/example.txt", 1)

        assert(numIncreases == 7)
    }

    test("part one puzzle input") {
        val numIncreases = Day01.depthSweep("day01/puzzleInput.txt", 1)

        assert(numIncreases == 1583)
    }

    test("part two example input") {
        val numIncreases = Day01.depthSweep("day01/example.txt", 3)

        assert(numIncreases == 5)
    }

    test("part two puzzle input") {
        val numIncreases = Day01.depthSweep("day01/puzzleInput.txt", 3)

        assert(numIncreases == 1627)
    }
}
