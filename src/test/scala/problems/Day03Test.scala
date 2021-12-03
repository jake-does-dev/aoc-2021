package org.jakedoes.dev
package problems

import org.scalatest.funsuite.AnyFunSuite

class Day03Test extends AnyFunSuite {

    test("part one example input") {
        val (gammaRate, epsilonRate) = Day03.determineRatesByBitSummation("day03/example.txt")

        assert(gammaRate * epsilonRate == 198)
    }

    test("part one puzzle input") {
        val (gammaRate, epsilonRate) = Day03.determineRatesByBitSummation("day03/puzzleInput.txt")

        assert(gammaRate * epsilonRate == 3633500)
    }

    test("part two example input") {
        val (gammaRate, epsilonRate) = Day03.determineRatesByBitFrequency("day03/example.txt")

        assert(gammaRate * epsilonRate == 230)
    }

    test("part two puzzle input") {
        val (gammaRate, epsilonRate) = Day03.determineRatesByBitFrequency("day03/puzzleInput.txt")

        assert(gammaRate * epsilonRate == 4550283)
    }
}
