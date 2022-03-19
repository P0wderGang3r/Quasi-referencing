package logStart

import KeyWordStruct
import inProcess.*

fun logInitText(fileName: String): String {
    val lText = initText(fileName)

    if (isLogInitText)
        println(lText)

    return lText
}

fun logInitSentences(lText: String): List<String> {
    val lSentences: List<String> = initSentences(lText)

    if (isLogInitSentences) {
        for (lSentence in lSentences) {
            println(lSentence)
        }
        println()
    }

    return lSentences
}

fun logInitWords(lSentences: List<String>): ArrayList<ArrayList<String>> {
    val lWords: ArrayList<ArrayList<String>> = initWords(lSentences)

    if (isLogInitWords) {
        for (line in lWords) {
            for (word in line) {
                print("|$word~")
            }
            println()
        }
    }

    return lWords
}

fun logInitKeyWordList(lWords: ArrayList<ArrayList<String>>): ArrayList<KeyWordStruct> {
    val lKeyWordList: ArrayList<KeyWordStruct> = initKeyWordList(lWords)

    if (isLogInitKeyWordList) {
        for (keyWord in lKeyWordList) {
            println(keyWord.word + " " + keyWord.occurrences)
        }
    }

    return lKeyWordList
}

fun logInitKeyWordMatrix(lKeyWordList: ArrayList<KeyWordStruct>): Array<Array<Int>> {
    val lKeyWordMatrix: Array<Array<Int>> = initKeyWordMatrix(lKeyWordList)

    if (isLogInitKeyWordMatrix) {
        for ((iteration, width) in lKeyWordMatrix.withIndex()) {
            for (height in width) {
                print("$height | ")
            }
            print(lKeyWordList[iteration].word)
            println()
        }
    }

    return lKeyWordMatrix
}

fun logInitJointOccurrenceMatrix(lKeyWordList: ArrayList<KeyWordStruct>, lWords: ArrayList<ArrayList<String>>): Array<Array<Int>> {
    val lJointOccurrenceMatrix: Array<Array<Int>> = initJointOccurrenceMatrix(lKeyWordList, lWords)

    if (isLogInitJointOccurrenceMatrix) {
        for ((iteration, width) in lJointOccurrenceMatrix.withIndex()) {
            for (height in width) {
                print("$height | ")
            }
            print(lKeyWordList[iteration].word)
            println()
        }
    }

    return lJointOccurrenceMatrix
}