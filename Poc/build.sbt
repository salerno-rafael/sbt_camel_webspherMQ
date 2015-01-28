name := "poc"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.5"

unmanagedJars in Compile ++= 
  (file("[C:/workspace/Poc/lib/") * "*.jar").classpath
 
libraryDependencies ++= Seq (
	"org.scalatest" % "scalatest_2.10" % "1.9.1" % "test",
	"org.apache.camel" % "camel-scala" % "2.14.1",
	"org.apache.camel" % "camel-jms" % "2.14.1",
	"javax.jms" % "javax.jms-api" % "2.0",
	"javax.resource" % "connector-api" % "1.5",
	"javax.transaction" % "jta" % "1.1",
	"commons-logging" % "commons-logging" % "1.1.1"
  
)
