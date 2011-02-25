package sleepingbarber

import scala.actors.Actor._
import scala.collection.{immutable, mutable}
import scala.util.Random

object BarbershopSimulator {
  private val random = new Random()
  private val customers = new mutable.ArrayBuffer[Customer]()
  private val shop = new Shop()

  def generateCustomers {
    for (i <- 1 to 20) {
      val customer = new Customer(i)
      customer.start()
      customers += customer
    }
  }

  // Customers arrive at random intervals
  def trickleCustomers {
    for (customer <- customers) {
      shop ! customer
      Thread.sleep(random.nextInt(450))
    }
  }

  def tallyCuts {
    // Wait for any remaining concurrent actions to complete
    Thread.sleep(2000)
    val shornCount = customers.filter(c => c.shorn).size
    println("[I] " + shornCount + " customers got haircuts today")
  }
  
  def main(args: Array[String]) {
    println("[I] starting barbershop simulation")
    shop.start()
    
    generateCustomers
    trickleCustomers
    tallyCuts

    System.exit(0)
  }

}
