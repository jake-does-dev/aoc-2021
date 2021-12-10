package org.jakedoes.dev
package problems

import problems.Day09.{sumBasinSizes, sumRiskLevels}

import org.scalatest.funsuite.AnyFunSuite

class Day10Test extends AnyFunSuite {

    test("part one example input") {
        val corruptionSum = Day10.findCorruptions("day10/example.txt")

        assert(corruptionSum == 26397)
    }

    test("part one puzzle input") {
        val corruptionSum = Day10.findCorruptions("day10/puzzleInput.txt")

        assert(corruptionSum == 392097)
    }

    test("part two example input") {
        val incompleteSum = Day10.incompleteHandling("day10/example.txt")

        assert(incompleteSum == 288957)
    }

    test("part two puzzle input") {
        val incompleteSum = Day10.incompleteHandling("day10/puzzleInput.txt")

        assert(incompleteSum == 4263222782L) // not the right answer
    }
}
