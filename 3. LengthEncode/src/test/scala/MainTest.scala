import org.scalatest._

class MainTest extends FunSuite {
  test("Should encode word with length encode") {
    assert(Main.runLengthEncode("aaaaaaaaaabbbaxxxxyyyzyx") == "a10b3a1x4y3z1y1x1")
  }
  test("Should decode word with length decode") {
    assert(Main.runLengthDecode("a10b3a1x4y3z1y1x1") == "aaaaaaaaaabbbaxxxxyyyzyx")
  }


}
