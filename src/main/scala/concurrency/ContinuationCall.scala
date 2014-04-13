package concurrency

import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global
import java.io.PrintWriter
import java.net.Socket
import scala.util.Success

class ContinuationCall extends ServiceClient with RequestResponse {
  override def Run(socket : Socket, url : String) : Unit = {

    Request(url).onComplete(response =>
      response match {
        case Success(res) => Response(socket, res)
        case _ =>
      }
    )
  }
}
