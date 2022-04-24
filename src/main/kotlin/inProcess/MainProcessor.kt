package inProcess

import Paragraph
import WordList

fun makeWordListWeight(lParagraphs: ArrayList<Paragraph>, lGlobalWordOccurrenceList: ArrayList<WordList>) {
    for (paragraph in lParagraphs) {
        for (globalWordListElem in lGlobalWordOccurrenceList) {
            for (wordListElem in paragraph.wordWeightList) {
                if (checkWordEquality(wordListElem.word, globalWordListElem.word)) {
                    wordListElem.weight = calculateWordWeight(wordListElem.weight, globalWordListElem.weight)
                }
            }
        }
    }
}