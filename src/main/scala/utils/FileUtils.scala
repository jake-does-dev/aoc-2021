package org.jakedoes.dev
package utils

import scala.io.Source

object FileUtils {
    def readFile(location: String): List[String] = {
        val source = Source.fromResource(location)
        val lines = try source.getLines().toList finally source.close()

        lines
    }

    def readFileArray(location: String): Array[Array[Int]] = {
        val source = Source.fromResource(location)
        val array = try source.getLines().toArray finally source.close()

        val asIntArray = array.indices.map(i => {
            val row = array(i)
            row.split("").map(x => x.toInt)
        })

        asIntArray.toArray
    }
}
