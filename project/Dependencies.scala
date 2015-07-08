import sbt._

object Dependencies {
  object Cache {
    val ehCache = "net.sf.ehcache" % "ehcache-core" % "2.6.11"
  }

  object Database {
    val postgresAsync = "com.github.mauricio" %% "postgresql-async" % "0.2.16"
  }

  object Play {
    val playFilters = play.sbt.PlayImport.filters
    val playWs = play.sbt.PlayImport.ws
    val playJson = play.sbt.PlayImport.json
    val playTest = "com.typesafe.play" %% "play-test" % "2.4.2"
  }

  object WebJars {
    val requireJs = "org.webjars" % "requirejs" % "2.1.18"
    val bootstrap = "org.webjars" % "bootstrap" % "3.3.5"
  }

  object Mail {
    val mailer = "com.typesafe.play" %% "play-mailer" % "3.0.1"
  }

  object Authentication {
    val silhouette = "com.mohiva" %% "play-silhouette" % "3.0.0-RC2"
  }

  object Metrics {
    val metrics = "nl.grons" %% "metrics-scala" % "3.5.1" withSources()
    val jvm = "io.dropwizard.metrics" % "metrics-jvm" % "3.1.2" withSources()
    val ehcache = "io.dropwizard.metrics" % "metrics-ehcache" % "3.1.2" withSources() intransitive()
    val healthChecks = "io.dropwizard.metrics" % "metrics-healthchecks" % "3.1.2" withSources() intransitive()

    val json = "io.dropwizard.metrics" % "metrics-json" % "3.1.2" withSources()

    val jettyServlet = "org.eclipse.jetty" % "jetty-servlet" % "9.3.0.RC1" withSources()
    val servlets = "io.dropwizard.metrics" % "metrics-servlets" % "3.1.2" withSources() intransitive()
    val graphite = "io.dropwizard.metrics" % "metrics-graphite" % "3.1.2" withSources() intransitive()
  }

  object Testing {
    val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % "2.3.11"
  }
}
