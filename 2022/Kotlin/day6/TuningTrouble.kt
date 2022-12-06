package day6

import java.io.File

fun main(args: Array<String>) {
    println(tuningTrouble(4))
    println(tuningTrouble(14))
}


fun tuningTrouble(markerSize: Int): Int {
    val data = getStringOfData()
    var charsToCheck = mutableListOf<Char>()
    var count = 0
    data.forEach { char ->
        count++
        while(charsToCheck.size != markerSize) {
            charsToCheck.add(char)
        }
        val charsToCheckSet: Set<Char> = charsToCheck.toSet()
        if(charsToCheck.size == charsToCheckSet.size) {
            return count
        }
        charsToCheck = charsToCheck.subList(1, markerSize)

    }
    return count
}

fun getStringOfData(): String {
    var data = ""
    File("/Users/hannahspencer/Documents/GitHub/AdventOfCode/2022/Kotlin/day6/input.txt").useLines { lines -> lines.forEach { data += it }}
    return data
}