package day5


import java.io.File

fun main(args: Array<String>) {
    println(supplyStacksPart1())
    println(supplyStacksPart2())
}

fun supplyStacksPart1(): String {
    val lineList = createLineList()
    val stacksAndMoves = separateCratesFromMoves(lineList)
    val moves = stacksAndMoves.first
    val stacks = stacksAndMoves.second

    moves.forEach{ move ->

        val instructionList = move.split(" ")
        val totalToMove = instructionList[1].toInt()
        val from = instructionList[3].toInt() - 1
        val to = instructionList[5].toInt() - 1
        val elementsToMove = stacks[from].take(totalToMove).reversed()

        stacks[from] = stacks[from].subList(totalToMove, stacks[from].size)
        stacks[to].addAll(0, elementsToMove)
    }
    var answer = ""
    stacks.forEach { stack -> if(stack.isNotEmpty()) {answer += stack[0] } }
    return answer

}

fun supplyStacksPart2(): String {
    val lineList = createLineList()
    val stacksAndMoves = separateCratesFromMoves(lineList)
    val moves = stacksAndMoves.first
    val stacks = stacksAndMoves.second

    moves.forEach{ move ->

        val instructionList = move.split(" ")
        val totalToMove = instructionList[1].toInt()
        val from = instructionList[3].toInt() - 1
        val to = instructionList[5].toInt() - 1
        val elementsToMove = stacks[from].take(totalToMove)

        stacks[from] = stacks[from].subList(totalToMove, stacks[from].size)
        stacks[to].addAll(0, elementsToMove)
    }
    var answer = ""
    stacks.forEach { stack -> if(stack.isNotEmpty()) {answer += stack[0] } }
    return answer

}

fun separateCratesFromMoves(lineList: MutableList<String>): Pair<MutableList<String>, MutableList<MutableList<Char>>> {
    val stacksOfCrates = mutableListOf<MutableList<Char>>()
    val instructions = mutableListOf<String>()
    lineList.forEach { line ->
        var processing = line
        var stack = 0
        while (processing.isNotEmpty()) {
            stacksOfCrates.add(mutableListOf())
            if (processing.first() == ' ') {
                processing = processing.drop(4)
                stack++
            } else if (processing.startsWith('[')) {
                processing = processing.drop(1)
                stacksOfCrates[stack].add(processing.first())
                processing = processing.drop(3)
                stack++
            } else if(processing.startsWith("move")) {
                instructions.add(line)
                processing = ""
            } else if(processing.startsWith('1')) {
                // skip line
                processing = ""
            } else {
                println("There's something you haven't considered here: " + processing.first() + "?")
            }
        }

    }
    return Pair(instructions, stacksOfCrates)
}

fun createLineList(): MutableList<String> {
    val lineList = mutableListOf<String>()
    File("/Users/hannahspencer/Documents/GitHub/AdventOfCode/2022/Kotlin/day5/input.txt").useLines { lines -> lines.forEach { lineList.add(it) }}
    return lineList
}