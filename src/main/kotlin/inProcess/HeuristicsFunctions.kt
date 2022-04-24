package inProcess

/*
 * Функция-затычка для сравнения слов.
 * Затычка лишь потому, что тут буквально производится посимвольное сравнение.
 * Возможно, заменю на более эффективную.
 */
fun checkWordEquality(keyWord: String, word: String): Boolean {
    return keyWord == word
}

fun calculateWordWeight(keyWord: Double, globalWord: Double): Double {
    return keyWord / globalWord
}