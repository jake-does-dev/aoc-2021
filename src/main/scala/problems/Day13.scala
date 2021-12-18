package org.jakedoes.dev
package problems

import problems.Day13.fold
import problems.domain.Point
import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day13 {

    def fold(fileLocation: String, numFolds: String) : (Int, List[Point[Char]]) = {

        val lines = FileUtils.readFile(fileLocation)

        var points = lines.filter(l => l.contains(",")).map(l => {
            val parts = l.split(",")
            Point('#', parts(0).toInt, parts(1).toInt)
        })

        val folds = lines.filter(l => l.contains("=")).map(l => {
            val parts = l.split("=")

            (parts(0).charAt(parts(0).length - 1), parts(1).toInt)
        })

        val countsAfterFold = ListBuffer[Int]()
        countsAfterFold += points.size

        (0 until loopEnd(folds.size, numFolds)).foreach(i => {
            val fold = folds(i)

            val foldAxis = fold._1
            val foldValue = fold._2

            points.filter(point => {
                foldAxis match {
                    case 'x' =>
                        point.x > foldValue
                    case 'y' =>
                        point.y > foldValue
                }
            }).foreach(point => {
                foldAxis match {
                    case 'x' =>
                        point.x -= (point.x - foldValue) * 2

                    case 'y' =>
                        point.y -= (point.y - foldValue) * 2
                }
            })

            points = points.distinct
            countsAfterFold += points.size
        })

        (countsAfterFold.last, points)
    }

    def visualise(fileLocation: String) : Array[Array[Char]] = {
        val (_, points) = fold(fileLocation, "all")

        val maxWidth = points.map(p => p.x).max
        val maxDepth = points.map(p => p.y).max

        val array = Array.fill[Char](maxWidth + 1, maxDepth + 1) {'.'}

        points.foreach(p => {
            array(p.x)(p.y) = p.value
        })

        val transposed = array.transpose

        transposed
    }

    def loopEnd(totalFoldsFound: Int, numFolds: String) : Int = {
        if (numFolds == "all") {
            totalFoldsFound
        } else {
            numFolds.toInt
        }
    }
}
