package org.jakedoes.dev
package problems

class BingoBoard(var points : List[List[BingoPoint]]) {
    def mark(bingoBall : Int): Unit = {
        points
            .map(row => {
                row.map(p => {
                    if (p.value == bingoBall) {
                        p.isDrawn = true
                    }
                })
            })
    }

    def isBingo: Boolean = {
        // Check rows
        val isRowBingo = points.map(row => {
            row.map(p => {
                p.isDrawn
            })
                .reduce((x, y) => x && y) // find if row is bingo
        })
            .reduce((x, y) => x || y) // see if any row is bingo

        val isColumnBingo = (0 to 4).map(i => {
            points.map(row => row(i)) //column i
                .map(p => p.isDrawn)
                .reduce((x, y) => x && y)
        })
            .reduce((x, y) => x || y)

        isRowBingo || isColumnBingo
    }

    def sumUnmarked() : Int = {
        var sum = 0

        points
            .foreach(row => {
                row.foreach(p => {
                    if (!p.isDrawn) {
                        sum += p.value
                    }
                })
            })

        sum
    }
}
