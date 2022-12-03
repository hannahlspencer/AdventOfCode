package day3
import java.io.File


fun main(args: Array<String>) {
    println(rucksackReorgPart1())
    println(rucksackReorgPart2())
}


fun rucksackReorgPart1() : Int{
    val input = createLineList()
    var total = 0
    input.forEach{line ->
        val first = line.substring(0, line.length / 2).toCharArray().toList()
        val second = line.substring(line.length / 2, line.length).toCharArray().toSet()
        val result : Char = first.intersect(second).first()
        total += convertLetterToPriority(result)
    }
    return total
}

fun rucksackReorgPart2() : Int {
    val input = createLineList()
    var total = 0
    var setOfItems = setOf<Char>()
    var counter = 0
    input.forEach{line ->
        counter += 1
        when(counter) {
            1 -> setOfItems = line.toSet()
            2 -> setOfItems = setOfItems.intersect(line.toSet())
            3 -> {
                setOfItems = setOfItems.intersect(line.toSet())
                total += convertLetterToPriority(setOfItems.first())
                counter = 0
            }
            else -> println("Counter has gone wrong")
        }
    }
    return total
}

fun convertLetterToPriority(letter: Char) : Int{
    val stringLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    return stringLetters.indexOf(letter) + 1
}

fun createLineList(): MutableList<String> {
    val lineList = mutableListOf<String>()
    File("/Users/hannahspencer/Documents/GitHub/AdventOfCode/2022/Kotlin/day3/input.txt").useLines { lines -> lines.forEach { lineList.add(it) }}
    return lineList
}