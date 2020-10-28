package com.cloudera.frisch.listentoatlas.config

object AtlasConfig extends AppConfig {

  val atlasConf = conf.getConfig("atlas")

  val entityTopic = atlasConf.getString("entity-topic")

}
