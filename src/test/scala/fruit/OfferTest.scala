package fruit

import org.scalacheck._
import Prop.forAll
import FruitSpecification.arbFruit
import Checkout.offers
import scala.util.Random.shuffle

object OfferSpecification extends Properties("Offer") {

  // offers should never reduce the cost below zero
  property("noTotalDiscounts") = forAll {
    (xs: List[Fruit]) => xs.applyOffers(offers).value >= 0
  }

  // offers should never increase the cost
  property("discountsReduceCost") = forAll {
    (xs: List[Fruit]) => xs.applyOffers(offers).value <= xs.value
  }

  // the order of the items in the basket should not affect the offers
  property("orderInsensitive") = forAll { (xs: List[Fruit]) =>
    xs.applyOffers(offers).value == shuffle(xs).applyOffers(offers).value
  }

  // nthFree should always discount the cheaper item
  property("cheapestFirst") = forAll { (fs: List[Fruit]) => fs.isEmpty || {
    val cheapest = fs.sortBy(_.value).head
    fs.applyOffers(Seq(
      Offer.nthFree(fs.size, fs :_*)
    )).value + cheapest.value == fs.value
  }}

}
