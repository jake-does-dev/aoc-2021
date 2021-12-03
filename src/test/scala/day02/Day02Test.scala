package org.jakedoes.dev
package day02

import helpers.MovementType.{Aimed, Naive}
import problems.Day02

import org.scalatest.funsuite.AnyFunSuite

class Day02Test extends AnyFunSuite {

    test("add forward movement") {
        val moves = List("forward 3")
        val position = Day02.resolveSubmarinePosition(moves)

        assert(position == (3, 0))
    }

    test("add multiple forward movements") {
        val moves = List("forward 3", "forward 2")
        val position = Day02.resolveSubmarinePosition(moves)

        assert(position == (5, 0))
    }

    test("down increases depth") {
        val moves = List("down 3")
        val position = Day02.resolveSubmarinePosition(moves)

        assert(position == (0, 3))
    }

    test("up decreases depth") {
        val moves = List("down 5", "up 3")
        val position = Day02.resolveSubmarinePosition(moves)

        assert(position == (0, 2))
    }

    test("course") {
        val moves = List(
            "forward 5",
            "down 2",
            "forward 11",
            "up 1",
            "forward 3",
            "down 7"
        )
        val position = Day02.resolveSubmarinePosition(moves)

        assert(position == (19, 8))
    }

    test("aiming") {
        val moves = List(
            "forward 5",
            "down 6",
            "forward 3",
            "up 1",
            "forward 2"
        )
        val position = Day02.resolveSubmarineAimedPosition(moves)

        assert(position == (10, 6*3 + 5*2))
    }

    test("part one example input)") {
        val (distance, depth) = Day02.submarinePosition("day02/example.txt", Naive)

        assert(distance * depth == 150)
    }

    test("part one puzzle input)") {
        val (distance, depth) = Day02.submarinePosition("day02/puzzleInput.txt", Naive)

        assert(distance * depth == 1882980)
    }

    test("part two example input") {
        val (distance, depth) = Day02.submarinePosition("day02/example.txt", Aimed)

        assert(distance * depth == 900)
    }

    test("part two puzzle input") {
        val (distance, depth) = Day02.submarinePosition("day02/puzzleInput.txt", Aimed)

        assert(distance * depth == 1971232560)
    }
}
