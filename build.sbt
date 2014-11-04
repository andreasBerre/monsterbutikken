name := "monstershopen"

version := "1.0"

scalaVersion := "2.11.2"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

val jettyVersion = "9.2.3.v20140905"
val springVersion = "4.1.1.RELEASE"
val slf4jVersion = "1.7.7"
val jacksonVersion = "2.4.3"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.3.6"

libraryDependencies += "com.geteventstore" %% "eventstore-client" % "1.0.1"

libraryDependencies += "org.springframework" % "spring-webmvc" % springVersion


libraryDependencies += "org.eclipse.jetty" % "jetty-server" % jettyVersion % "test"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "test"

libraryDependencies += "org.eclipse.jetty" % "jetty-annotations" % jettyVersion % "test"

libraryDependencies += "org.eclipse.jetty" % "jetty-plus" % jettyVersion % "test"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"

libraryDependencies += "org.slf4j" % "log4j-over-slf4j" % slf4jVersion

libraryDependencies += "org.slf4j" % "slf4j-api" % slf4jVersion

libraryDependencies += "org.slf4j" % "jcl-over-slf4j" % slf4jVersion

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.2"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion

libraryDependencies += "junit" % "junit" % "4.11"

libraryDependencies += "com.google.code.gson" % "gson" % "2.3"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.6"

libraryDependencies += "org.apache.httpcomponents" % "fluent-hc" % "4.3.6"


