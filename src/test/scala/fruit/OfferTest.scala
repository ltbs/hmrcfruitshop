package fruit

import org.scalacheck._
import Prop.forAll
import FruitSpecification.arbFruit
import Checkout.offers

object OfferSpecification extends Properties("Offer") {

  property("noTotalDiscounts") = forAll {
    (xs: List[Fruit]) => xs.applyOffers(offers).value >= 0
  }

  property("discountsReduceCost") = forAll {
    (xs: List[Fruit]) => xs.applyOffers(offers).value <= xs.value
  }

}
