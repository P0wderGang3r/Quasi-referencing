package logStart

import BushStruct
import ParagraphConnectionsStruct
import ParagraphStruct
import WordStruct
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

fun logInitWordOccurrenceList(lWords: ArrayList<String>): ArrayList<WordStruct> {
    val lWordOccurrenceList: ArrayList<WordStruct> = initWordOccurrenceList(lWords)

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

fun logInitGlobalWordOccurrenceList(paragraphStructs: ArrayList<ParagraphStruct>): ArrayList<WordStruct> {
    val lWordOccurrenceList: ArrayList<WordStruct> = initGlobalWordOccurrenceList(paragraphStructs)

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

fun logInitWordAmount(wordOccurrenceList: ArrayList<WordStruct>): Double {
    val wordAmount = initWordAmount(wordOccurrenceList)

    if (isLogInitWordAmount)
        println("logInitWordAmount ----> $wordAmount\n")

    return wordAmount
}


fun logParagraphStatus(paragraphStructs: ArrayList<ParagraphStruct>) {
    if (isLogParagraphStatus) {
        println("logParagraphStatus")
        for (paragraph in paragraphStructs) {
            println("vvvvvvvvvvvvvvvvvvvvv")
            for (word in paragraph.wordWeightList)
                println(word.word + " " + word.weight)
            println("^^^^^^^^^^^^^^^^^^^^^")
        }
        println()
    }
}

fun logMakeSemanticConnections(paragraphs: ArrayList<ParagraphStruct>): ArrayList<ParagraphConnectionsStruct> {
    val semanticConnections: ArrayList<ParagraphConnectionsStruct> = makeSemanticConnections(paragraphs)

    if (isLogMakeSemanticConnections) {
        println("logParagraphStatus")
        for (semanticPair in semanticConnections) {
            if (semanticPair.connectionCoefficient != 1.0) {
                println("vvvvvvvvvvvvvvvvvvvvv")
                println(semanticPair.firstParagraph.paragraph)
                println("совпадает с коэффициентом " + semanticPair.connectionCoefficient + " с")
                println(semanticPair.secondParagraph.paragraph)
                println("^^^^^^^^^^^^^^^^^^^^^")
            }
        }
    }

    return semanticConnections
}

fun logMakeBushList(paragraphConnections: ArrayList<ParagraphConnectionsStruct>): ArrayList<BushStruct> {
    val lBushList: ArrayList<BushStruct> = makeBushList(paragraphConnections)

    if (isLogMakeBushList) {
        println("logMakeBushList")
        for (bush in lBushList) {
            println("vvvvvvvvvvvvvvvvvvvvv")
            println("В кусте " + bush.paragraphAmount + " абзацев.")
            for (paragraphPair in bush.paragraphPairs)
                println("Значимость абзаца: " + paragraphPair.firstParagraph.paragraphWeight + "; Текст абзаца: " + paragraphPair.firstParagraph.paragraph + " || " + paragraphPair.secondParagraph.paragraph)
            println("^^^^^^^^^^^^^^^^^^^^^")
        }
        println()
    }

    return lBushList
}