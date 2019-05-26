object Main {
  def add(x: Int, y: Int) = x + y


  def main(args: Array[String]): Unit = {
    println(factorial(5))
  }

  //Factorial function
  def factorial(n: Int) = {
    var result = 1
    for (i <- 1 to n)
      result = result * i
    result
  }
}