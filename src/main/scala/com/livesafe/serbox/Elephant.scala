package com.livesafe.serbox

import java.util.UUID

import akka.persistence.{ PersistentActor, RecoveryCompleted, SnapshotOffer }
import com.livesafe.serbox.Elephant.{ GetMemory, Memory }

//object ElephantSchema {
//  case class OtherElephant(name: String) extends Sight
//}

trait Sight
object Sight {
  case object Tree extends Sight
  case class OtherElephant(name: String, foo: Int = 1) extends Sight
  case object ShereKhan extends Sight
  case object RandomOne extends Sight
  case object RandomTwo extends Sight
  case object RandomThree extends Sight
  case object Mogli extends Sight
}
object Elephant {

  case object GetMemory

  case class Memory(sights: Seq[Sight]) {
    def see(sight: Sight) = copy(sights = sights :+ sight)
  }
}

/**
 * Created by kolemannix on 10/26/16.
 * "An elephant never forgets"
 */
class Elephant extends PersistentActor {
  def persistenceId = "Elephant1"
//  val persistenceId = "Elephant" + UUID.randomUUID()

  var memory: Memory = Memory(Seq.empty)

  override def postStop(): Unit = {
//    saveSnapshot(memory)
  }

  def remember(sight: Sight) = {
    memory = memory.see(sight)
  }

  val receiveRecover: Receive = {
    case sight: Sight => remember(sight)
    case SnapshotOffer(_, memories: Memory) =>
      println("Using snapshot" + memories)
      memory = memories
    case RecoveryCompleted =>
      println("Recovery Completed")
      println("Recovery Completed")
      println("Recovery Completed")
    case other =>
      println("Other recovery message: " + other)
      println("Other recovery message: " + other)
      println("Other recovery message: " + other)
  }

  val receiveCommand: Receive = {
    case sight: Sight =>
      println("Elephant sees " + sight)
      persist(sight)(remember)
    case GetMemory => sender() ! memory
    case msg => println("Elephant received " + msg)
  }

  override def onRecoveryFailure(cause: Throwable, event: Option[Any]): Unit = {
    println("RECOVERY FAILURE")
    println(cause)
    println(event)
  }



}
