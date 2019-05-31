package game.behaviour;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * Class representing the behaviour of wandering around the map.
 */
public class WanderBehaviour implements ActionFactory {

    private Random random = new Random();

    /**
     * Constructor to create a behaviour that allows an Actor to move randomly on the map.
     */
    public WanderBehaviour() {
    }

    /**
     * Returns a random movement of an Action to be executed.
     *
     * @param actor the actor that performs the action
     * @param map   the map which the actor is in it
     * @return an Action that moves the Actor in random direction or skips the Actor's turn
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        Actions actions = new Actions();
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                Ground adjacentGround = map.groundAt(destination);
                actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
            }
        }
        actions.add(new SkipTurnAction());
        return actions.get(random.nextInt(actions.size()));
    }
}
