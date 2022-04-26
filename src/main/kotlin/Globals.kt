const val paragraphEqualityThreshold = 0.2

const val maximumDistanceBetweenParagraphs = 5
const val maximumParagraphsInBush = 10

data class WordStruct(var word: String, var weight: Double)

data class ParagraphStruct(val paragraph: String, val wordWeightList: ArrayList<WordStruct>, val wordAmount: Double, var paragraphWeight: Double)

data class ParagraphConnectionsStruct(val firstParagraph: ParagraphStruct, val secondParagraph: ParagraphStruct, val connectionCoefficient: Double)

data class BushStruct(val paragraphPairs: ArrayList<ParagraphConnectionsStruct>, var paragraphAmount: Int)