name := "custom-explore"

scalaVersion in ThisBuild := "2.11.6"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)

libraryDependencies in ThisBuild ++= Seq(
  "org.squeryl"        %% "squeryl"       % "0.9.6-RC3",
  "org.postgresql"     %  "postgresql"    % "9.3-1101-jdbc41",
  "org.slf4j"          %  "slf4j-nop"     % "1.6.4"
)
