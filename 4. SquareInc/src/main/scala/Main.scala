object Main {
  def add(x: Int, y: Int) = x + y


  def main(args: Array[String]): Unit = {
    val h = compose(square, inc)
    println(h(6))
    println(h(2))
  }

  // Increament
  def inc(num: Int) = num + 1

  // Square
  def square(num: Int) = num * num

  // Compose function
  def compose(f: (Int) => Int, g: (Int) => Int) = (x: Int) => f(g(x))

}