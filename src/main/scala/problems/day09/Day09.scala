package org.jakedoes.dev
package problems.day09

import problems.domain.VisitingPoint
import utils.ArrayUtils.extract
import utils.{ArrayUtils, FileUtils}

import scala.collection.mutable.ListBuffer

object Day09 {

    def localMinima(fileLocation: String, localMinimaInspectionFunction: (List[VisitingPoint], Array[Array[VisitingPoint]]) => Int): Int = {
        val array = FileUtils.readFileArrayAsVisitingPoints(fileLocation)

        val localMinima = ListBuffer[VisitingPoint]()

        array.zipWithIndex.foreach((slice, rowIdx) => {
            slice.zipWithIndex.foreach((point, colIdx) => {
                val neighbours = getNeighbours(array, point)

                val isLocalMinima = neighbours
                    .map(n => point.value < n.value)
                    .reduce((p, q) => p && q)

                if (isLocalMinima) {
                    localMinima += point
                }
            })
        })

        localMinimaInspectionFunction(localMinima.toList, array)
    }

    def sumRiskLevels(localMinima: List[VisitingPoint], array: Array[Array[VisitingPoint]]): Int = {
        localMinima
            .map(point => point.value + 1) //risk level is 1 + height
            .sum
    }

    def sumBasinSizes(localMinima: List[VisitingPoint], array: Array[Array[VisitingPoint]]): Int = {
        val counts = ListBuffer[Int]()

        var currentCount = 1

        def resolveBasinNeighbours(array: Array[Array[VisitingPoint]], point: VisitingPoint): Unit = {
            val neighbours = List(
                extract(array, point.x, point.y - 1),
                extract(array, point.x, point.y + 1),
                extract(array, point.x - 1, point.y),
                extract(array, point.x + 1, point.y)
            )

            //BFS
            val filteredNeighbours = neighbours
                .filter(n => n.isDefined)
                .map(n => n.get)
                .filter(n => !n.visited)
                .filter(n => n.value != 9) //9s do not belong to basins

            //Tag the neighbours as visited before searching
            filteredNeighbours.foreach(n => {
                n.visited = true
                currentCount += 1
            })

            // Now search
            filteredNeighbours.foreach(n => {
                resolveBasinNeighbours(array, n)
            })
        }

        localMinima.foreach(point => {
            point.visited = true

            resolveBasinNeighbours(array, point)
            counts += currentCount

            currentCount = 1
        })

        val decreasingCounts = counts.sorted.reverse

        decreasingCounts(0) * decreasingCounts(1) * decreasingCounts(2)
    }

    def getNeighbours(array: Array[Array[VisitingPoint]], point: VisitingPoint): List[VisitingPoint] = {
        val neighbours = ListBuffer[VisitingPoint]()

        extractIfExists(neighbours, array, point.x, point.y - 1)
        extractIfExists(neighbours, array, point.x, point.y + 1)
        extractIfExists(neighbours, array, point.x - 1, point.y)
        extractIfExists(neighbours, array, point.x + 1, point.y)

        neighbours.toList
    }

    def extractIfExists(neighbours: ListBuffer[VisitingPoint], array: Array[Array[VisitingPoint]], neighbourRowIdx: Int, neighbourColIdx: Int): Unit = {
        val point = extract(array, neighbourRowIdx, neighbourColIdx)

        if (point.isDefined) {
            neighbours += point.get
        }
    }
}
