package inProcess

import BushStruct
import ParagraphConnectionsStruct
import ParagraphStruct
import WordStruct
import maximumDistanceBetweenParagraphs
import maximumParagraphsInBush
import paragraphEqualityThreshold

/*
 * Приведение списка количества появлений слова в тексте к списку их весов.
 */
fun wordOccurrenceToWeight(paragraphStructs: ArrayList<ParagraphStruct>, globalWordOccurrenceList: ArrayList<WordStruct>) {
    for (paragraph in paragraphStructs) {
        for (globalWordListElem in globalWordOccurrenceList) {
            for (wordListElem in paragraph.wordWeightList) {
                if (checkWordEquality(wordListElem.word, globalWordListElem.word)) {
                    wordListElem.weight = calculateWordWeight(wordListElem.weight, globalWordListElem.weight)
                }
            }
        }
    }
}

/*
 * Функция, обновляющая значение веса значимости абзаца.
 */
fun updateParagraphWeight(paragraphStructs: ArrayList<ParagraphStruct>) {
    for (paragraph in paragraphStructs) {
        for (wordListElem in paragraph.wordWeightList) {
            paragraph.paragraphWeight += wordListElem.weight
        }
    }
}

/*
 * Функция, создающая список - карту сильно связанных абзацев.
 */
fun makeSemanticConnections(paragraphs: ArrayList<ParagraphStruct>): ArrayList<ParagraphConnectionsStruct> {
    val semanticConnections: ArrayList<ParagraphConnectionsStruct> = ArrayList()

    //Проходим по всему списку абзацев.
    for (firstIndex in 0 until paragraphs.size) {
        //Допускаем совпадение абзаца с самим собой, важно для удобства работы с пучками
        var secondIndex = firstIndex

        //Если второстепенный индекс не превышает количество абзацев и значение предела анализируемых абзацев, то
        // анализируем пару абзацев на предмет совпадения.
        while (secondIndex < paragraphs.size && secondIndex < firstIndex + maximumDistanceBetweenParagraphs) {
            val connectionCoefficient = calculateParagraphEquality(paragraphs[firstIndex], paragraphs[secondIndex])

            //Если при подсчёте коэффициента совпадения абзацев минимальный порог их совпадения был превышен,
            // то добавляем семантическую связь между параграфами.
            if (connectionCoefficient >= paragraphEqualityThreshold)
                semanticConnections.add(ParagraphConnectionsStruct(paragraphs[firstIndex], paragraphs[secondIndex], connectionCoefficient))
            secondIndex++
        }
    }

    return semanticConnections
}

/*
 * Функция, создающая наборы кустов из абзацев по факту их семантической схожести
 */
fun makeBushList(paragraphConnections: ArrayList<ParagraphConnectionsStruct>): ArrayList<BushStruct> {
    val lBushList: ArrayList<BushStruct> = ArrayList()

    //Проходимся по всем исходным парам абзацев
    for (paragraphsPair in paragraphConnections) {
        //Булева переменная, индицирующая, что абзац был куда-либо добавлен
        var isAddedSomewhere = false

        //Проходимся по всем кустам
        for (bush in lBushList) {

            //Булева переменная, индицирующая, что 
            var isFirstParagraphBelongsToBush = false

            //Проходимся по всем парам абзацев в кусте
            for (paragraphsPairInBush in bush.paragraphPairs) {

                //Если в паре первый элемент уже присутствует либо транзитивно, либо непосредственно,
                // то помечаем пару как пару, которую можно добавить в куст
                if (paragraphsPair.firstParagraph == paragraphsPairInBush.firstParagraph ||
                        paragraphsPair.firstParagraph == paragraphsPairInBush.secondParagraph) {
                    isFirstParagraphBelongsToBush = true
                }
            }

            //Если пара помечена на добавление и количество элементов в кусте меньше максимального количества,
            // то добавляем пару в куст
            if (isFirstParagraphBelongsToBush && bush.paragraphAmount < maximumParagraphsInBush) {
                bush.paragraphPairs.add(paragraphsPair)
                bush.paragraphAmount += 1
                isAddedSomewhere = true
                break
            }
        }

        //Если пара не была никуда добавлена, то создаём новый куст
        if (!isAddedSomewhere) {
            val newPairList: ArrayList<ParagraphConnectionsStruct> = ArrayList()
            newPairList.add(paragraphsPair)
            lBushList.add(BushStruct(newPairList, 1))
        }
    }

    return lBushList
}

/*
 * функция, которая:
 * проходит по bushes, вычисляет, что в связанном пучке является самым весомым,
 * добавляет самый весомый элемент пучка в конечный реферат
 */
fun makeTextSummary(bushes: ArrayList<BushStruct>): ArrayList<String> {
    val lTextSummary: ArrayList<String> = ArrayList()

    for (bush in bushes) {
        var lBestWeightedParagraphInBush = bush.paragraphPairs[0].firstParagraph

        for (paragraphPair in bush.paragraphPairs) {
            if (lBestWeightedParagraphInBush.paragraphWeight < paragraphPair.firstParagraph.paragraphWeight)
                lBestWeightedParagraphInBush = paragraphPair.firstParagraph
        }

        lTextSummary.add(lBestWeightedParagraphInBush.paragraph)
    }

    return lTextSummary
}