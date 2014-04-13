package concurrency

import java.net.Socket

trait ServiceClient {
  def Run(socket : Socket, url : String) : Unit
}

