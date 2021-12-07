package org.jakedoes.dev
package problems

import problems.Day07.{calculateFuelConstantStep, calculateFuelIncreasingStep}
import org.scalatest.funsuite.AnyFunSuite

class Day07Test extends AnyFunSuite {

    test("part one example input") {
        val fuel = Day07.calculateFuel("day07/example.txt", calculateFuelConstantStep)

        assert(fuel == 37)
    }

    test("part one puzzle input") {
        val fuel = Day07.calculateFuel("day07/puzzleInput.txt", calculateFuelConstantStep)

        assert(fuel == 335271)
    }

    test("part two example input") {
        val fuel = Day07.calculateFuel("day07/example.txt", calculateFuelIncreasingStep)

        assert(fuel == 168)
    }

    test("part two puzzle input") {
        val fuel = Day07.calculateFuel("day07/puzzleInput.txt", calculateFuelIncreasingStep)

        assert(fuel == 95851339)
    }

}
