package concurrency

import dispatch._
import Defaults._
import java.io.{BufferedOutputStream, BufferedWriter, OutputStreamWriter, PrintWriter}
import java.net.Socket

trait RequestResponse {
  val OUTPUT_HEADERS = "HTTP/1.1 200 OK\r\n" +
    "Content-Type: text/html\r\n" +
    "Content-Length: ";
  val OUTPUT_END_OF_HEADERS = "\r\n\r\n";

  def Request(url : String) = {
    val req = dispatch.url(url)
    Http(req OK as.String)
  }

  def Response(socket : Socket, response : String) : Unit = {
    val out = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(socket.getOutputStream()), "UTF-8"))

    //this is required. Otherwise ab fails with Connection reset by peer 104
    val in = socket.getInputStream
    val outputByte = new Array[Byte](4096)
    in.read(outputByte, 0, 4096)

    out.write(OUTPUT_HEADERS + response.length() + OUTPUT_END_OF_HEADERS + response)
    out.flush()
    out.close()
  }
}
