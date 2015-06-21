import com.trueaccord.scalapb.{ScalaPbPlugin => PB}

PB.protobufSettings

name := "monsterbutikken"

scalaVersion := "2.11.6"

sbtVersion := "0.13.8"

fork := true

val jettyVersion = "9.2.3.v20140905"

val slf4jVersion = "1.7.7"

val jacksonVersion = "2.4.3"

dependencyOverrides += "org.scala-lang" % "scala-library" % scalaVersion.value


val akka_version: String = "2.3.10"

libraryDependencies ++= Seq("com.typesafe.akka" %% "akka-actor" % akka_version ,
  "com.typesafe.akka" %% "akka-persistence-experimental" % akka_version,
  "com.geteventstore" %% "akka-persistence-eventstore" % "2.0.2"
)

libraryDependencies += "org.iq80.leveldb"            % "leveldb"          % "0.7"

libraryDependencies += "org.fusesource.leveldbjni"   % "leveldbjni-all"   % "1.8"

libraryDependencies ++= Seq(
  "org.springframework" % "spring-webmvc" % "4.1.1.RELEASE",
  "org.eclipse.jetty" % "jetty-server" % jettyVersion,
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion,
  "org.eclipse.jetty" % "jetty-annotations" % jettyVersion,
  "org.eclipse.jetty" % "jetty-plus" % jettyVersion,
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % Provided,
  "org.slf4j" % "log4j-over-slf4j" % slf4jVersion,
  "org.slf4j" % "slf4j-api" % slf4jVersion,
  "org.slf4j" % "jcl-over-slf4j" % slf4jVersion,
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
)

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.5" % Test

libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % akka_version % Test

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % Test

libraryDependencies += "net.sandrogrzicic" %% "scalabuff-runtime" % "1.4.0"


libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.6"

libraryDependencies += "org.apache.httpcomponents" % "fluent-hc" % "4.3.6"
