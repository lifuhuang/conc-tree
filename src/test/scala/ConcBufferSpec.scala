import org.scalatest._

/**
  * Created by Leo on 2016/10/23.
  */
class ConcBufferSpec extends FlatSpec{
  "A ConcBuffer" can "be constructed from range 0 until 100000" in {
    val cb = (0 until 100000).par.aggregate(ConcBuffer[Int]())(_ += _, _ combine _)
    val arr = Conc.traverse(cb.result())
    assert(arr.length == 100000)
    for (i <- 0 until 100000) {
      assert(arr(i) == i)
    }
  }
}
