package org.jakedoes.dev
package problems

import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day03 {

    def determineRates(fileLocation: String) : (Int, Int) = {
        val lines = FileUtils.readFile(fileLocation)

        val bitLists = lines
            .map(x => x.split(""))
            .map(x => {
                x.map(y => y.toInt)
            })
            .map(x => x.toList)

        val bitSum : List[Int] = bitLists
            .reduce((x, y) => {
                x.zip(y).map((a, b) => a + b)
            }
        )

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

}
