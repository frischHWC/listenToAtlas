package com.cloudera.frisch.listentoatlas.config

object ZookeeperConfig extends AppConfig {

  val zkConf = conf.getConfig("zookeeper")

  val connect = zkConf.getString("connect")

}
