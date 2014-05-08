import concurrency._
import server.Server

object main {
  def main (args : Array [String]) : Unit = {
    //new Server().run(new SynchronousCall());
    //new Server().run(new ContinuationCall());
    //new Server().run(new AsyncAwaitCall());
    //new Server().run(new ActorCall());
    //new Server().run(new RxCall());
    new Server().run(new FutureCompositionCall());
  }
}
