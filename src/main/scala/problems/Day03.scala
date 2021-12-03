package org.jakedoes.dev
package problems

import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day03 {

    def determineRatesByBitSummation(fileLocation: String) : (Int, Int) = {
        val lines = FileUtils.readFile(fileLocation)

        val bitLists = resolveBitLists(lines)

        val bitSum : List[Int] = calculateBitSum(bitLists)

        var (epsilonRate, gammaRate) = ("", "")

        bitSum.foreach(x => {
            if (x >= bitLists.length / 2) {
                epsilonRate += "1"
                gammaRate += "0"
            } else {
                epsilonRate += "0"
                gammaRate += "1"
            }
        })

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
                x.zip(y).map((a, b) => a + b) //pairwise summation acts as element wise operation on two Lists
            })
    }

    def determineRatesByBitFrequency(fileLocation: String) : (Int, Int) = {
        val lines = FileUtils.readFile(fileLocation)

        val bitLists = resolveBitLists(lines)

        val (oxygenGeneratorRating, carbonDioxideGeneratorRating) =
            (determineRating(bitLists, oxygenRatingComparer), determineRating(bitLists, carbonDioxideRatingComparer))

        (Integer.parseInt(oxygenGeneratorRating, 2), Integer.parseInt(carbonDioxideGeneratorRating, 2))
    }

    def oxygenRatingComparer(onesCount: Int, zerosCount: Int) : Boolean = {
        onesCount >= zerosCount
    }

    def carbonDioxideRatingComparer(onesCount: Int, zerosCount: Int) : Boolean = {
        onesCount < zerosCount
    }

    def determineRating(bitLists: List[List[Int]], comparer: (Int, Int) => Boolean) : String = {
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
            .map(x => x.toString)
            .reduce((x, y) => x.concat(y))
    }
}
