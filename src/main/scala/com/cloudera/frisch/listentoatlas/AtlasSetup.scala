package com.cloudera.frisch.listentoatlas

import com.cloudera.frisch.listentoatlas.config.{AtlasConfig, KafkaConfig, StandardConfig, ZookeeperConfig}
import org.apache.atlas.ApplicationProperties
import org.apache.logging.log4j.scala.Logging


object AtlasSetup extends Logging {

  /**
    * Goal of this method is to setup Atlas configuration using existing configuration passed through application.conf
    * Note that file atlas-application.properties in resources is required to be able to load first configuration (even if it is empty)
    */
    def setupAtlasConf(): Unit = {

    val atlasConf = ApplicationProperties.get()

    atlasConf.setProperty("atlas.notification.entities.consumer.topic.names", AtlasConfig.entityTopic)

    atlasConf.setProperty("atlas.kafka.zookeeper.connect", ZookeeperConfig.connect)
    atlasConf.setProperty("atlas.kafka.bootstrap.servers", KafkaConfig.brokers )
    atlasConf.setProperty("atlas.kafka.security.protocol", KafkaConfig.protocol)
    atlasConf.setProperty("atlas.kafka.entities.group.id", StandardConfig.appName)


    if(KafkaConfig.protocol.contains("SASL")) {
      atlasConf.setProperty("atlas.kafka.sasl.kerberos.service.name", KafkaConfig.serviceName)

      atlasConf.setProperty("atlas.jaas.KafkaClient.loginModuleControlFlag", KafkaConfig.loginModule)
      atlasConf.setProperty("atlas.jaas.KafkaClient.loginModuleName", KafkaConfig.loginModuleName)
      atlasConf.setProperty("atlas.jaas.KafkaClient.option.serviceName", KafkaConfig.serviceName)
      atlasConf.setProperty("atlas.jaas.KafkaClient.option.useKeyTab", KafkaConfig.useKeytab)
      atlasConf.setProperty("atlas.jaas.KafkaClient.option.storeKey", KafkaConfig.storeKey)
      atlasConf.setProperty("atlas.jaas.KafkaClient.option.useTicketCache", KafkaConfig.useTicketCache)
      atlasConf.setProperty("atlas.jaas.KafkaClient.option.keyTab", StandardConfig.keytab)
      atlasConf.setProperty("atlas.jaas.KafkaClient.option.principal", StandardConfig.kerberosUser)
    }

    if(KafkaConfig.protocol.contains("SSL")) {
      atlasConf.setProperty("atlas.kafka.ssl.truststore.location", KafkaConfig.truststoreLocation)
      atlasConf.setProperty("atlas.kafka.ssl.truststore.password", KafkaConfig.truststorePassword)

      atlasConf.setProperty("atlas.kafka.ssl.keystore.location", KafkaConfig.keystoreLocation)
      atlasConf.setProperty("atlas.kafka.ssl.keystore.password", KafkaConfig.keystorePassword)
      atlasConf.setProperty("atlas.kafka.ssl.key.password", KafkaConfig.keystoreKeyPassword)

    }

  }

}
