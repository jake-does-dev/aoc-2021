package org.jakedoes.dev
package problems

import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day03 {

    def determineRatesByBitSummation(fileLocation: String) : (Int, Int) = {
        val lines = FileUtils.readFile(fileLocation)

        val bitLists = resolveBitLists(lines)

        val bitSum : List[Int] = calculateBitSum(bitLists)

        var epsilonRateBuffer = ListBuffer[String]()
        var gammaRateBuffer = ListBuffer[String]()

        val halfCount = bitLists.length / 2

        bitSum.foreach(x => {
            if (x > halfCount) {
                epsilonRateBuffer += "1"
                gammaRateBuffer += "0"
            } else {
                epsilonRateBuffer += "0"
                gammaRateBuffer += "1"
            }
        })

        val epsilonRate = epsilonRateBuffer.reduce((x, y) => x.concat(y))
        val gammaRate = gammaRateBuffer.reduce((x, y) => x.concat(y))

        (Integer.parseInt(epsilonRate, 2), Integer.parseInt(gammaRate, 2))
    }

    def resolveBitLists(lines: List[String]) : List[List[Int]] = {
        lines
            .map(x => x.split(""))
            .map(x => {
                x.map(y => y.toInt)
            })
            .map(x => x.toList)
    }

    def calculateBitSum(bitLists: List[List[Int]]) : List[Int] = {
        bitLists
            .reduce((x, y) => {
                x.zip(y).map((a, b) => a + b)
            })
    }

    def determineRatesByBitFrequency(fileLocation: String) : (Int, Int) = {
        val lines = FileUtils.readFile(fileLocation)

        val bitLists = resolveBitLists(lines)

        val oxygenGeneratorRating = determineRating(bitLists, oxygenRatingComparer)
            .map(x => x.toString)
            .reduce((x, y) => x.concat(y))
        val carbonDioxideGeneratorRating = determineRating(bitLists, carbonDioxideRatingComparer)
            .map(x => x.toString)
            .reduce((x, y) => x.concat(y))

        (Integer.parseInt(oxygenGeneratorRating, 2), Integer.parseInt(carbonDioxideGeneratorRating, 2))
    }

    def oxygenRatingComparer(onesCount: Int, zerosCount: Int) : Boolean = {
        onesCount >= zerosCount
    }

    def carbonDioxideRatingComparer(onesCount: Int, zerosCount: Int) : Boolean = {
        onesCount < zerosCount
    }

    def determineRating(bitLists: List[List[Int]], comparer: (Int, Int) => Boolean) : List[Int] = {
        var bitIdx = 0

        var filteredLists = bitLists

        while (filteredLists.length != 1) {
            val onesCount = calculateBitSum(filteredLists)
            val onesCountAtPosition = onesCount(bitIdx)
            val zerosCountAtPosition = filteredLists.length - onesCountAtPosition

            if (comparer(onesCountAtPosition, zerosCountAtPosition)) {
                filteredLists = filteredLists.filter(
                    x => x(bitIdx) == 1
                )

            } else {
                filteredLists = filteredLists.filter(
                    x => x(bitIdx) == 0
                )
            }

            bitIdx += 1
        }

        filteredLists.head
    }
}
