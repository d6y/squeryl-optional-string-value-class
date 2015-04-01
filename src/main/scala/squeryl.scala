import org.squeryl._
import org.squeryl.dsl._

object SquerylEntrypoint extends PrimitiveTypeMode {

  // Don't do this...
  // case class PlanetName(value: String) extends AnyVal

  // But this is fine:
  case class PlanetName(value: String)

  implicit val planetNameTEF = new NonPrimitiveJdbcMapper[String, PlanetName, TString](stringTEF, this) {
    def convertFromJdbc(v: String) = new PlanetName(v)
    def convertToJdbc(v: PlanetName) = v.value
  }
  implicit def planetNamePKToTE(v: PlanetName) = planetNameTEF.create(v)

  implicit val optionPlanetNameTEF =
    new TypedExpressionFactory[Option[PlanetName], TOptionString] with DeOptionizer[String, PlanetName, TString, Option[PlanetName], TOptionString] {
      val deOptionizer = planetNameTEF
    }
  implicit def optionPlanetNameToTE(s: Option[PlanetName]) = optionPlanetNameTEF.create(s)
}

import SquerylEntrypoint._

object SolarSystem extends Schema {
  case class World(planetName: Option[PlanetName])
  val worlds = table[World]("world")
}

object Main extends App {

  import SolarSystem._

  DB.init()

  inTransaction {
    Session.currentSession.setLogger( println )

    val earth: Option[PlanetName] = Some(new PlanetName("Earth"))
    worlds.insert( World(earth) )

    println(
      from(worlds)(w => select(w)).headOption
    )
  }
}


object DB {

  def init() : Unit = {
    import org.squeryl.SessionFactory
    import org.squeryl.Session
    import org.squeryl.adapters.PostgreSqlAdapter

    Class.forName("org.postgresql.Driver")
    SessionFactory.concreteFactory = Some(()=>
      Session.create(
        java.sql.DriverManager.getConnection("jdbc:postgresql:scratch", System.getProperty("user.name"), ""),
        new PostgreSqlAdapter))
  }
}