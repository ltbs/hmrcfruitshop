package fruit

import Offer._

/**
  * The checkout must know the possible items ahead of time (for
  * parsing), and what offers are applicable.
  */
class Checkout(val knownItems: Valuable*)(val offers: OfferLine*) {

  lazy val itemsToParse: Map[String,Valuable] =
      knownItems.map{ x => (x.toString, x)}.toMap

  def parse(i: String): Option[Valuable] =
    itemsToParse.get(i).orElse {
      System.err.println(s"Warning - unknown item '$i'")
      None
    }

  def parseAll(ix: Seq[String]) : Seq[Valuable] = ix.flatMap(parse)
}

/** 
  * Main application entry point
  */
object Checkout extends Checkout (
  // Known Items
  Apple, Orange, Melon, Banana
)(
  // Applicable Offers
  nthFree(2, Apple, Banana), nthFree(3, Orange), nthFree(3, Melon)
) with App {
  parseAll(args).applyOffers(offers).printTable
}
