package object fruit {

  type OfferLine = Seq[Valuable] => Option[Offer]

  /**
    * Enriched implicit class for sequences to add logic pertaining to valuables
    */
  implicit class ValuableSeq(s: Seq[Valuable]) {

    /**
      * Total value of all Valuable items in the sequence
      */
    def value = s.map(_.value).sum

    /**
      * Adds any applicable offers returning a new sequence. Removes any
      * existing offers beforehand (for idempotence).
      */
    def applyOffers(
      offers: Seq[OfferLine]
    ): Seq[Valuable] = {
      val offersRemoved = s.filter {
        case _: Offer => false
        case _ => true
      }
      offersRemoved ++ offers.map{_(offersRemoved)}.flatten
    }
  }
}
