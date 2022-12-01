package Day01
import java.io.File
import kotlin.math.max

fun main(args: Array<String>) {
    calorieCountingPartOne()
    calorieCountingPartTwo()
}

fun calorieCountingPartTwo() {
    println(calculateElfTotalsList().sorted().takeLast(3).sum())
}

fun calculateElfTotalsList(): List<Int> {
    val lines = createLineList()
    val elves = mutableListOf<Int>()
    var currentElfTotal = 0
    lines.forEach{line ->
        if(line.isBlank()) {
            elves.add(currentElfTotal)
            currentElfTotal = 0
        } else {
            currentElfTotal += line.toInt()
        }
    }
    return elves
}

fun calorieCountingPartOne() {
    val lines = createLineList()
    var currentElfTotal = 0
    var highestSoFar = 0
    lines.forEach{line ->
        if(line.isBlank()) {
            highestSoFar = max(currentElfTotal, highestSoFar)

            currentElfTotal = 0
        } else {
            currentElfTotal += line.toInt()
        }
    }
    println(highestSoFar)
}

fun createLineList(): MutableList<String> {
    val lineList = mutableListOf<String>()
    File("/Users/hannahspencer/Documents/GitHub/AdventOfCode/2022/Kotlin/Day01/input.txt").useLines { lines -> lines.forEach { lineList.add(it) }}
    return lineList
}
