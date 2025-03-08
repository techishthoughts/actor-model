package com.github.techshithoughts.java_actor_model_with_akka;
    
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-model-with-java");
        ActorRef actor = system.actorOf(Props.create(HelloActor.class), "hello-actor");
        actor.tell("Hello", ActorRef.noSender());
    }
}