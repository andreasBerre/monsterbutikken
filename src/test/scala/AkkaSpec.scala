import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfterAll, Matchers}
import scala.concurrent.duration._


object AkkaSpec {
  def getTestSystem(): ActorSystem = {
    // Replace normal event handler with test event handler supporting
    // expecting exceptions and log messages during tests
    val config = ConfigFactory.parseString(
      """akka.loggers = ["akka.testkit.TestEventListener"]
        akka.persistence.journal.plugin = "akka.persistence.journal.leveldb"
        akka.persistence.snapshot-store.plugin = "akka.persistence.snapshot-store.local"
      """.stripMargin)

    ActorSystem("testSystem", config)
  }
}

abstract class AkkaSpec extends TestKit(AkkaSpec.getTestSystem())
with FlatSpecLikeHelper
with ImplicitSender
with BeforeAndAfterAll
with MockitoSugar
with Matchers {

  implicit val timeout = Timeout(3 seconds)

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
}
