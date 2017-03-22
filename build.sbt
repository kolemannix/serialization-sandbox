name := "SerializationSandbox"

val akka = "2.4.11"
val scala = "2.11.8"
val typesafeConfig = "1.3.0"
val chill = "0.8.0"

libraryDependencies ++= Seq(
  "com.twitter" %% "chill" % chill,
  "com.twitter" %% "chill-akka" % chill,
  "com.typesafe" % "config" % typesafeConfig,
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "com.typesafe.akka" %% "akka-persistence" % akka,
  "com.typesafe.akka" %% "akka-testkit" % akka % "test",
  "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.21"
)

version := "1.0"

scalaVersion := "2.11.8"
    