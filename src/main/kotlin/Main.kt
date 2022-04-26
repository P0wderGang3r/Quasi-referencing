import inProcess.*
import logStart.*

fun printTextSummary(textSummary: ArrayList<String>) {
    for (paragraph in textSummary) {
        println("$paragraph\n")
    }
}

/*
 * "Тихий" запуск
 */
fun start(path: String): ArrayList<String> {
    val paragraphStructs: ArrayList<ParagraphStruct> = ArrayList()

    //Читаем текст из файла
    val text = initText(path)

    //Разбиваем текст на абзацы
    val lParagraphs = initParagraphs(text)
    lParagraphs.removeIf { paragraph -> paragraph.isEmpty() }

    for (paragraph in lParagraphs) {
        //Создаём реестр слов в каждом из абзацев
        val words = initWords(paragraph)
        //Создаём реестр уникальных слов с количествами повторений
        val wordOccurrenceList = initWordOccurrenceList(words)
        //Считаем количество слов в абзаце
        val wordAmount = initWordAmount(wordOccurrenceList)
        paragraphStructs.add(ParagraphStruct(paragraph, wordOccurrenceList, wordAmount,0.0))
    }

    //Создаём набор пар связанных абзацев
    val paragraphConnections = makeSemanticConnections(paragraphStructs)

    //Создаём реестр уникальных слов во всём тексте
    val globalWordOccurrenceList = initGlobalWordOccurrenceList(paragraphStructs)
    //Переводим количество повторений слов в их значимость
    wordOccurrenceToWeight(paragraphStructs, globalWordOccurrenceList)
    //Обновляем значимость абзаца
    updateParagraphWeight(paragraphStructs)

    globalWordOccurrenceList.clear()

    //Создаём кусты абзацев
    val bushes: ArrayList<BushStruct> = makeBushList(paragraphConnections)

    //Возвращаем готовый реферат
    return makeTextSummary(bushes)
}

/*
 * Запуск с выводом результатов действий
 */
fun logStart(path: String): ArrayList<String> {
    val paragraphStructs: ArrayList<ParagraphStruct> = ArrayList()

    //Читаем текст из файла
    val text = logInitText(path)

    //Разбиваем текст на абзацы
    val lParagraphs = logInitParagraphs(text)
    lParagraphs.removeIf { paragraph -> paragraph.isEmpty() }

    for (paragraph in lParagraphs) {
        //Создаём реестр слов в каждом из абзацев
        val words = logInitWords(paragraph)
        //Создаём реестр уникальных слов с количествами повторений
        val wordOccurrenceList = logInitWordOccurrenceList(words)
        //Считаем количество слов в абзаце
        val wordAmount = logInitWordAmount(wordOccurrenceList)
        paragraphStructs.add(ParagraphStruct(paragraph, wordOccurrenceList, wordAmount,0.0))
    }

    //Создаём набор пар связанных абзацев
    val paragraphConnections = logMakeSemanticConnections(paragraphStructs)

    //Создаём реестр уникальных слов во всём тексте
    val globalWordOccurrenceList = logInitGlobalWordOccurrenceList(paragraphStructs)
    //Переводим количество повторений слов в их значимость
    wordOccurrenceToWeight(paragraphStructs, globalWordOccurrenceList)
    //Обновляем значимость абзаца
    updateParagraphWeight(paragraphStructs)
    logParagraphStatus(paragraphStructs)

    globalWordOccurrenceList.clear()

    //Создаём кусты абзацев
    val bushes: ArrayList<BushStruct> = logMakeBushList(paragraphConnections)

    //Возвращаем готовый реферат
    return makeTextSummary(bushes)
}

/*
 * На вход подаём единственным параметром путь до файла,
 * из которого будет произведено чтение.
 * Иначе bruhгер ввиду отсутствия файла.
 */
fun main(args: Array<String>) {
    if (args.size != 1)
        return

    val textSummary = if (!isLogStart)
        start(args[0])
    else
        logStart(args[0])

    printTextSummary(textSummary)
}