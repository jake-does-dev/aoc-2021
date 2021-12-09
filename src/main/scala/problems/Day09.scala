package org.jakedoes.dev
package problems

import utils.FileUtils

import org.jakedoes.dev.problems.Day09.extractIfExists

import scala.collection.mutable.ListBuffer

object Day09 {

    def localMinima(fileLocation: String, localMinimaInspectionFunction: (List[(Int, Int, Int)], Array[Array[Int]]) => Int) : Int = {
        val array = FileUtils.readFileArray(fileLocation)

        val localMinima = ListBuffer[(Int, Int, Int)]()

        array.indices.zipWithIndex.foreach((_, rowIdx) => {
            array(0).zipWithIndex.foreach((_, colIdx) => {
                val current = array(rowIdx)(colIdx)
                val neighbours = getNeighbours(array, rowIdx, colIdx)

                val isLocalMinima = neighbours
                    .map(n => current < n)
                    .reduce((p, q) => p && q)

                if (isLocalMinima) {
                    localMinima += Tuple3(current, rowIdx, colIdx)
                }
            })
        })

        localMinimaInspectionFunction(localMinima.toList, array)
    }

    def sumRiskLevels(localMinima: List[(Int, Int, Int)], array: Array[Array[Int]]) : Int = {
        localMinima
            .map((value, _, _) => value + 1) //risk level is 1 + height
            .sum
    }

    def sumBasinSizes(localMinima: List[(Int, Int, Int)], array: Array[Array[Int]]) : Int = {
        val counts = ListBuffer[Int]()

        var currentCount = 1
        var currentBasinNeighbours = ListBuffer[(Int, Int, Int)]()

        def resolveBasinNeighbours(array: Array[Array[Int]], rowIdx: Int, colIdx: Int, startingHeight: Int) : Unit = {
            val neighbours = List(
                extract(array, rowIdx,     colIdx - 1),
                extract(array, rowIdx,     colIdx + 1),
                extract(array, rowIdx - 1, colIdx),
                extract(array, rowIdx + 1, colIdx)
            )
                .filter((n, _, _) => n.isDefined)
                .map((n, rowIdx, colIdx) => (n.get, rowIdx, colIdx))
                .filter((n, _, _) => n != 9) //9s do not belong to basins
                .filter(neighbour => !currentBasinNeighbours.contains(neighbour))
                .filter((n, _, _) => n == startingHeight + 1)

            currentBasinNeighbours.addAll(neighbours) // track the neighbours for the current basin so we don't double count

            currentCount += neighbours.size

            neighbours.foreach((neighbourHeight, neighbourRowIdx, neighbourColIdx) => {
                resolveBasinNeighbours(array, neighbourRowIdx, neighbourColIdx, neighbourHeight)
            })
        }

        localMinima.foreach((startingHeight, rowIdx, colIdx) => {
            resolveBasinNeighbours(array, rowIdx, colIdx, startingHeight)
            counts += currentCount
            currentCount = 1
            currentBasinNeighbours = ListBuffer[(Int, Int, Int)]()
        })

        val decreasingCounts = counts.sorted.reverse

        decreasingCounts(0) * decreasingCounts(1) * decreasingCounts(2)
    }

    def getNeighbours(array: Array[Array[Int]], rowIdx: Int, colIdx: Int) : List[Int] = {
        val neighbours = ListBuffer[Int]()

        extractIfExists(neighbours, array, rowIdx,     colIdx - 1)
        extractIfExists(neighbours, array, rowIdx,     colIdx + 1)
        extractIfExists(neighbours, array, rowIdx - 1, colIdx)
        extractIfExists(neighbours, array, rowIdx + 1, colIdx)

        neighbours.toList
    }

    def extractIfExists(neighbours: ListBuffer[Int], array: Array[Array[Int]], neighbourRowIdx: Int, neighbourColId: Int) : Unit = {
        val (value, _, _) = extract(array, neighbourRowIdx, neighbourColId)

        if (value.isDefined) {
            neighbours += value.get
        }
    }

    def extract(array: Array[Array[Int]], neighbourRowIdx: Int, neighbourColIdx: Int) : (Option[Int], Int, Int) = {
        try {
            (Some(array(neighbourRowIdx)(neighbourColIdx)), neighbourRowIdx, neighbourColIdx)
        } catch {
            case e: ArrayIndexOutOfBoundsException => (None, -1, -1)
        }
    }
}
