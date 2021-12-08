package org.jakedoes.dev
package problems

import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day08 {

    def countEasyDigits(fileLocation: String) : Int = {
        var count = 0

        val decodedNumbers = sevenSegmentSearch(fileLocation)

        decodedNumbers.map(decoded => {
            decoded.map(number => {
                if (List(1, 4, 7, 8).contains(number)) {
                    count += 1
                }
            })
        })

        count
    }

    def countAllDigits(fileLocation: String) : Int = {
        var count = 0

        val decodedNumbers = sevenSegmentSearch(fileLocation)

        decodedNumbers
            .map(x => {
                x
                    .map(y => y.toString)
                    .reduce((x, y) => x + y)

            })
            .foreach(number => count += number.toInt)

        count
    }

    def sevenSegmentSearch(fileLocation: String): List[List[Int]] = {
        val lines = FileUtils.readFile(fileLocation)

        lines.map(l => decodeSegments(l))
    }

    def decodeSegments(line: String): List[Int] = {
        val (testDigits, outputDigits) = formatLine(line)

        var segmentGroupsToNumbers = Map[String, Int]()

        // Get the numbers based on the unique number of segments
        segmentGroupsToNumbers += (testDigits.filter(x => x.length == 2).head, 1)
        segmentGroupsToNumbers += (testDigits.filter(x => x.length == 4).head, 4)
        segmentGroupsToNumbers += (testDigits.filter(x => x.length == 3).head, 7)
        segmentGroupsToNumbers += (testDigits.filter(x => x.length == 7).head, 8)

        // Now, look at the number of each segments across all five segment digits
        // and the six segment digits, and inspect their relationships.
        var (fiveSegmentDigits, fiveSegmentDigitsSegmentCounts) = inspectDigitsBySegmentLength(testDigits, 5)
        var (sixSegmentDigits, sixSegmentDigitsSegmentCounts) = inspectDigitsBySegmentLength(testDigits, 6)

        /*
        Here are the five segment digits:

         aaaa      aaaa      aaaa
        .    c    .    c    b    .
        .    c    .    c    b    .
         dddd      dddd      dddd
        e    .    .    f    .    f
        e    .    .    f    .    f
         gggg      gggg      gggg

        Here are the six segment digits

         aaaa      aaaa      aaaa
        b    c    b    .    b    c
        b    c    b    .    b    c
         ....      dddd      dddd
        e    f    e    f    .    f
        e    f    e    f    .    f
         gggg      gggg      gggg
         */

        val five = determineFive(fiveSegmentDigits, sixSegmentDigits, fiveSegmentDigitsSegmentCounts, sixSegmentDigitsSegmentCounts)
        segmentGroupsToNumbers += (five, 5)
        fiveSegmentDigits -= five

        val two = determineTwo(fiveSegmentDigits, sixSegmentDigits, fiveSegmentDigitsSegmentCounts, sixSegmentDigitsSegmentCounts)
        segmentGroupsToNumbers += (two, 2)
        fiveSegmentDigits -= two

        // The remaining five segment digit is 3
        segmentGroupsToNumbers += (fiveSegmentDigits.head, 3)

        val zero = determineZero(fiveSegmentDigits, sixSegmentDigits, fiveSegmentDigitsSegmentCounts, sixSegmentDigitsSegmentCounts)
        segmentGroupsToNumbers += (zero, 0)
        sixSegmentDigits -= zero

        val six = determineSix(fiveSegmentDigits, sixSegmentDigits, fiveSegmentDigitsSegmentCounts, sixSegmentDigitsSegmentCounts)
        segmentGroupsToNumbers += (six, 6)
        sixSegmentDigits -= six

        // The remaining six segment digit is 9
        segmentGroupsToNumbers += (sixSegmentDigits.head, 9)

        // Now, decode the output.
        val numbers = outputDigits.map(digit => segmentGroupsToNumbers(digit))

        numbers
    }

    def formatLine(line: String): (List[String], List[String]) = {
        val parts = line.split("\\|")
            .map(x => x.trim)
            .map(x => x.split(" ").map(x => x.sorted))

        (parts.head.toList, parts.last.toList)
    }

    def inspectDigitsBySegmentLength(testDigits: List[String], segmentLength: Int): (ListBuffer[String], Map[String, Int]) = {
        val segmentDigits = ListBuffer[String]()

        testDigits
            .filter(segments => segments.length == segmentLength)
            .map(segments => segments.sorted)
            .foreach(segment => segmentDigits += segment)

        val segmentDigitsSegmentCounts = segmentDigits
            .flatten
            .map(x => x.toString)
            .groupBy(identity)
            .view.mapValues(_.size)
            .toMap

        (segmentDigits, segmentDigitsSegmentCounts)
    }

    def determineFive(fiveSegmentDigits: ListBuffer[String], sixSegmentDigits: ListBuffer[String], fiveSegmentDigitsSegmentCounts: Map[String, Int], sixSegmentDigitsSegmentCounts: Map[String, Int]): String = {
        // The "b" segment has a count of:
        // --> one in the five segment digits
        // --> three in the six segment digits
        // The one count in the five digit segments corresponds to number 5

        val matchingSegments = fiveSegmentDigitsSegmentCounts
            .filter(entry => entry._2 == 1)
            .keySet

        val matchedSegment = matchingSegments
            .filter(segment => sixSegmentDigitsSegmentCounts(segment) == 3)
            .head

        val five = fiveSegmentDigits.filter(x => x.contains(matchedSegment)).head

        five
    }

    def determineTwo(fiveSegmentDigits: ListBuffer[String], sixSegmentDigits: ListBuffer[String], fiveSegmentDigitsSegmentCounts: Map[String, Int], sixSegmentDigitsSegmentCounts: Map[String, Int]): String = {
        // The "e" segment has a count of:
        // --> one in the five segment digits
        // --> two in the six segment digits
        // This one count in the five digit segments corresponds to number 2

        val matchingSegments = fiveSegmentDigitsSegmentCounts
            .filter(entry => entry._2 == 1)
            .keySet

        val matchedSegment = matchingSegments
            .filter(segment => sixSegmentDigitsSegmentCounts(segment) == 2)
            .head

        val two = fiveSegmentDigits.filter(x => x.contains(matchedSegment)).head

        two
    }

    def determineZero(fiveSegmentDigits: ListBuffer[String], sixSegmentDigits: ListBuffer[String], fiveSegmentDigitsSegmentCounts: Map[String, Int], sixSegmentDigitsSegmentCounts: Map[String, Int]): String = {
        // The "d" segment has a count of:
        // --> three in the five segment digits
        // --> two in the six segment digits
        // The six segment digit that does not have the "d" segment corresponds to number 0

        val matchingSegments = fiveSegmentDigitsSegmentCounts
            .filter(entry => entry._2 == 3)
            .keySet

        val matchedSegment = matchingSegments
            .filter(segment => sixSegmentDigitsSegmentCounts(segment) == 2)
            .head

        val zero = sixSegmentDigits.filter(x => !x.contains(matchedSegment)).head

        zero
    }

    def determineSix(fiveSegmentDigits: ListBuffer[String], sixSegmentDigits: ListBuffer[String], fiveSegmentDigitsSegmentCounts: Map[String, Int], sixSegmentDigitsSegmentCounts: Map[String, Int]): String = {
        // The "c" segment has a count of:
        // --> two in the five segment digits
        // --> two in the six segment digits
        // Given we have now deduced number 0 earlier, this remaining six segment digit
        // that has this segment is the number 9

        val matchingSegments = fiveSegmentDigitsSegmentCounts
            .filter(entry => entry._2 == 2)
            .keySet

        val matchedSegment = matchingSegments
            .filter(segment => sixSegmentDigitsSegmentCounts(segment) == 2)
            .head

        val six = sixSegmentDigits.filter(x => !x.contains(matchedSegment)).head

        six
    }
}
