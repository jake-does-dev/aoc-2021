package org.jakedoes.dev
package problems

import utils.FileUtils

object Day07 {

    def calculateFuel(fileLocation: String, fuelExpenditureFunction: (Int, Int) => Int) : Int = {

        val lines = FileUtils.readFile(fileLocation)
            .head
            .split(",")
            .map(x => x.toInt)
            .toList

        val totalFuelPerPosition = (0 to lines.max).map(targetPosition => {
            lines
                .map(crabPosition => fuelExpenditureFunction(crabPosition, targetPosition))
                .sum
        })

        val totalFuel = totalFuelPerPosition.min

        totalFuel
    }

    def calculateFuelConstantStep(crabPosition: Int, targetPosition: Int) : Int = {
        math.abs(crabPosition - targetPosition)
    }

    def calculateFuelIncreasingStep(crabPosition: Int, targetPosition: Int) : Int = {
        val numberOfSteps = math.abs(crabPosition - targetPosition) + 1

        (numberOfSteps * (numberOfSteps - 1)) / 2 // sum of n natural numbers = n(n-1)/2
    }
}
