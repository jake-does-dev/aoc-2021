package org.jakedoes.dev
package utils

import problems.domain.{Point, VisitingPoint}

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

        array.map(slice => {
            slice.split("").map(value => value.toInt)
        })
    }

    def readFileArrayAsPoints(location: String): Array[Array[Point]] = {
        val array = readFileArray(location)

        array.indices.map(x => {
            val row = array(x)

            row.indices.map(y => {
                Point(array(x)(y), x, y)
            }).toArray

        }).toArray
    }

    def readFileArrayAsVisitingPoints(location: String): Array[Array[VisitingPoint]] = {
        val array = readFileArray(location)

        array.indices.map(x => {
            val row = array(x)

            row.indices.map(y => {
                VisitingPoint(array(x)(y), x, y, false)
            }).toArray

        }).toArray
    }
}
