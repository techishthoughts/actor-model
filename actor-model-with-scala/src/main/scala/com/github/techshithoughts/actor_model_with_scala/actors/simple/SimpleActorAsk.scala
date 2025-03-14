package com.github.techshithoughts.actor_model_with_scala.actors.simple

import akka.actor.{Actor, ActorLogging}
import com.github.techshithoughts.actor_model_with_scala.actors.simple.messages.{IntMessage, StringMessage, DoubleMessage}

class SimpleActorAsk extends Actor with ActorLogging {
  override def receive: Receive = {
    case value: IntMessage =>
      sender() ! value
    case value: StringMessage =>
      sender() ! value
    case value: DoubleMessage =>
      sender() ! value
    case _ =>
      log.warning("Received an unexpected message")
  }
}
