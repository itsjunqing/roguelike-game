package game;

import edu.monash.fit2099.engine.*;

public class FollowBehaviour implements ActionFactory {

    private Actor target;

    /**
     * Constructor to create a behavior that follows an Actor
     *
     * @param subject an actor to follow
     */
    public FollowBehaviour(Actor subject) {
        this.target = subject;
    }

    // pls rephrase this
    /**
     * Moves the Actor one step nearer to the followed subject Actor
     *
     * @param actor the actor to be moved
     * @param map the map which the actor is on
     * @return an Action that moves the actor
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }
        return null;
    }

    /**
     * Calculates the Manhattan distance between the two locations.
     *
     * @param a a Location
     * @param b a Location
     * @return distance between the locations
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}