package org.jakedoes.dev
package day02

import utils.FileUtils

object Day02 {

    def submarinePosition(fileLocation: String): (Int, Int) = {

        val moves = FileUtils.readFile(fileLocation)

        val position = resolveSubmarinePosition(moves)

        position
    }

    def resolveSubmarinePosition(moves: List[String]): (Int, Int) = {

        val moveTuples = moves
            .map(m => m.split(" "))
            .map {
                case Array(f1, f2) => (f1, f2.toInt)
            }

        val horizontalMoves : List[(String, Int)] = moveTuples
            .filter((direction, _) => direction == "forward")

        val verticalMoves : List[(String, Int)] = moveTuples
            .filter((direction, _) => direction != "forward")

        val totalHorizontalDistance = horizontalMoves
            .map((_, value) => value)
            .sum

        val totalDepth = verticalMoves
            .map {
                case ("down", value) => value
                case ("up", value) => -value
            }
            .sum

        (totalHorizontalDistance, totalDepth)
    }
}
