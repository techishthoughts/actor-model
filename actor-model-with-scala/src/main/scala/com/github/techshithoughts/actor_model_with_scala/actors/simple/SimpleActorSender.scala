package com.github.techshithoughts.actor_model_with_scala.actors.simple

import org.apache.pekko.actor.{Actor, ActorLogging}
import com.github.techshithoughts.actor_model_with_scala.actors.simple.messages.{IntMessage, StringMessage, DoubleMessage}

class SimpleActorSender extends Actor with ActorLogging {
  override def receive: Receive = {
    case value: IntMessage =>
      log.info(s"I received the message of type Int $value")
    case value: StringMessage =>
      log.info(s"I received the message of type String $value")
    case value: DoubleMessage =>
      log.info(s"I received the message of type Double $value")
    case _ =>
      context.stop(self)
      log.warning("Received an unexpected message")
  }
}
