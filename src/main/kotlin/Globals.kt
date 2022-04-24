val isLogParagraphStatus = true

data class WordList(var word: String, var weight: Double)

data class Paragraph(var paragraph: String, var words: ArrayList<String>, var wordWeightList: ArrayList<WordList>)