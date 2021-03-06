import android.Keys._
import android.Dependencies.{LibraryDependency, aar}

android.Plugin.androidBuild

platformTarget in Android := "android-21"

name := "DiggingMacroid"

scalaVersion := "2.11.1"

run <<= run in Android

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  "jcenter" at "http://jcenter.bintray.com"
)
scalacOptions in (Compile, compile) ++=
  (dependencyClasspath in Compile).value.files.map("-P:wartremover:cp:" + _.toURI.toURL)

scalacOptions in (Compile, compile) ++= Seq(
  "-P:wartremover:traverser:macroid.warts.CheckUi"
)

libraryDependencies ++= Seq(
  aar("org.macroid" %% "macroid" % "2.0.0-M3"),
  aar("com.google.android.gms" % "play-services" % "4.0.30"),
  aar("com.android.support" % "support-v4" % "21.0.3"),
  aar("com.android.support" % "appcompat-v7" % "21.0.3"),
  aar("com.android.support" % "recyclerview-v7" % "21.0.3"),
  aar("com.android.support" % "cardview-v7" % "21.0.3"),
  aar("com.fortysevendeg" %% "macroid-extras" % "0.1.1"),
  compilerPlugin("org.brianmckenna" %% "wartremover" % "0.10")
)

proguardScala in Android := true

proguardOptions in Android ++= Seq(
  "-ignorewarnings",
  "-keep class scala.Dynamic"
)
