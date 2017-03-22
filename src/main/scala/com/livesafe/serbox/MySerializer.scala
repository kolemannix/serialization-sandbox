package com.livesafe.serbox

import akka.actor.ExtendedActorSystem
import akka.serialization.Serializer

/**
 * Created by kolemannix (mailto:koleman@livesafemobile.com).
 */
class MySerializer(system: ExtendedActorSystem) extends Serializer {

  def includeManifest: Boolean = false
  def identifier = 8675309
  def toBinary(obj: AnyRef): Array[Byte] = Array.emptyByteArray
  def fromBinary(bytes: Array[Byte], clazz: Option[Class[_]]): AnyRef =
    throw new Exception("Custom deserialization failed")

}
