package org.jakedoes.dev
package problems

import utils.FileUtils

import scala.collection.mutable.ListBuffer

object Day04 {

    def playBingo(fileLocation: String, continuePlayingCriterion: List[BingoBoard] => Boolean) : (Int, Int) = {

        val lines = FileUtils.readFile(fileLocation)
        val bingoBalls = lines.head.split(",").map(x => x.toInt)
        val bingoCards = lines.tail

        val bingoBoards = bingoCards
            .grouped(6)
            .map(card => card.tail)
            .map(card => {
                card.map(line => {
                    line.trim
                        .replace("  ", " ") //fix formatting in the input
                        .split(" ").map(value => new BingoPoint(value.toInt)).toList
                })
            })
            .map(card => new BingoBoard(card))
            .toList


        var ballIdx = 0
        val winningBoards = ListBuffer[BingoBoard]()

        while (continuePlayingCriterion(bingoBoards)) {

            val ball = bingoBalls(ballIdx)

            bingoBoards.foreach(board => {
                board.mark(ball)

                if (board.isBingo && !board.called) {
                    winningBoards.prepend(board)
                    board.called = true
                }
            })

            ballIdx += 1
        }

        val winningBoard = winningBoards.head
        val lastBallDrawn = bingoBalls(ballIdx - 1)

        (lastBallDrawn, winningBoard.sumUnmarked())
    }

    def oneBoardNeedsBingo(bingoBoards: List[BingoBoard]): Boolean = {
        !bingoBoards
            .map(board => board.isBingo)
            .reduce((x, y) => x || y)
    }

    def everyBoardNeedsBingo(bingoBoards: List[BingoBoard]): Boolean = {
        !bingoBoards
            .map(board => board.isBingo)
            .reduce((x, y) => x && y)
    }
}
