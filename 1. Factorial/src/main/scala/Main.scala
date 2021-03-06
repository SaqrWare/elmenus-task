object Main {

  def main(args: Array[String]): Unit = {
    println(factorial(5))
    println(factorialRecursive(5))
  }

  // Factorial function
  def factorial(n: Int) = {
    var result = 1
    for (i <- 1 to n)
      result = result * i
    result
  }

  // With recursion
  def factorialRecursive(n: Int): Int = {
    if (n <= 0) 1
    else n * factorialRecursive(n - 1)
  }
}