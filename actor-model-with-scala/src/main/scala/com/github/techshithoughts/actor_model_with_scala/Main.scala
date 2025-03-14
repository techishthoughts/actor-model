package com.github.techshithoughts.actor_model_with_scala

import org.apache.pekko.actor.ActorSystem
import com.github.techshithoughts.actor_model_with_scala.actors.DemoExecutorHelper

object Main extends App {
  val system = ActorSystem("techshi-thoughts-actor-model-with-scala")
  DemoExecutorHelper.statelessWorkerExampleWithLogging(system)
  system.terminate()
}