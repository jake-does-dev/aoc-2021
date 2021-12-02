package org.jakedoes.dev
package day02

import day02.MovementType.{Aimed, Naive}
import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day02 {

    def submarinePosition(fileLocation: String, movementType: MovementType): (Int, Int) = {
        val moves = FileUtils.readFile(fileLocation)

        val position = movementType match {
            case Naive => resolveSubmarinePosition(moves)
            case Aimed => resolveSubmarineAimedPosition(moves)
        }

        position
    }

    def resolveSubmarinePosition(moves: List[String]): (Int, Int) = {

        val moveTuples = resolveMoveTuples(moves)

        val totalForwardDistance = moveTuples
            .filter((direction, _) => direction == "forward")
            .map((_, value) => value)
            .sum

        val totalDepth = moveTuples
            .filter((direction, _) => direction != "forward")
            .map( {
                case ("down", value) => value
                case ("up", value) => -value
            })
            .sum

        (totalForwardDistance, totalDepth)
    }

    def resolveSubmarineAimedPosition(moves: List[String]): (Int, Int) = {

        val moveTuples = resolveMoveTuples(moves)

        var aim = 0
        var totalForwardDistance = 0
        var totalDepthChange = 0

        moveTuples.foreach{
            case ("down", value) => aim += value
            case ("up", value) => aim -= value
            case ("forward", value) =>
                totalForwardDistance += value
                totalDepthChange += value * aim
        }

        (totalForwardDistance, totalDepthChange)
    }

    def resolveMoveTuples(moves: List[String]): List[(String, Int)] = {
        val moveTuples = moves
            .map(m => m.split(" "))
            .map {
                case Array(f1, f2) => (f1, f2.toInt)
            }

        moveTuples
    }
}
