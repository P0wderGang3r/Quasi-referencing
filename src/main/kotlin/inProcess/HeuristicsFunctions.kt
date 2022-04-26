package inProcess

import ParagraphStruct

/*
 * Функция-затычка для сравнения слов.
 * Затычка лишь потому, что тут буквально производится посимвольное сравнение.
 * Возможно, заменю на более эффективную.
 */
fun checkWordEquality(firstWord: String, secondWord: String): Boolean {
    return firstWord == secondWord
}

/*
 * Функция подсчёта веса, или значимости, слова в абзаце.
 */
fun calculateWordWeight(keyWord: Double, globalWord: Double): Double {
    return keyWord / globalWord
}

/*
 * Функция подсчёта коэффициента совпадения абзацев друг с другом.
 * Для этого подсчитывается количество совпадающих между параграфами слов.
 * Если слова совпадают, то добавляем к метрике совпадений среднее арифметическое количество их появлений в двух параграфах.
 * Затем общую метрику делим на общее количество слов в параграфе.
 */
fun calculateParagraphEquality(firstParagraph: ParagraphStruct, secondParagraph: ParagraphStruct): Double {
    var paragraphEqualityMetric = 0.0

    for (firstParagraphWord in firstParagraph.wordWeightList) {
        for (secondParagraphWord in secondParagraph.wordWeightList) {
            if (checkWordEquality(firstParagraphWord.word, secondParagraphWord.word)) {
                paragraphEqualityMetric += ((firstParagraphWord.weight + secondParagraphWord.weight) / 2)
            }
        }
    }

    paragraphEqualityMetric /= ((firstParagraph.wordAmount + secondParagraph.wordAmount) / 2)

    return paragraphEqualityMetric
}