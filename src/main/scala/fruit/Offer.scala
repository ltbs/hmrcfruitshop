package fruit

case class Offer(description: String, value: BigDecimal) extends Valuable {
  override def toString = description
}

object Offer {

  /**
    * Very crude pluralization - won't work for some nouns
    * (e.g. sheep) but is good for our fruit
    */
  private def pluralize(i: Valuable, qty: Int) = i.toString ++ {
    qty match {
      case 0 => ""
      case _ => "s"
    }}

  def nthFree(n: Int, itemsInOffer: Valuable*)(items: Seq[Valuable]) = {
    val applicableItems = items.filter{itemsInOffer contains _}

    val titleSub = itemsInOffer.map(pluralize(_,n)) match {
      case Nil => ""
      case Seq(x) => x
      case xs => Seq(
        xs.dropRight(2).mkString(", "),
        xs.takeRight(2).mkString(" or ")
      ).filter(_.nonEmpty).mkString(", ")
    }

    {applicableItems.size / n} match {
      case 0 => None
      case x => {
        val cheapest = applicableItems.sortBy(_.value).take(x)
        Some(Offer(s"Buy $n $titleSub get 1 free", -cheapest.value))
      }
    }
  }
}
