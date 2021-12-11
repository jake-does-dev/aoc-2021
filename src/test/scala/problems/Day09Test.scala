package org.jakedoes.dev
package problems

import problems.day09.Day09
import problems.day09.Day09.{sumBasinSizes, sumRiskLevels}

import org.scalatest.funsuite.AnyFunSuite

class Day09Test extends AnyFunSuite {

    test("part one example input") {
        val riskLevels = Day09.localMinima("day09/example.txt", sumRiskLevels)

        assert(riskLevels == 15)
    }

    test("part one puzzle input") {
        val riskLevels = Day09.localMinima("day09/puzzleInput.txt", sumRiskLevels)

        assert(riskLevels == 468)
    }

    test("part two example input") {
        val basins = Day09.localMinima("day09/example.txt", sumBasinSizes)

        assert(basins == 1134)
    }

    test("part two puzzle input") {
        val basins = Day09.localMinima("day09/puzzleInput.txt", sumBasinSizes)

        assert(basins == 1280496)
    }
}
