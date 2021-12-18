package org.jakedoes.dev
package problems

import Day04.{everyBoardNeedsBingo, oneBoardNeedsBingo}

import org.scalatest.funsuite.AnyFunSuite

class Day04Test extends AnyFunSuite {

    test("part one example input") {
        var (lastBallDrawn, unmarkedSum) = Day04.playBingo("day04/example.txt", oneBoardNeedsBingo)

        assert(lastBallDrawn == 24)
        assert(unmarkedSum == 188)

        assert(lastBallDrawn * unmarkedSum == 4512)
    }

    test("part one puzzle input") {
        var (lastBallDrawn, unmarkedSum) = Day04.playBingo("day04/puzzleInput.txt", oneBoardNeedsBingo)

        assert(lastBallDrawn == 46)
        assert(unmarkedSum == 839)

        assert(lastBallDrawn * unmarkedSum == 38594)
    }

    test("part two example input") {
        var (lastBallDrawn, unmarkedSum) = Day04.playBingo("day04/example.txt", everyBoardNeedsBingo)

        assert(lastBallDrawn == 13)
        assert(unmarkedSum == 148)

        assert(lastBallDrawn * unmarkedSum == 1924)
    }

    test("part two puzzle input") {
        var (lastBallDrawn, unmarkedSum) = Day04.playBingo("day04/puzzleInput.txt", everyBoardNeedsBingo)

        assert(lastBallDrawn == 64)
        assert(unmarkedSum == 331)

        assert(lastBallDrawn * unmarkedSum == 21184)
    }
}
