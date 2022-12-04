package day4

import java.io.File

fun main(args: Array<String>) {
    println(campCleanupPart1())
    println(campCleanupPart2())
}

fun campCleanupPart1(): Int {
    val lines = createLineList()
    var count = 0
    lines.forEach { line ->
        val elves = line.split(",", "-")
        val first = elves.subList(0, 2)
        val second = elves.subList(2, 4)

        if ((first[0].toInt() <= second[0].toInt() && first[1].toInt() >= second[1].toInt())
            || (first[0].toInt() >= second[0].toInt() && first[1].toInt() <= second[1].toInt())) {
            count++
        }
    }
    return count
}

fun campCleanupPart2(): Int {
    val lines = createLineList()
    var count = 0
    lines.forEach { line ->
        val elves = line.split(",", "-")
        val first = elves.subList(0, 2)
        val second = elves.subList(2, 4)


        val firstRange = first[0].toInt()..first[1].toInt()
        val secondRange = second[0].toInt()..second[1].toInt()

        if(firstRange.contains(second[0].toInt()) || firstRange.contains(second[1].toInt()) ||
            secondRange.contains(first[0].toInt()) || secondRange.contains(first[1].toInt())) {
            count++
        }
    }

    return count

}

fun createLineList(): MutableList<String> {
    val lineList = mutableListOf<String>()
    File("/Users/hannahspencer/Documents/GitHub/AdventOfCode/2022/Kotlin/day4/input.txt").useLines { lines -> lines.forEach { lineList.add(it) }}
    return lineList
}