import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import akka.http.scaladsl.Http
import scala.io.StdIn
import scala.concurrent.Future
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.PathMatcher
import akka.http.scaladsl.model.StatusCodes
import scala.io.Source
import io.circe.parser
import io.circe.generic.semiauto.deriveDecoder

//import spray.json.DefaultJsonProtocol._
//import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

object Main {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher
    case class Restaurant(enName: String,
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
                          closed: Boolean)

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
                println(isClosed)
                complete(HttpEntity(ContentTypes.`application/json`, restaurants))
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

  def decodeRestaurants() = {

  }
}