package com.github.techshithoughts.actor_model_with_scala


import akka.actor.ActorSystem
import com.github.techshithoughts.actor_model_with_scala.actors.DemoExecutorHelper



object Main extends App {

  // Create an actor system
  val system = ActorSystem("my-system")

  // Use the correct method from DemoExecutorHelper
  DemoExecutorHelper.statelessWorkerExampleWithLogging(system)

  system.terminate()
}
