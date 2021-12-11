package org.jakedoes.dev
package problems.day09

class Point(
               var value: Int,
               var x: Int,
               var y: Int,
               var visited: Boolean) {


    override def toString = s"Point(value=$value, x=$x, y=$y, visited=$visited)"
}
