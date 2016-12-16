package fruit

import org.scalacheck._
import Prop.forAll
import Arbitrary._
import scala.util.Random.shuffle

object FruitSpecification extends Properties("Fruit") {

  implicit val arbFruit: Arbitrary[Fruit] = Arbitrary(Gen.oneOf(Apple,Orange))

  property("addingIncreasesCost") = forAll { (xs: List[Fruit], y: Fruit) =>
    (y :: xs).value > xs.value && (if (xs.isEmpty) xs.value == 0 else true)
  }

  property("addingIncreasesCostByCorrectAmount") = forAll { (xs: List[Fruit], y: Fruit) =>
    (y :: xs).value == (xs.value + y.value) && (if (xs.isEmpty) xs.value == 0 else true)
  }

  property("commutative") = forAll { (xs: List[Fruit]) =>
    shuffle(xs).value == xs.value
  }

}
