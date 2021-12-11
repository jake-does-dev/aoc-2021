package org.jakedoes.dev
package utils

object ArrayUtils {

    def extract[T](array: Array[Array[T]], x: Int, y: Int): Option[T] = {
        try {
            Some(array(x)(y))
        } catch {
            case e: ArrayIndexOutOfBoundsException => None
        }
    }
}
