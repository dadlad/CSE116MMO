package theGame
import physics.{Physics, PhysicsVector, World}
import play.api.libs.json.{JsValue, Json}
class Game {
  val world: World = new World(10)
  var lastUpdateTime: Long = System.nanoTime()
  var playerSize: Double = 30.0
  var grid: Grid = new Grid
  var blobs: List[Blob] = List()
  var players: Map[String, Player] = Map()
  val player: Player = new Player(player.location, player.velocity)
  val Width: Double = 20
  val Height: Double = 30
  var Line: Double = -0.3
  def placeBlob(x: Int, y: Int): Unit = {
    blobs = new Blob(x, y) :: blobs
  }
  def spawn(): PhysicsVector = {
    new PhysicsVector(grid.Spawn.x + 0.5, grid.Spawn.y + 0.5)
  }
  def addPlayer(user: String): Unit = {
    val player = new Player(spawn(), new PhysicsVector(0, 0))
    players = players + (user -> player)
    world.objects = player :: world.objects
  }
  def removePlayer(user: String): Unit = {
    players(user).destroy()
    players -= user
  }
  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
    "gridSize" -> Json.toJson(Map("x" -> grid.gridWidth, "y" -> grid.gridHeight)),
    "spawn" -> Json.toJson(Map("x" -> grid.Spawn.x, "y" -> grid.Spawn.y)),
    "blobs" -> Json.toJson(this.blobs.map({ b => Json.toJson(Map("x" -> b.x, "y" -> b.y))})),
    "players" -> Json.toJson(this.players.map({ case (i, p) => Json.toJson(Map(
      "x" -> Json.toJson(p.location.x),
      "y" -> Json.toJson(p.location.y),
      "v_x" -> Json.toJson(p.velocity.x),
      "v_y" -> Json.toJson(p.velocity.y),
      "id" -> Json.toJson(i)))})),
    "playerSize" -> Json.toJson(playerSize)
    )
    Json.stringify(Json.toJson(gameState))
  }
  def update(): Unit = {
    val time: Long = System.nanoTime()
    val dt = (time - this.lastUpdateTime) / 1000000000.0
    Physics.updateWorld(this.world, dt)
    playerCollision(new Player(player.location, player.velocity), new Player(player.location, player.velocity))
    this.lastUpdateTime = time

  }
  def playerCollision(player: Player, player2: Player): Unit = {
    for(player <- players.values){
      for(player2 <- players.values){
        if(player.location.distance2d(player2.location) < playerSize){
          playerSize = playerSize + 30.0
          player2.destroy()
        }
      }
    }
  }
}



// square on circle collision (melee/projectile hit boxes are a square)
