package org.jakedoes.dev
package problems

import utils.FileUtils

object Day01 {

    def depthSweep(fileLocation: String, window: Int): Int = {
        val depths = readDepths(fileLocation)

        val numIncreases = calculateNumIncreases(depths, window)

        numIncreases
    }

    def calculateNumIncreases(depths: List[Int], window: Int): Int = {
        val depthsSequence = generateDepthsSequence(depths, window)

        var count = 0

        val previous = depthsSequence.slice(0, depths.size - 1)
        val next = depthsSequence.slice(1, depths.size)

        val pairs = previous.zip(next)
        pairs.foreach((x, y) => if (y > x) count = count + 1)

        count
    }

    def generateDepthsSequence(depths: List[Int], window: Int): List[Int] = {
        val windowSlide = 1

        val windowTuples = depths.sliding(window, windowSlide).toList
        val windowSummedSequence = windowTuples.map(x => x.sum)

        windowSummedSequence
    }

    def readDepths(location: String): List[Int] = {
        val depths = FileUtils.readFile(location)
        val depthsValues = depths.map(x => x.toInt)

        depthsValues
    }
}
