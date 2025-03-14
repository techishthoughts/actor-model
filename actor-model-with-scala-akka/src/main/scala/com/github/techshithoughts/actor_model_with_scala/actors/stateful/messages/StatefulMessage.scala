package com.github.techshithoughts.scala_actor_model_with_akka.actors.stateful.messages

case object GetStatusStateful

enum WorkerStatusStateful {
  case Idle
  case Available
  case WaitingResponse
  case InMeeting
}

sealed trait StatefulMessage
case class AskForHelpStateful(value: String) extends StatefulMessage
case class HelpResponseStateful(value: String) extends StatefulMessage
case class SendMeetingLinkStateful(value: String) extends StatefulMessage
case class JoinMeetingStateful(value: String) extends StatefulMessage
case class InMeetingStateful(value: String) extends StatefulMessage
