package org.jakedoes.dev
package problems

import utils.FileUtils

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Day10 {

    val OPEN = List('(', '[', '{', '<')
    val CLOSE = List(')', ']', '}', '>')

    val ERROR_SCORES = Map(
        ')' -> 3,
        ']' -> 57,
        '}' -> 1197,
        '>' -> 25137
    )

    val COMPLETION_SCORES = Map(
        '(' -> 1,
        '[' -> 2,
        '{' -> 3,
        '<' -> 4
    )

    def incompleteHandling(fileLocation: String) : Long = {
        val lines = FileUtils.readFile(fileLocation)

        val completionScores = ListBuffer[Long]()

        lines.foreach(line => {
            val (isCorrupt, _, stack) = findCorruption(line)

            if (!isCorrupt) {
                completionScores += computeCompletionScore(stack)
            }
        })

        val sortedCompletionScores = completionScores.sorted
        sortedCompletionScores((sortedCompletionScores.length - 1) / 2)
    }

    def findCorruptions(fileLocation: String): Long = {
        val lines = FileUtils.readFile(fileLocation)

        val corruptChars = ListBuffer[Char]()

        lines.foreach(line => {
            val (isCorrupt, corruptChar, _) = findCorruption(line)

            if (isCorrupt) {
                corruptChars += corruptChar
            }
        })

        val corruptionSum = corruptChars
            .map(c => ERROR_SCORES(c))
            .sum

        corruptionSum
    }

    def findCorruption(line: String) : (Boolean, Char, mutable.Stack[Char]) = {
        var isCorrupt = false
        var corruptChar = '-'

        val stack = mutable.Stack[Char]()
        var idx = 0

        while(!isCorrupt && idx != line.length) {
            val char = line(idx)

            if (OPEN.contains(char)) {
                stack.push(char)

            } else if (CLOSE.contains(char)) {
                val closeCharIdx = CLOSE.indexOf(char)
                val openChar = OPEN(closeCharIdx)

                if (openChar != stack.pop()) {
                    isCorrupt = true
                    corruptChar = char
                }
            }

            idx += 1
        }

        (isCorrupt, corruptChar, stack)
    }

    def computeCompletionScore(stack: mutable.Stack[Char]) : Long = {
        var score = 0L

        while (stack.nonEmpty) {
            score = score * 5 + COMPLETION_SCORES(stack.pop())
        }

        score
    }
}
