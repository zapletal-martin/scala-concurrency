package concurrency

import java.net.Socket
import scala.util.Success
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

class FutureCompositionCall extends ServiceClient with RequestResponse  {
  override def Run(socket: Socket, url: String): Unit = {
      Request(url).map(response => Response(socket, response))
  }
}
