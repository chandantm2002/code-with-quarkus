// PlayerB.java
package org.acme;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlayerB {

    private static final String CONTROL_ADDRESS = "control";

    @Inject
    EventBus eventBus;

    // Initialize and subscribe to the event bus
    @PostConstruct
    public void initialize() {
        if (eventBus == null) {
            System.out.println("Player B: EventBus is not injected.");
            return;
        }

        System.out.println("Player B: Initializing and subscribing to the event bus");

        // Set up a consumer to listen for "ping" messages
        MessageConsumer<String> consumer = eventBus.consumer(CONTROL_ADDRESS);
        consumer.handler(message -> {
            if ("ping".equals(message.body())) {
                System.out.println("Player B: Received 'ping', replying with 'pong'");
                message.reply("pong");
            } else {
                System.out.println("Player B: Received unknown message: " + message.body());
            }
        });
    }
}
