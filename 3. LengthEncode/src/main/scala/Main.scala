object Main {
  
  def main(args: Array[String]): Unit = {
    println(runLengthEncode("aaaaaaaaaabbbaxxxxyyyzyx"))
    println(runLengthDecode("a10b3a1x4y3z1y1x1"))
  }

  // Encode
  def runLengthEncode(text: String): String = {
    var result = ""
    var lastCount = 1
    for (i <- 1 until text.length) {
      if (text(i) != text(i - 1)) {
        result = result + text(i - 1) + lastCount
        lastCount = 1

        // if last item is not equal to the one before
        if (i == text.length - 1)
          result = result + text(i) + lastCount
      } else {
        lastCount += 1
      }
    }
    return result
  }

  // Decode
  def runLengthDecode(text: String): String = {
    val charPattern = "[a-z]|\\d+".r
    var result = ""

    // split nums & letters with regex
    val charList = charPattern.findAllIn(text).toArray

    for (i <- 1 until charList.length by 2) {
      for (x <- 1 to charList(i).toInt)
        result += charList(i - 1)

    }
    
    return result
  }
}