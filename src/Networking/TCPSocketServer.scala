package Networking
import java.net.InetSocketAddress

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import play.api.libs.json.{JsValue, Json}
class TCPSocketServer(gameActor: ActorRef) extends Actor  {
  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 8000))

  var webServers: Set[ActorRef] = Set()
  var buffer: String = ""
  val delimiter: String = "~"
  override def receive: Receive = {
    case b: Bound => println("Listening on port: " + b.localAddress.getPort)

    case c: Connected =>
      println("Client Connected: " + c.remoteAddress)
      this.webServers = this.webServers + sender()
      sender() ! Register(self)

    case PeerClosed =>
      println("Client Disconnected: " + sender())
      this.webServers = this.webServers - sender()

    case r: Received =>
      buffer += r.data.utf8String
      while (buffer.contains(delimiter)) {
        val curr = buffer.substring(0, buffer.indexOf(delimiter))
        buffer = buffer.substring(buffer.indexOf(delimiter) + 1)
      }

    case SendGameState =>
      gameActor ! SendGameState

    case gs: GameState =>
      this.webServers.foreach((client: ActorRef) => client ! Write(ByteString(gs.gameState + delimiter)))
  }
}
object TCPSocketServer {
  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem()
    import actorSystem.dispatcher

    import scala.concurrent.duration._

    val gameActor = actorSystem.actorOf(Props(classOf[GameActor]))
    val server = actorSystem.actorOf(Props(classOf[TCPSocketServer], gameActor))

    actorSystem.scheduler.schedule(16.milliseconds, 32.milliseconds, gameActor, UpdateGame)
    actorSystem.scheduler.schedule(32.milliseconds, 32.milliseconds, server, SendGameState)
  }
}
