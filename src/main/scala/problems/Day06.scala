package org.jakedoes.dev
package problems

import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day06 {

    def simulateLanternfish(fileLocation: String, numDays: Int): Long = {
        val counts = (0 to 8).map(i => 0.toLong).toBuffer

        FileUtils.readFile(fileLocation)
            .head
            .split(",")
            .map(x => x.toInt)
            .foreach(x => counts(x) += 1)

        val decreasingTraversal = (0 to 8).reverse.toList

        (0 until numDays).foreach(_ => {
            val nonZeroFish = decreasingTraversal.filter(i => counts(i) > 0)
            val nonZeroCounts = nonZeroFish.map(nzf => counts(nzf))

            nonZeroFish.indices.foreach(fishIdx => {
                val fishValue = nonZeroFish(fishIdx)
                val fishCount = nonZeroCounts(fishIdx)

                fishValue match {
                    case 0 =>
                        counts(0) -= fishCount
                        counts(6) += fishCount
                        counts(8) += fishCount

                    case i =>
                        counts(i) -= fishCount
                        counts(i - 1) += fishCount
                }
            })
        })

        val numFish = counts.sum

        numFish
    }
}
