package org.jakedoes.dev
package utils

import scala.io.Source

object FileUtils {
    def readFile(location: String): List[String] = {
        val source = Source.fromResource(location)
        val lines = try source.getLines().toList finally source.close()

        lines
    }
}
