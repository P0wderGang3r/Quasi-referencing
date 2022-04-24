
fun logParagraphStatus(paragraphs: ArrayList<Paragraph>) {
    if (isLogParagraphStatus) {
        println("logParagraphStatus")
        for (paragraph in paragraphs) {
            println("vvvvvvvvvvvvvvvvvvvvv")
            for (word in paragraph.wordWeightList)
                println(word.word + " " + word.weight)
            println("^^^^^^^^^^^^^^^^^^^^^")
        }
    }
}

