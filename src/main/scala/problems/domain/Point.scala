package org.jakedoes.dev
package problems.domain

class Point[T](var value: T, var x: Int, var y: Int) {
    override def toString = s"Point(value=$value, x=$x, y=$y)"

    def canEqual(other: Any): Boolean = other.isInstanceOf[Point[T]]

    override def equals(other: Any): Boolean = other match {
        case that: Point[T] =>
            (that canEqual this) &&
                value == that.value &&
                x == that.x &&
                y == that.y
        case _ => false
    }

    override def hashCode(): Int = {
        val state = Seq(value, x, y)
        state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
    }
}
