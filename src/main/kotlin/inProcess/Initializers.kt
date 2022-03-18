package inProcess

import java.io.FileReader

fun initText(fileName: String): String {
    val reader: FileReader
    var lText = ""

    try {
        reader = FileReader(fileName)
    } catch (e: java.lang.Exception) {
        return "Error"
    }

    var inp: Int = reader.read()

    while (inp != -1) {
        lText = lText.plus(inp.toChar())
        inp = reader.read()
    }
    return lText
}

fun initSentences(lText: String): List<String> {
    return lText.split(".", "?!", "!?", "?", "!")
}

fun initWords(lSentences: List<String>): ArrayList<ArrayList<String>> {
    val lWords: ArrayList<ArrayList<String>> = ArrayList()

    for (sentence in lSentences) {
        lWords.add(sentence.split(" ", ",", "–", ":", "«", "»", "\n") as java.util.ArrayList<String>)
    }

    for (line in lWords) {
        line.removeIf { word -> word == "" }
    }

    lWords.removeIf { line -> line.size == 0 }

    return lWords
}

fun initKeyWordsMatrix() {
    TODO("Наступила ночь")
}

fun initJointOccurrenceMatrix() {
    TODO("Наступила ночь")
}