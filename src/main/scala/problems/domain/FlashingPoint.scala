package org.jakedoes.dev
package problems.domain

class FlashingPoint[T](value: T, x: Int, y: Int, var flashed: Boolean) extends Point[T](value, x, y) {
    override def toString = s"FlashingPoint(visited=$flashed)${super.toString}"
}
