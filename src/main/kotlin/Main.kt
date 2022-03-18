import inProcess.*
import logStart.*

const val isLogStart = true

var text: String = ""
lateinit var sentences: List<String>
var words: ArrayList<ArrayList<String>> = ArrayList()

fun start(path: String): Int {
    text = initText(path)
    sentences = initSentences(text)
    words = initWords(sentences)

    return 0
}

fun logStart(path: String): Int {
    text = logInitText(path)
    sentences = logInitSentences(text)
    words = logInitWords(sentences)

    return 0
}

/*
 * На вход подаётся единственным параметром путь до файла,
 * из которого будет произведено чтение.
 */
fun main(args: Array<String>) {
    if (args.size != 1)
        return

    if (!isLogStart)
        start(args[0])
    else
        logStart(args[0])
}