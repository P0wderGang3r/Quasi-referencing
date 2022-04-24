import inProcess.*
import logStart.*

var paragraphs: ArrayList<Paragraph> = ArrayList()

var text: String = ""

/*
 * "Тихий" запуск
 */
fun start(path: String): Int {
    text = initText(path)
    val lParagraphs = initParagraphs(text)

    lParagraphs.removeIf { paragraph -> paragraph.isEmpty() }

    for (paragraph in lParagraphs) {
        val words = initWords(paragraph)
        val wordOccurrenceList = initWordOccurrenceList(words)
        paragraphs.add(Paragraph(paragraph, words, wordOccurrenceList))
    }

    val globalWordOccurrenceList = initGlobalWordOccurrenceList(paragraphs)

    makeWordListWeight(paragraphs, globalWordOccurrenceList)

    return 0
}

/*
 * Запуск с выводом результатов действий
 */
fun logStart(path: String): Int {
    text = logInitText(path)

    val lParagraphs = logInitParagraphs(text)
    lParagraphs.removeIf { paragraph -> paragraph.isEmpty() }

    for (paragraph in lParagraphs) {
        val words = logInitWords(paragraph)
        val wordOccurrenceList = logInitWordOccurrenceList(words)
        paragraphs.add(Paragraph(paragraph, words, wordOccurrenceList))
    }

    val globalWordOccurrenceList = logInitGlobalWordOccurrenceList(paragraphs)

    makeWordListWeight(paragraphs, globalWordOccurrenceList)
    logParagraphStatus(paragraphs)

    return 0
}

/*
 * На вход подаём единственным параметром путь до файла,
 * из которого будет произведено чтение.
 * Иначе bruhгер ввиду отсутствия файла.
 */
fun main(args: Array<String>) {
    if (args.size != 1)
        return

    if (!isLogStart)
        start(args[0])
    else
        logStart(args[0])
}