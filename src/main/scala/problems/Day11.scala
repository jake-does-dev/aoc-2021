package org.jakedoes.dev
package problems

import problems.domain.FlashingPoint
import utils.ArrayUtils.extractIfExists
import utils.FileUtils.ofFlashingPoint
import utils.{ArrayUtils, FileUtils}

import scala.collection.mutable.ListBuffer

object Day11 {

    def octopusSteps(fileLocation: String, steps: Int) : Int = {
        val array = FileUtils.readFileArray(fileLocation, ofFlashingPoint)
        var flashCount = 0

        (0 until steps).foreach(_ => {
            increaseEnergyLevels(array)

            flashCount += performFlashing(array)

            resetFlashedPoints(array)
        })

        flashCount
    }

    def octopusSync(fileLocation: String) : Int = {
        var step = 0
        var synchronised = false

        val array = FileUtils.readFileArray(fileLocation, ofFlashingPoint)

        while(!synchronised) {
            step += 1

            increaseEnergyLevels(array)

            performFlashing(array)

            synchronised = haveAllOctopusFlashed(array)

            resetFlashedPoints(array)
        }

        step
    }

    def increaseEnergyLevels(array: Array[Array[FlashingPoint[Int]]]) : Unit = {
        array.foreach(slice => slice.foreach(point => point.value += 1))
    }

    def performFlashing(array: Array[Array[FlashingPoint[Int]]]) : Int = {
        var flashCount = 0

        var pointsToFlash = needToFlash(array)
        while(pointsToFlash.nonEmpty) {
            pointsToFlash.foreach(point => {
                val neighbours = ListBuffer[FlashingPoint[Int]]()

                extractIfExists(neighbours, array, point.x - 1, point.y - 1)
                extractIfExists(neighbours, array, point.x, point.y - 1)
                extractIfExists(neighbours, array, point.x + 1, point.y - 1)
                extractIfExists(neighbours, array, point.x - 1, point.y)
                extractIfExists(neighbours, array, point.x + 1, point.y)
                extractIfExists(neighbours, array, point.x - 1, point.y + 1)
                extractIfExists(neighbours, array, point.x, point.y + 1)
                extractIfExists(neighbours, array, point.x + 1, point.y + 1)

                point.flashed = true
                flashCount += 1

                neighbours.foreach(point => point.value += 1)
            })

            pointsToFlash = needToFlash(array)
        }

        flashCount
    }

    def needToFlash(array: Array[Array[FlashingPoint[Int]]]) : List[FlashingPoint[Int]] = {
        val pointsToFlash = ListBuffer[FlashingPoint[Int]]()

        array.foreach(slice => slice.foreach(point => {
            if (point.value >= 10 && !point.flashed) {
                pointsToFlash += point
            }
        }))

        pointsToFlash.toList
    }

    def resetFlashedPoints(array: Array[Array[FlashingPoint[Int]]]) : Unit = {
        array.foreach(slice => slice.foreach(point => {
            if (point.flashed) {
                point.value = 0
                point.flashed = false
            }
        }))
    }

    def haveAllOctopusFlashed(array: Array[Array[FlashingPoint[Int]]]) : Boolean = {
        var allFlashed = true

        array.foreach(slice => slice.foreach(point => allFlashed = allFlashed && point.flashed))

        allFlashed
    }
}
