package object fruit {

  /**
    * Enriched implicit class for sequences to add logic pertaining to fruits
    */
  implicit class FruitSeq(s: Seq[Fruit]) {

    /**
      * Total value of all Fruit items in the sequence
      */
    def value = s.map(_.value).sum

  }
}
