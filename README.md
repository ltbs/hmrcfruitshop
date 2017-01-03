# hmrcfruitshop
Demo Scala project

## To run - 
```bash 
sbt "run Apple Apple Orange"
sbt test
```

## REPL usage - 
```scala
import fruit._
val costOfBanana = Banana.value
val costOfFruitSalad = Seq(Apple, Orange, Banana).value

// Example of offer where buying 3 apples or bananas gives the cheapest free
val myOffer = Offer.nthFree(3, Apple, Banana) _
val threeBananas = Seq(Banana, Banana, Banana).applyOffers(Seq(myoffer))
val costOf2Bananas = threeBananas.value

// Display table of line items (showing discounts) to stdout
threeBananas.printTable
```
