package logStart

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