import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import net.liftweb.json.Serialization.write
import net.liftweb.json._

import scala.io.Source

case class RestaurantData(
                           enName: String,
                           arName: String,
                           state: String,
                           routingMethod: Any,
                           logo: String,
                           coverPhoto: String,
                           enDescription: String,
                           arDescription: String,
                           shortNumber: String,
                           facebookLink: String,
                           twitterLink: String,
                           youtubeLink: String,
                           website: String,
                           onlinePayment: Boolean,
                           client: Boolean,
                           pendingInfo: Boolean,
                           pendingMenu: Boolean,
                           closed: Boolean
                         )

case class Restaurant(
                       uuid: String,
                       data: RestaurantData
                     )

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher


    val route =
      path("api" / "restaurant") {
        get {
          parameter("closed".?) {
            closed => {
              val restaurants = getRestaurants()
              if (closed.isEmpty) {
                complete(HttpEntity(ContentTypes.`application/json`, restaurants))
              }
              else {
                var isClosed = if (closed == Option("1")) true else false
                val restaurantsList = decodeRestaurants(restaurants)
                if (!isClosed) {
                  val restaurantsFiltered = restaurantsList.filter(x => x.data.closed == false)
                  val restaurantsFilteredText = encodeRestaurants(restaurantsFiltered.toList)
                  complete(HttpEntity(ContentTypes.`application/json`, restaurantsFilteredText))
                } else {
                  complete(HttpEntity(ContentTypes.`application/json`, restaurants))
                }
              }
            }
          }
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "0.0.0.0", 4000)

    println(s"Server online at http://localhost:4000/")
  }

  def readFile(filename: String): String = {
    return Source.fromFile(filename).getLines.mkString
  }

  def getRestaurants(): String = readFile("data.json")

  def decodeRestaurants(restaurantsText: String) = {
    implicit val formats = DefaultFormats
    val jValue = parse(restaurantsText)
    val result = jValue.extract[List[Restaurant]]
    result.toArray
  }

  def encodeRestaurants(restaurants: List[Restaurant]) = {
    implicit val formats = DefaultFormats
    val jsonString = write(restaurants)
    jsonString
  }
}