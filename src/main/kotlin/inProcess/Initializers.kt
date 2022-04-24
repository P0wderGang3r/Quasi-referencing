package inProcess

import Paragraph
import WordList
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
fun initParagraphs(lText: String): ArrayList<String> {
    return lText.split("\n") as java.util.ArrayList<String>
}

/*
 * Парсинг абзацев в набор слов в них.
 */
fun initWords(lParagraphs: String): ArrayList<String> {
    val lWords = lParagraphs.lowercase().split(" ", ",", "–", ":", "«", "»", "\n", ".", "?!", "!?", "?", "!") as java.util.ArrayList<String>

    lWords.removeIf { word -> word == "" }

    return lWords
}

/*
 * Создание списка количества появлений слов в абзаце.
 */
fun initWordOccurrenceList(lWords: ArrayList<String>): ArrayList<WordList> {
    val lWordOccurrenceList: ArrayList<WordList> = ArrayList()
    var trigger: Boolean

    for (word in lWords) {
        trigger = false
        for (uniqueWord in lWordOccurrenceList) {
            if (checkWordEquality(uniqueWord.word, word)) {
                uniqueWord.weight += 1
                trigger = true
                break
            }
        }
        if (!trigger) {
            lWordOccurrenceList.add(WordList(word, 1.0))
        }
    }

    return lWordOccurrenceList
}

/*
 * Создание списка количества появлений слов во всём тексте.
 */
fun initGlobalWordOccurrenceList(paragraphs: ArrayList<Paragraph>): ArrayList<WordList> {
    val lGlobalWordOccurrenceList: ArrayList<WordList> = ArrayList()
    var trigger: Boolean

    //Проход по абзацам
    for (paragraph in paragraphs) {
        //Проход по словам в абзацах
        for (wordListElem in paragraph.wordWeightList) {
            trigger = false
            //Проход по списку слов в глобальном реестре слов
            for (globalWordListElem in lGlobalWordOccurrenceList) {
                //Если найдено совпадение, то добавляем к глобальному счётчику слов счётчик слов из абзаца
                if (checkWordEquality(wordListElem.word, globalWordListElem.word)) {
                    globalWordListElem.weight += wordListElem.weight
                    trigger = true
                    break
                }
            }
            //Иначе добавляем новое слово с количеством локальных повторений в список
            if (!trigger) {
                lGlobalWordOccurrenceList.add(WordList(wordListElem.word, wordListElem.weight))
            }
        }
    }

    return lGlobalWordOccurrenceList
}
