package org.jakedoes.dev
package utils

import problems.domain.VisitingPoint

import scala.collection.mutable.ListBuffer

object ArrayUtils {

    def extract[T](array: Array[Array[T]], x: Int, y: Int): Option[T] = {
        try {
            Some(array(x)(y))
        } catch {
            case e: ArrayIndexOutOfBoundsException => None
        }
    }

    def extractIfExists[T](buffer: ListBuffer[T], array: Array[Array[T]], x: Int, y: Int): Unit = {
        val point = extract(array, x, y)

        if (point.isDefined) {
            buffer += point.get
        }
    }
}
