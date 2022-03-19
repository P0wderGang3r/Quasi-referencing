package inProcess

import KeyWordStruct
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

fun checkWordEquality(keyWord: String, word: String): Boolean {
    return keyWord == word
}

fun initKeyWordList(lWords: ArrayList<ArrayList<String>>): ArrayList<KeyWordStruct> {
    val lKeyWordList: ArrayList<KeyWordStruct> = ArrayList()
    var trigger: Boolean

    for (sentence in lWords) {
        for (word in sentence) {
            trigger = false
            for (keyWord in lKeyWordList) {
                if (checkWordEquality(keyWord.word, word)) {
                    keyWord.occurrences += 1
                    trigger = true
                    break
                }
            }
            if (!trigger) {
                lKeyWordList.add(KeyWordStruct(word, 1))
            }
        }
    }

    return lKeyWordList
}


fun initKeyWordMatrix(lKeyWordList: ArrayList<KeyWordStruct>): Array<Array<Int>> {
    val lKeyWordMatrix: Array<Array<Int>> = Array(lKeyWordList.size) { Array(lKeyWordList.size) { 0 } }

    for ((iteration, width) in lKeyWordMatrix.withIndex()) {
        width[iteration] = lKeyWordList[iteration].occurrences
    }

    return lKeyWordMatrix
}

/*
 * Тут стоит оставить себе на будущее объяснение.
 * Допустим, получаем следующее слово, соседей которого мы должны получить.
 * Проходимся по матрице по горизонтали.
 * Если мы находим основное слово, совпадающее по горизонтали (а мы находим), то ищем соседа слева (справа) по вертикали.
 * В случае нахождения соседа (а мы его находим) добавляем к счётчику единицу и продолжаем проход по предложениям.
 */
fun initJointOccurrenceMatrix(lKeyWordList: ArrayList<KeyWordStruct>,
                              lWords: ArrayList<ArrayList<String>>): Array<Array<Int>> {
    val lJointOccurrenceMatrix: Array<Array<Int>> = Array(lKeyWordList.size) { Array(lKeyWordList.size) { 0 } }

    for (sentence in lWords) {
        for (iteration: Int in 0 until sentence.size) {
            if (iteration > 0) {
                for (widthIteration in 0 until lKeyWordList.size) {
                    if (lKeyWordList[widthIteration].word == sentence[iteration]) {
                        for (heightIteration in 0 until lKeyWordList.size) {
                            if (lKeyWordList[heightIteration].word == sentence[iteration - 1]) {
                                lJointOccurrenceMatrix[widthIteration][heightIteration] += 1
                                break
                            }
                        }
                        break
                    }
                }
            }

            if (iteration < sentence.size - 1) {
                for (widthIteration in 0 until lKeyWordList.size) {
                    if (lKeyWordList[widthIteration].word == sentence[iteration]) {
                        for (heightIteration in 0 until lKeyWordList.size) {
                            if (lKeyWordList[heightIteration].word == sentence[iteration + 1]) {
                                lJointOccurrenceMatrix[widthIteration][heightIteration] += 1
                                break
                            }
                        }
                        break
                    }
                }
            }
        }
    }

    return lJointOccurrenceMatrix
}