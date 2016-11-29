import sbt._

object Dependencies {

  val resolutionRepos = Seq(
    "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos",
    "JBoss public" at "http://repository.jboss.org/nexus/content/groups/public/"
  )

  object V {
    val spark = "1.6.0-cdh5.7.1"
    val hadoopCom = "2.6.0-cdh5.7.1"
    val scopt = "3.3.0"
    val scalaTest = "3.0.0"
    val logback = "1.1.3"
    val drools = "6.5.0.Final"
  }

  object Libraries {
    val sparkCore= "org.apache.spark" %% "spark-core" % V.spark
    val sparkSql= "org.apache.spark" %% "spark-sql" % V.spark
    val hadoopCommon = "org.apache.hadoop" % "hadoop-common" % V.hadoopCom % "provided"
    val scopt= "com.github.scopt" %% "scopt" % V.scopt
    val scalaTest = "org.scalatest" % "scalatest_2.10" % V.scalaTest % "test"
    val sparkAssembly = "org.apache.spark" %% "spark-assembly" % V.spark % "test"
    val logback = "ch.qos.logback" % "logback-classic" % V.logback
    val droolsLib = "org.drools" % "drools-core" % V.drools
    val droolsKnw = "org.drools" % "knowledge-api" % V.drools
    val droolsComp = "org.drools" % "drools-compiler" % V.drools
    val kryo = "com.esotericsoftware" % "kryo" % "4.0.0"
  }

}