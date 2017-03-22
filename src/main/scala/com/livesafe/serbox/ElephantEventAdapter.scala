package com.livesafe.serbox

import akka.actor.ExtendedActorSystem
import akka.persistence.journal.{ EventAdapter, EventSeq }

/**
 * Created by kolemannix on 10/26/16.
 */
class ElephantEventAdapter(system: ExtendedActorSystem) extends EventAdapter {
  override def manifest(event: Any) = ""

  println("Hii")
  println("Hii")
  println("Hii")

  override def toJournal(event: Any): Any = event match {
    case _ => println("toJournal(" + event); event
  }

  override def fromJournal(event: Any, manifest: String): EventSeq = event match {
    case all => println("fromJournal(" + all + ")"); EventSeq.single(all)
  }

}
