package org.jakedoes.dev
package problems

import utils.FileUtils

object Day04 {

    def playBingo(fileLocation: String) : (Int, Int) = {

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

        while (!searchingForBingo(bingoBoards)) {

            val ball = bingoBalls(ballIdx)

            bingoBoards.foreach(board => {
                board.mark(ball)
            })

            ballIdx += 1
        }

        val winningBoard = bingoBoards
            .filter(board => board.isBingo)
            .head

        val lastBallDrawn = bingoBalls(ballIdx - 1)

        (lastBallDrawn, winningBoard.sumUnmarked())
    }

    def searchingForBingo(bingoBoards: List[BingoBoard]): Boolean = {
        bingoBoards
            .map(board => board.isBingo)
            .reduce((x, y) => x || y)
    }
}
