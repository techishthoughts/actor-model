package com.github.techshithoughts.scala_actor_model_with_akka


import akka.actor.ActorSystem

import com.github.techshithoughts.scala_actor_model_with_akka.actors.DemoExecutorHelper


object Main extends App {

  // Create an actor system
  val system = ActorSystem("my-system")

  // Use the correct method from DemoExecutorHelper
  DemoExecutorHelper.simpleActorExampleUsingSender(system)

  system.terminate()
}
