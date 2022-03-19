import inProcess.*
import logStart.*

var text: String = ""
lateinit var sentences: List<String>
var words: ArrayList<ArrayList<String>> = ArrayList()
var keyWordList: ArrayList<KeyWordStruct> = ArrayList()

var keyWordMatrix: Array<Array<Int>> = arrayOf()
var jointOccurrenceMatrix: Array<Array<Int>> = arrayOf()

fun start(path: String): Int {
    text = initText(path)
    sentences = initSentences(text)
    words = initWords(sentences)
    keyWordList = initKeyWordList(words)
    keyWordMatrix = initKeyWordMatrix(keyWordList)
    jointOccurrenceMatrix = initJointOccurrenceMatrix(keyWordList, words)

    return 0
}

fun logStart(path: String): Int {
    text = logInitText(path)
    sentences = logInitSentences(text)
    words = logInitWords(sentences)
    keyWordList = logInitKeyWordList(words)
    keyWordMatrix = logInitKeyWordMatrix(keyWordList)
    jointOccurrenceMatrix = logInitJointOccurrenceMatrix(keyWordList, words)

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