// PlayerA.java
package org.acme;

import io.vertx.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PlayerA {

    private static final String CONTROL_ADDRESS = "control";
    private volatile boolean running = false;

    @Inject
    EventBus eventBus;

    // Start the game, send the first "ping"
    public void startGame() {
        running = true;  // Set running to true when starting the game
        System.out.println("Player A: Starting the game, sending 'ping'");
        sendPing();
    }

    // Stop the game
    public void stopGame() {
        running = false;
        System.out.println("Player A: Stopping the game");
    }

    // Send a "ping" message if the game is running
    private void sendPing() {
        if (!running) {
            return;  // Exit if the game is not running
        }
        
        eventBus.request(CONTROL_ADDRESS, "ping", reply -> {
            if (reply.succeeded()) {
                System.out.println("Player A: Received " + reply.result().body());
                sendPing();  // Continue the game by sending the next "ping"
            } else {
                System.out.println("Player A: Failed to send ping. Cause: " + reply.cause());
            }
        });
    }
}
