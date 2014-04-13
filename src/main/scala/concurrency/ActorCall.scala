package concurrency

import java.net.Socket
import akka.actor.{Actor, Props, ActorSystem}
import org.mashupbots.socko.rest.RestResponseSerializer
import scala.concurrent.{Await, ExecutionContext}
import ExecutionContext.Implicits.global
import scala.concurrent.duration._

case class MakeRequest(socket : Socket, url : String)

class ActorCall extends ServiceClient {
  var system = ActorSystem("ActorCall")

  override def Run(socket: Socket, url: String): Unit = {
    system.actorOf(Props[RequestActor]) ! MakeRequest(socket, url)
  }
}

class RequestActor extends Actor with RequestResponse {
  override def receive: Actor.Receive = {
    case MakeRequest(socket, url) => {
      for {
        resp <- Request(url)
      } yield {
        Response(socket, resp)
      }

      context.stop(self)
    }
  }
}
