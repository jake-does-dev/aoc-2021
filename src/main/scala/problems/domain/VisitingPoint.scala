package org.jakedoes.dev
package problems.domain

import problems.domain.Point

class VisitingPoint[T](value: T, x: Int, y: Int, var visited: Boolean) extends Point[T](value, x, y) {
    override def toString = s"VisitingPoint(visited=$visited)${super.toString}"
}
