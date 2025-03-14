package com.github.techshithoughts.actor_model_with_scala.actors

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import org.apache.pekko.actor.{ActorSystem, Props, ActorRef}
import  org.apache.pekko.pattern.ask
import org.apache.pekko.util.Timeout

import com.github.techshithoughts.actor_model_with_scala.actors.stateful._
import com.github.techshithoughts.actor_model_with_scala.actors.stateless._
import com.github.techshithoughts.actor_model_with_scala.actors.simple.{SimpleActorSender, SimpleActorAsk}
import com.github.techshithoughts.actor_model_with_scala.actors.simple.messages._
import com.github.techshithoughts.actor_model_with_scala.actors.stateful.messages._
import com.github.techshithoughts.actor_model_with_scala.actors.stateless.messages.AskForHelpStateless

implicit val timeout: Timeout = Timeout(5.seconds)

object DemoExecutorHelper {

  /** This method demonstrates how to use the ! (sender) operator to send
    * messages to an actor
    *
    * @param system ActorSystem
    */

  def simpleActorExampleUsingSender(system: ActorSystem): Unit = {
    val simpleActorSenderProps = Props[SimpleActorSender]()
    val simpleActorSender: ActorRef =
      system.actorOf(simpleActorSenderProps, "simple-actor-sender")

    simpleActorSender ! IntMessage(42)
    simpleActorSender ! StringMessage("Hello, World!")
    simpleActorSender ! DoubleMessage(42.0)
    simpleActorSender ! "This is an unexpected message"
  }

  /** This method demonstrates how to use the ask pattern to send messages to an
    * actor
    *
    * @param system ActorSystem
    */
  def simpleActorExampleUsingAsk(system: ActorSystem): Unit = {

    val simpleActorAskProps = Props[SimpleActorAsk]()

    val simpleActorAsk: ActorRef =
      system.actorOf(simpleActorAskProps, "simple-actor-ask")

    val resultIntMessage = Await.result(
      (simpleActorAsk ? IntMessage(42)).mapTo[IntMessage],
      timeout.duration
    )
    val resultStringMessage = Await.result(
      (simpleActorAsk ? StringMessage("Hello, World!")).mapTo[StringMessage],
      timeout.duration
    )
    val resultDoubleMessage = Await.result(
      (simpleActorAsk ? DoubleMessage(42.0)).mapTo[DoubleMessage],
      timeout.duration
    )

    println(s"Result: $resultIntMessage")
    println(s"Result: $resultStringMessage")
    println(s"Result: $resultDoubleMessage")
  }

  def statefulWorkerExampleWithLogging(system: ActorSystem): Unit = {
    // Use the companion object's props method to create the Props object
    val workerGabrielProps =
      StatefulActor.props("Gabriel", WorkerStatusStateful.Idle)
    val workerGabrielActor: ActorRef =
      system.actorOf(workerGabrielProps, "worker-gabriel")
    val workerRafaelProps =
      StatefulActor.props("Rafael", WorkerStatusStateful.Idle)
    val workerRafaelActor: ActorRef =
      system.actorOf(workerRafaelProps, "worker-rafael")

    // Create the SlackMessageCoordinator actor
    val slackMessageCoordinatorActor: ActorRef =
      system.actorOf(
        SlackMessageCoordinator.props(),
        "slack-message-coordinator"
      )

    slackMessageCoordinatorActor ! SlackMessage(
      workerGabrielActor,
      AskForHelpStateful("Rafael, could you help me ?"),
      workerRafaelActor
    )
  }

  def statelessWorkerExampleWithLogging(system: ActorSystem): Unit = {
    // Use the companion object's props method to create the Props object
    val workerGabrielProps =
      StatelessActor.props("Gabriel")
    val workerGabrielActor: ActorRef =
      system.actorOf(workerGabrielProps, "worker-gabriel")
    val workerRafaelProps =
      StatelessActor.props("Rafael")
    val workerRafaelActor: ActorRef =
      system.actorOf(workerRafaelProps, "worker-rafael")

    // Create the SlackMessageCoordinator actor
    val slackMessageCoordinatorActor: ActorRef =
      system.actorOf(
        SlackMessageCoordinator.props(),
        "slack-message-coordinator"
      )

    slackMessageCoordinatorActor ! SlackMessage(
      workerGabrielActor,
      AskForHelpStateless("Rafael, could you help me ?"),
      workerRafaelActor
    )
  }


}
