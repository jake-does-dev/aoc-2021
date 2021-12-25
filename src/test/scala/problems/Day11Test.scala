package org.jakedoes.dev
package problems

import org.scalatest.funsuite.AnyFunSuite

class Day11Test extends AnyFunSuite {

    test("multiflash example") {
        assert(Day11.octopusSteps("day11/multiFlash.txt", 1) == 9)
        assert(Day11.octopusSteps("day11/multiFlash.txt", 2) == 9)
        assert(Day11.octopusSteps("day11/multiFlash.txt", 3) == 9)
    }

    test("example input 10 steps") {
        assert(Day11.octopusSteps("day11/example.txt", 10) == 204)
    }

    test("example input 100 steps") {
        assert(Day11.octopusSteps("day11/example.txt", 100) == 1656)
    }

    test("puzzle input 100 steps") {
        assert(Day11.octopusSteps("day11/puzzleInput.txt", 100) == 1585)
    }

    test("example input synchronised") {
        assert(Day11.octopusSync("day11/example.txt") == 195)
    }

    test("puzzle input synchronised") {
        assert(Day11.octopusSync("day11/puzzleInput.txt") == 382)
    }

}
