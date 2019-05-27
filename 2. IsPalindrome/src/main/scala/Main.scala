object Main {
  def add(x: Int, y: Int) = x + y


  def main(args: Array[String]): Unit = {

    println(isPalindromeEasy("Anna"))
    println(isPalindrome("Anna"))
  }

  // easy way
  def isPalindromeEasy(word: String): Boolean = word.toLowerCase() == word.toLowerCase().reverse

  // Half steps
  def isPalindrome(word: String): Boolean = {
    val wordLength = word.length
    val halfLength = (word.length / 2).toInt
    for (i <- 0 to halfLength) {
      if (Character.toLowerCase(word(i)) != Character.toLowerCase(word(wordLength - 1 - i)))
        return false
    }
    return true
  }

}