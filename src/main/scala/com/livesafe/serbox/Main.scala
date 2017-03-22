package com.livesafe.serbox

import akka.actor.ActorSystem
import com.typesafe.config.{ Config, ConfigFactory }


class Main extends App {
  val system = ActorSystem("serialization-sandbox", ConfigFactory.load())
}
