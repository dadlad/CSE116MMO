package theGame

import play.api.libs.json.{JsValue, Json}
import physics.PhysicsVector
class Blob(val x: Int, val y: Int) extends GameObject{
  val game: Game = new Game()
  def compareDistances(players: Map[String, JsValue], players2: Map[String, JsValue]): Boolean = {
    val x = players("x").as[Double]
    val y = players("y").as[Double]
    val x_2 = players2("x").as[Double]
    val y_2 = players2("y").as[Double]
    new PhysicsVector(x, y).distance2d(new PhysicsVector(this.x + .5, this.y + .5)) < new PhysicsVector(x_2, y_2).distance2d(new PhysicsVector(this.x + .5, this.y + .5))
  }
  def eaten(jsonGameState: String): Unit = {
    var blobList: List[Blob] = List()
    val parsed: JsValue = Json.parse(jsonGameState)
    var players: List[Map[String, JsValue]] = (parsed \ "players").as[List[Map[String, JsValue]]]
  }
}
