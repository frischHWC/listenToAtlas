package com.cloudera.frisch.listentoatlas



import org.apache.atlas.hook.AtlasHook
import org.apache.atlas.kafka.{AtlasKafkaMessage, NotificationProvider}
import org.apache.atlas.model.notification.EntityNotification
import org.apache.atlas.notification.{NotificationConsumer, NotificationInterface}
import org.apache.logging.log4j.scala.Logging



object MessageTreaterFromAtlas extends AtlasHook with Logging {

  def listenToKafka(): Unit = {
    val notification = NotificationProvider.get()
    val consumer: NotificationConsumer[EntityNotification] =
      notification.createConsumers(NotificationInterface.NotificationType.ENTITIES, 1).get(0)

    while(true) {
      val messages: java.util.Iterator[AtlasKafkaMessage[EntityNotification]] = consumer.receive().iterator()

      while (messages.hasNext) {
        val message = messages.next()
        logger.info("Will treat message from: " + message.getTopic + " in partition: " + message.getPartition + " with offset: " + message.getOffset)

        treatMessage(message.getMessage)

        consumer.commit(message.getTopicPartition, message.getOffset)
      }
    }

    consumer.close()

  }

  def treatMessage(message: EntityNotification): Unit = {
    // TODO: Treat message here
    logger.info("Message is : " + message)
    logger.info("Message is of type: " + message.getType.toString)
  }


}
