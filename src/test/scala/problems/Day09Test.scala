package org.jakedoes.dev
package problems

import problems.Day09.{sumBasinSizes, sumRiskLevels}

import org.scalatest.funsuite.AnyFunSuite

class Day09Test extends AnyFunSuite {

    test("part one example input") {
        val numLocalMinima = Day09.localMinima("day09/example.txt", sumRiskLevels)

        assert(numLocalMinima == 15)
    }

    test("part one puzzle input") {
        val numLocalMinima = Day09.localMinima("day09/puzzleInput.txt", sumRiskLevels)

        assert(numLocalMinima == 468)
    }

    test("part two example input") {
        val numLocalMinima = Day09.localMinima("day09/example.txt", sumBasinSizes)

        assert(numLocalMinima == 1134)
    }

    test("part two puzzle input") {
        val numLocalMinima = Day09.localMinima("day09/puzzleInput.txt", sumBasinSizes)

        assert(numLocalMinima == 468) // not the right answer
    }
}
