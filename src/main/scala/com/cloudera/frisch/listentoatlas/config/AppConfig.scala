package com.cloudera.frisch.listentoatlas.config

import com.typesafe.config.ConfigFactory

trait AppConfig {

  val conf = ConfigFactory.load()
  val appConf = conf.getConfig("app")

}
