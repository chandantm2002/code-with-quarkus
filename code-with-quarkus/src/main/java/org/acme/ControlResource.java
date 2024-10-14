// ControlResource.java
package org.acme;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;

@Path("/control")
public class ControlResource {

    @Inject
    PlayerA playerA;

    @Inject
    PlayerB playerB;

    // Start the game by initializing PlayerB and PlayerA
    @POST
    @Path("/start")
    @Produces(MediaType.TEXT_PLAIN)
    public String start() {
        System.out.println("Control Received Start Message"); 

        // Initialize PlayerB before starting the game
        playerB.initialize();
        playerA.startGame();
        
        return "Start command received, game started";
    }

    // Stop the game
    @POST
    @Path("/stop")
    @Produces(MediaType.TEXT_PLAIN)
    public String stop() {
        playerA.stopGame();
        return "Stop command received, game stopped";
    }

    // Initialize both players (especially PlayerB)
    @POST
    @Path("/init")
    @Produces(MediaType.TEXT_PLAIN)
    public String init() {
        playerB.initialize();  // Ensure PlayerB is ready
        return "Initialization command received";
    }
}
