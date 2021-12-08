package org.jakedoes.dev
package problems

import org.scalatest.funsuite.AnyFunSuite

class Day08Test extends AnyFunSuite {

    test("part one example input") {
        assert(26 == Day08.countEasyDigits("day08/example.txt"))
    }

    test("part one puzzle input") {
        assert(532 == Day08.countEasyDigits("day08/puzzleInput.txt"))
    }

    test("part two example input") {
        assert(61229 == Day08.countAllDigits("day08/example.txt"))
    }

    test("part two puzzle input") {
        assert(1011284 == Day08.countAllDigits("day08/puzzleInput.txt"))
    }

    test("count easy digits") {
        assert(2 == Day08.countEasyDigits("day08/easyDigits.txt"))
    }
}
