package org.jakedoes.dev
package utils

import problems.domain.{FlashingPoint, Point, VisitingPoint}

import scala.io.Source
import scala.reflect.ClassTag

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

    def readFileArray[T:ClassTag](location: String, elementFunction: (Int, Int, Int) => T): Array[Array[T]] = {
        val array = readFileArray(location)

        array.indices.map(x => {
            val row = array(x)

            row.indices.map(y => {
                elementFunction(array(x)(y), x, y)
            }).toArray

        }).toArray
    }

    def ofPoint(value: Int, x: Int, y: Int) : Point[Int] = {
        Point(value, x, y)
    }

    def ofVisitingPoint(value: Int, x: Int, y: Int) : VisitingPoint[Int] = {
        VisitingPoint(value, x, y, false)
    }

    def ofFlashingPoint(value: Int, x: Int, y: Int) : FlashingPoint[Int] = {
        FlashingPoint(value, x, y, false)
    }
}
