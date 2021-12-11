package org.jakedoes.dev
package problems.day09

import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day09 {

    def localMinima(fileLocation: String, localMinimaInspectionFunction: (List[Point], Array[Array[Point]]) => Int): Int = {
        val array: Array[Array[Point]] = FileUtils.readFileArray(fileLocation)
            .zipWithIndex.map((slice, x) => {
            slice.zipWithIndex.map((value, y) => {
                Point(value, x, y, false)
            })
        })
            .map(slice => slice.toArray)

        val localMinima = ListBuffer[Point]()

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

    def sumRiskLevels(localMinima: List[Point], array: Array[Array[Point]]): Int = {
        localMinima
            .map(point => point.value + 1) //risk level is 1 + height
            .sum
    }

    def sumBasinSizes(localMinima: List[Point], array: Array[Array[Point]]): Int = {
        val counts = ListBuffer[Int]()

        var currentCount = 1

        def resolveBasinNeighbours(array: Array[Array[Point]], point: Point): Unit = {
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

    def getNeighbours(array: Array[Array[Point]], point: Point): List[Point] = {
        val neighbours = ListBuffer[Point]()

        extractIfExists(neighbours, array, point.x, point.y - 1)
        extractIfExists(neighbours, array, point.x, point.y + 1)
        extractIfExists(neighbours, array, point.x - 1, point.y)
        extractIfExists(neighbours, array, point.x + 1, point.y)

        neighbours.toList
    }

    def extractIfExists(neighbours: ListBuffer[Point], array: Array[Array[Point]], neighbourRowIdx: Int, neighbourColIdx: Int): Unit = {
        val point = extract(array, neighbourRowIdx, neighbourColIdx)

        if (point.isDefined) {
            neighbours += point.get
        }
    }

    def extract(array: Array[Array[Point]], x: Int, y: Int): Option[Point] = {
        try {
            Some(array(x)(y))
        } catch {
            case e: ArrayIndexOutOfBoundsException => None
        }
    }
}
