package com.github.techshithoughts.java_actor_model_with_akka;

import akka.actor.AbstractActor;

public class HelloActor extends AbstractActor {
    
    
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    System.out.println("Received message: " + s);
                })
                .build();
    }

}
