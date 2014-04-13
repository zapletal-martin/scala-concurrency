package concurrency

import java.net.Socket
import rx.lang.scala.Observable
import rx.lang.scala.Scheduler
import rx.lang.scala.schedulers._
import rx.lang.scala.JavaConversions
import rx.lang.scala.subjects._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

class RxCall extends ServiceClient with RequestResponse {
  val sub = ReplaySubject[SocketEvent]()
  sub.subscribe(x => { Request(x.url).onComplete(y => Response(x.socket, y.get)) })

  override def Run(socket: Socket, url: String): Unit = {
    emitEvent(socket, url)
  }

  def emitEvent(socket: Socket, url: String)= {
    sub.onNext(SocketEvent(socket, url))
  }
}

case class SocketEvent(socket: Socket, url: String)