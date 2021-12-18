package org.jakedoes.dev
package problems

import problems.Day09.sumRiskLevels

import org.scalatest.funsuite.AnyFunSuite

class Day13Test extends AnyFunSuite {

    test("part one example input") {
        val (pointCount, _) = Day13.fold("day13/example.txt", "1")

        assert(pointCount == 17)
    }

    test("part one puzzle input") {
        val (pointCount, _) = Day13.fold("day13/puzzleInput.txt", "1")

        assert(pointCount == 653)
    }

    test("part two visualise") {
        val code = Day13.visualise("day13/puzzleInput.txt")

        code.foreach(slice => println(slice.mkString))
    }
}
