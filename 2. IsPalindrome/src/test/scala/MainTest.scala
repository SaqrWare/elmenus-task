import org.scalatest._

class MainTest extends FunSuite {
  test("Should check is word palindrome") {
    assert(Main.isPalindrome("anna") == true)
    assert(Main.isPalindromeEasy("anna") == true)
  }
  test("Should check is word palindrome with false") {
    assert(Main.isPalindrome("banana") == false)
    assert(Main.isPalindromeEasy("banana") == false)
  }

  test("Should check is word palindrome with Uppercase ") {
    assert(Main.isPalindrome("Anna") == true)
    assert(Main.isPalindromeEasy("Anna") == true)
  }

}
