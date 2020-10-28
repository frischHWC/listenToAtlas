package com.cloudera.frisch.listentoatlas

import com.cloudera.frisch.listentoatlas.config.StandardConfig
import org.apache.logging.log4j.scala.Logging


object App extends Logging {

  /**
    * Main function that launches treatment
    * @param args Not needed
    */
  def main(args : Array[String]) {

    logger.info("Starting Program : " + StandardConfig.appName)

    AtlasSetup.setupAtlasConf()

    MessageTreaterFromAtlas.listenToKafka()

    logger.info("Finished Program")
  }

}