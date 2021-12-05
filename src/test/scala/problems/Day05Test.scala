package org.jakedoes.dev
package problems

import problems.Day05.{horizAndVertLines, horizVertAndDiagLines}

import org.scalatest.funsuite.AnyFunSuite

class Day05Test extends AnyFunSuite {

    test("part one example input") {
        val numOverlaps = Day05.determineNumOverlappingVents("day05/example.txt", horizAndVertLines)

        assert(numOverlaps == 5)
    }

    test("part one puzzle input") {
        val numOverlaps = Day05.determineNumOverlappingVents("day05/puzzleInput.txt", horizAndVertLines)

        assert(numOverlaps == 8060)
    }

    test("part two example input") {
        val numOverlaps = Day05.determineNumOverlappingVents("day05/example.txt", horizVertAndDiagLines)

        assert(numOverlaps == 12)
    }

    test("part two puzzle input") {
        val numOverlaps = Day05.determineNumOverlappingVents("day05/puzzleInput.txt", horizVertAndDiagLines)

        assert(numOverlaps == 8060)
    }
}
