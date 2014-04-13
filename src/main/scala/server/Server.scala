package server

import concurrency.ServiceClient
import java.net.ServerSocket
import java.io.PrintWriter

class Server {
  def run (client : ServiceClient): Unit = {
    val port = 8080
    val listener = new ServerSocket(port)
    printf("Listening at port %1$d", port)

    Runtime.getRuntime.addShutdownHook(new Thread {
      override def run { listener.close() }
    })

    while (true) {
      val sock = listener.accept()

      client.Run(sock, "http://api.hostip.info/country.php")
    }
  }
}
