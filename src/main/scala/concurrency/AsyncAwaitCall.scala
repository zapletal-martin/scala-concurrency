package concurrency

import java.net.Socket
import scala.async.Async.{async, await}
import dispatch.as.Response
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

class AsyncAwaitCall extends ServiceClient with RequestResponse {
  override def Run(socket: Socket, url: String): Unit = {
    async {
      val response = await(Request(url))
      Response(socket, response)
    }
  }
}
