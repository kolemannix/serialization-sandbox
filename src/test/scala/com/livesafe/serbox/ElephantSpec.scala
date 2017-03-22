package com.livesafe.serbox

import akka.actor.{ ActorSystem, Props }
import akka.testkit.{ ImplicitSender, TestKit }
import akka.pattern.ask
import scala.concurrent.duration._

import akka.util.Timeout
import com.livesafe.serbox.Elephant.{ GetMemory, Memory }
import com.livesafe.serbox.Sight._
import com.typesafe.config.ConfigFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{ BeforeAndAfterAll, FreeSpecLike, Matchers }

class ElephantSpec extends TestKit(ActorSystem("ElephantSpec"))
  with FreeSpecLike
  with BeforeAndAfterAll
  with ImplicitSender
  with Matchers
  with ScalaFutures {

  implicit val timeout = Timeout(3 second)

  val elephant = system.actorOf(Props(new Elephant()), "elephant")
  watch(elephant)

  "Elephant" - {
    "should remember" in {
//      elephant ! ShereKhan
//      elephant ! Mogli
      elephant ! OtherElephant("tom")
//      elephant ! OtherElephant("bob")

      elephant ! GetMemory
      expectMsgPF() {
        case Memory(m) =>
          println(m)
          m should not be empty
      }

    }
  }

  override def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

}
