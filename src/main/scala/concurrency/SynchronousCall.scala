package concurrency

import dispatch._
import Defaults._
import scala.concurrent.duration._
import scala.concurrent.Await
import java.io.PrintWriter
import java.net.Socket

class SynchronousCall extends ServiceClient with RequestResponse {

  override def Run(socket : Socket, url: String): Unit = {
    val response = Await.result(Request(url), 5 seconds)
    Response(socket, response)
  }
}
