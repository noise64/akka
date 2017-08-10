name := "akka-sample-camel-scala"

version := "2.4.20"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.20",
  "com.typesafe.akka" %% "akka-camel" % "2.4.20",
  "org.apache.camel" % "camel-jetty" % "2.17.7",
  "org.apache.camel" % "camel-quartz" % "2.17.7",
  "org.slf4j" % "slf4j-api" % "1.7.16",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

licenses := Seq(("CC0", url("http://creativecommons.org/publicdomain/zero/1.0")))
