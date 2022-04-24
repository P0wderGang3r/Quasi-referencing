package logStart

import Paragraph
import WordList
import inProcess.*

fun logInitText(fileName: String): String {
    val lText = initText(fileName)

    if (isLogInitText)
        println(lText)

    return lText
}

fun logInitParagraphs(lText: String): ArrayList<String> {
    val lParagraphs: ArrayList<String> = initParagraphs(lText)

    if (isLogInitParagraphs) {
        println("logInitParagraphs")
        for (lParagraph in lParagraphs) {
            println("vvvvvvvvvvvvvvvvvvvvv")
            println(lParagraph)
            println("^^^^^^^^^^^^^^^^^^^^^")
        }
        println()
    }

    return lParagraphs
}

fun logInitWords(lParagraph: String): ArrayList<String> {
    val lWords: ArrayList<String> = initWords(lParagraph)

    if (isLogInitWords) {
        println("logInitWords")
        println("vvvvvvvvvvvvvvvvvvvvv")
        for (word in lWords) {
            print("|$word~")
        }
        println()
        println("^^^^^^^^^^^^^^^^^^^^^")
        println()
    }

    return lWords
}

fun logInitWordOccurrenceList(lWords: ArrayList<String>): ArrayList<WordList> {
    val lWordOccurrenceList: ArrayList<WordList> = initWordOccurrenceList(lWords)

    if (isLogInitWordOccurrenceList) {
        println("logInitWordOccurrenceList")
        println("vvvvvvvvvvvvvvvvvvvvv")
        for (word in lWordOccurrenceList) {
            println(word.word + " " + word.weight)
        }
        println("^^^^^^^^^^^^^^^^^^^^^")
        println()
    }

    return lWordOccurrenceList
}

fun logInitGlobalWordOccurrenceList(paragraphs: ArrayList<Paragraph>): ArrayList<WordList> {
    val lWordOccurrenceList: ArrayList<WordList> = initGlobalWordOccurrenceList(paragraphs)

    if (isLogInitGlobalWordOccurrenceList) {
        println("logInitGlobalWordOccurrenceList")
        println("vvvvvvvvvvvvvvvvvvvvv")
        for (word in lWordOccurrenceList) {
            println(word.word + " " + word.weight)
        }
        println("^^^^^^^^^^^^^^^^^^^^^")
        println()
    }

    return lWordOccurrenceList
}
