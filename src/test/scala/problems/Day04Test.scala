package org.jakedoes.dev
package problems

import org.scalatest.funsuite.AnyFunSuite

class Day04Test extends AnyFunSuite {

    test("part one example input") {
        var (lastBallDrawn, unmarkedSum) = Day04.playBingo("day04/example.txt")

        assert(lastBallDrawn == 24)
        assert(unmarkedSum == 188)

        assert(lastBallDrawn * unmarkedSum == 4512)
    }

    test("part one puzzle input") {
        var (lastBallDrawn, unmarkedSum) = Day04.playBingo("day04/puzzleInput.txt")

        assert(lastBallDrawn == 46)
        assert(unmarkedSum == 839)

        assert(lastBallDrawn * unmarkedSum == 38594)
    }
}
