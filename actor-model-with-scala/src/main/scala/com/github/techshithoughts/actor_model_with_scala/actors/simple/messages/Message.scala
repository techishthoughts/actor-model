package com.github.techshithoughts.actor_model_with_scala.actors.simple.messages

sealed trait Message
case class IntMessage(value: Int) extends Message
case class StringMessage(value: String) extends Message
case class DoubleMessage(value: Double) extends Message
