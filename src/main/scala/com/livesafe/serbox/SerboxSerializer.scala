package com.livesafe.serbox

import akka.actor.ExtendedActorSystem
import com.esotericsoftware.kryo.io.Output
import com.livesafe.serbox.Sight._
import com.twitter.chill
import com.twitter.chill.akka.ActorRefSerializer
import com.twitter.chill.{ KryoInstantiator, ScalaKryoInstantiator }

class SerboxSerializer(system: ExtendedActorSystem) extends chill.akka.AkkaSerializer(system) {

  class SerboxKryoInstantiator extends ScalaKryoInstantiator {
    override def newKryo = {
      val k = super.newKryo
      val reg = k.getRegistration(classOf[Sight.OtherElephant])
      println("Elephant reg:" + reg)
      k
    }
  }

  override def kryoInstantiator: KryoInstantiator =
    (new SerboxKryoInstantiator)
      .withRegistrar(new ActorRefSerializer(system))
//      .withRegistrar(new DateTimeSerializer)
//      .withRegistrar(new UrlSerializer)

}
