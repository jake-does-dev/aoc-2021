package org.jakedoes.dev
package problems

import utils.FileUtils

import scala.collection.SortedMap
import scala.collection.mutable.ListBuffer

object Day05 {

    def determineNumOverlappingVents(fileLocation: String, plottingFunction: (Array[(Int, Int)], ListBuffer[(Int, Int)]) => Unit): Int = {
        val inputLines = FileUtils.readFile(fileLocation)

        val coordinates = inputLines.map(line => {
            line
                .split("->")
                .map(coord => coord.trim)
                .map(coord => coord.split(",").map(x => x.toInt))
                .map(coord => Tuple2(coord(0), coord(1)))
        })

        val points = ListBuffer[(Int, Int)]()

        coordinates.foreach(pair => plottingFunction(pair, points))

        var pointsCount: Map[(Int, Int), Int] = Map()

        points.toList.foreach(point => {
            val previousCount = pointsCount.getOrElse(point, 0)
            val newCount = previousCount + 1
            pointsCount += (point, newCount)
        })

        val numOverlappingVents = pointsCount.iterator.count((_, value) => value > 1)

        numOverlappingVents
    }

    def horizAndVertLines(pair: Array[(Int, Int)], points: ListBuffer[(Int, Int)]) : Unit = {
        val (x1, y1) = pair(0)
        val (x2, y2) = pair(1)

        if (x1 == x2) {
            val start = math.min(y1, y2)
            val end = math.max(y1, y2)

            (start to end).foreach(i => points.append((x1, i)))
        } else if (y1 == y2) {
            val start = math.min(x1, x2)
            val end = math.max(x1, x2)

            (start to end).foreach(i => points.append((i, y1)))
        }
    }

    def horizVertAndDiagLines(pair: Array[(Int, Int)], points: ListBuffer[(Int, Int)]) : Unit = {
        horizAndVertLines(pair, points)

        val (x1, y1) = pair(0)
        val (x2, y2) = pair(1)

        // Check if implied function has slope of 1 or -1 to determine diagonal
        if (isDiagonalSlope(x1, y1, x2, y2)) {
            // Find function of slope, using y = mx + c
            val gradient = (y2 - y1) / (x2 - x1)
            val intercept = y1 - gradient * x1

            val xValues = (math.min(x1, x2) to math.max(x1, x2)).toList

            xValues.foreach(x => points.append((x, gradient * x + intercept)))
        }
    }

    def isDiagonalSlope(x1: Int, y1: Int, x2: Int, y2: Int) : Boolean = {
        val numerator = y2 - y1
        val denominator = x2 - x1

        if (denominator == 0) {
            false
        } else {
            math.abs(numerator / denominator) == 1
        }
    }
}
