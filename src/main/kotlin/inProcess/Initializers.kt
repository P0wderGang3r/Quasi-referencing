package inProcess

import ParagraphStruct
import WordStruct
import java.io.FileReader

/*
 * Считывание текста из файла.
 */
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

/*
 * Парсинг текста в набор абзацев.
 */
fun initParagraphs(text: String): ArrayList<String> {
    return text.split("\n") as java.util.ArrayList<String>
}

/*
 * Парсинг абзацев в набор слов в них.
 */
fun initWords(paragraphs: String): ArrayList<String> {
    val lWords = paragraphs.lowercase().split(" ", ",", "–", ":", ";", "«", "»", "\n", "(", ")", ".", "?!", "!?", "?", "!") as java.util.ArrayList<String>

    lWords.removeIf { word -> word == "" }

    return lWords
}

/*
 * Создание списка количества появлений слов в абзаце.
 */
fun initWordOccurrenceList(words: ArrayList<String>): ArrayList<WordStruct> {
    val lWordOccurrenceList: ArrayList<WordStruct> = ArrayList()
    var trigger: Boolean

    for (word in words) {
        trigger = false
        for (uniqueWord in lWordOccurrenceList) {
            if (checkWordEquality(uniqueWord.word, word)) {
                uniqueWord.weight += 1
                trigger = true
                break
            }
        }
        if (!trigger) {
            lWordOccurrenceList.add(WordStruct(word, 1.0))
        }
    }

    return lWordOccurrenceList
}

/*
 * Создание количества слов в абзаце.
 */
fun initWordAmount(wordOccurrenceList: ArrayList<WordStruct>): Double {
    var wordAmount = 0.0

    for (word in wordOccurrenceList) {
        wordAmount += word.weight
    }

    return wordAmount
}

/*
 * Создание списка количества появлений слов во всём тексте.
 */
fun initGlobalWordOccurrenceList(paragraphStructs: ArrayList<ParagraphStruct>): ArrayList<WordStruct> {
    val lGlobalWordOccurrenceList: ArrayList<WordStruct> = ArrayList()

    //Проход по абзацам
    for (paragraph in paragraphStructs) {
        //Проход по словам в абзацах
        for (wordListElem in paragraph.wordWeightList) {

            //Булева переменная, индицирующая, найдено ли искомое слово в глобальном реестре слов
            var isWordAlreadyInGlobalWordOccurrenceList = false

            //Проход по списку слов в глобальном реестре слов
            for (globalWordListElem in lGlobalWordOccurrenceList) {

                //Если найдено совпадение, то добавляем к глобальному счётчику слов счётчик слов из абзаца
                if (checkWordEquality(wordListElem.word, globalWordListElem.word)) {
                    globalWordListElem.weight += wordListElem.weight
                    isWordAlreadyInGlobalWordOccurrenceList = true
                    break
                }
            }
            //Иначе добавляем новое слово с количеством локальных повторений в список
            if (!isWordAlreadyInGlobalWordOccurrenceList) {
                lGlobalWordOccurrenceList.add(WordStruct(wordListElem.word, wordListElem.weight))
            }
        }
    }

    return lGlobalWordOccurrenceList
}
