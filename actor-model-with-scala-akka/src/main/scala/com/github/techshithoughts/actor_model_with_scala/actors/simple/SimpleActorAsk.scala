package com.github.techshithoughts.scala_actor_model_with_akka.actors.simple

import akka.actor.{Actor, ActorLogging}
import com.github.techshithoughts.scala_actor_model_with_akka.actors.simple.messages.{IntMessage, StringMessage, DoubleMessage}

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
