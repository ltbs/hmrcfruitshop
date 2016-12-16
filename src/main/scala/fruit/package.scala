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

    /**
      * Prints the costs as a table
      */
    def printTable {
      val maxLength = {10 :: s.map(_.toString.size).toList}.max + 1

      def line(label: String, money: BigDecimal) {
        val pad = " " * (maxLength - label.size)
        println(f"${label}${pad}£${money}%.2f")
      }

      s.foreach{ i => line(i.toString, i.value) }

      println("=" * (maxLength + 8))
      line("Total Cost", s.value)
    }
    
  }
}
