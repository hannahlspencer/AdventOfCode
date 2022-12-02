package Day02

import java.io.File

fun main(args: Array<String>) {
    rockPaperScissorsPartOne()
    rockPaperScissorsPartTwo()
}

fun rockPaperScissorsPartOne() {
    val rounds = createLineList()
    var score = 0
    rounds.forEach{round ->
        score += getRoundResult(round.first(), round.last())
    }
    println(score)
}

fun rockPaperScissorsPartTwo() {
    val rounds = createLineList()
    var score = 0
    rounds.forEach{round ->
        score += consultRoundMapForScore(round.first(), round.last())
    }
    println(score)
}

fun getRoundResult(opponentShape: Char, ownShape: Char): Int {
    val ownShapes = listOf('X', 'Y', 'Z')
    val opponentShapes = listOf('A', 'B', 'C')
    val ownIndex = ownShapes.indexOf(ownShape) + 1
    val opponentIndex = opponentShapes.indexOf(opponentShape) + 1

    if(ownIndex == opponentIndex) {
        return ownIndex + 3
    }
    return when(ownShape) {
        'X' -> {
            (if(opponentShape == 'B') 0 else 6) + ownIndex
        }
        'Y' -> {
            (if(opponentShape == 'A') 6 else 0) + ownIndex
        }
        'Z' -> {
            (if(opponentShape == 'A') 0 else 6) + ownIndex
        }
        else -> -1
    }

}

fun consultRoundMapForScore(opponentShape: Char, ownShape: Char): Int {
    val mapOfScores = mutableMapOf(
        Pair('A', 'X') to 3, // rock, lose = scissors
        Pair('A', 'Y') to 4, // rock, draw = rock
        Pair('A', 'Z') to 8, // rock, win = paper
        Pair('B', 'X') to 1, // paper, lose = rock
        Pair('B', 'Y') to 5, // paper, draw = paper
        Pair('B', 'Z') to 9, // paper, win = scissors
        Pair('C', 'X') to 2, // scissors, lose = paper
        Pair('C', 'Y') to 6, // scissors, draw = scissors
        Pair('C', 'Z') to 7) // scissors, win = rock

    return mapOfScores[Pair(opponentShape, ownShape)]!!
}

fun createLineList(): MutableList<String> {
    val lineList = mutableListOf<String>()
    File("/Users/hannahspencer/Documents/GitHub/AdventOfCode/2022/Kotlin/Day02/input.txt").useLines { lines -> lines.forEach { lineList.add(it) }}
    return lineList
}
