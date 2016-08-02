package fruit

object Checkout {

  /**
    * Prints the costs as a table
    */
  def printCost(items: Seq[Fruit]) {
    val maxLength = {10 :: items.map(_.toString.size).toList}.max + 1
    def line(label: String, money: BigDecimal) {
      val pad = " " * (maxLength - label.size)
      println(f"${label}${pad}£${money}%.2f")
    }

    items.foreach{ i => line(i.toString, i.value) }

    println("=" * (maxLength + 8))
    line("Total Cost", items.value)
  }

  /**
    * Parses arguments looking for a space separated list of products and prints
    * out the table of costs. Could be improved by moving the parsing logic out
    * and using a Reads typeclass.
    */
  def main(args: Array[String]) {
    val fruit = args.flatMap{raw =>
      raw.toLowerCase match {
      case "apple" => Some(Apple)
      case "orange" => Some(Orange)
      case _ =>
        System.err.println(s"Warning - unknown item '$raw'")
        None
    }}
    printCost(fruit)
  }

}
