package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the behaviour of following an Actor.
 */
public class FollowBehaviour implements ActionFactory {

    private Actor target;

    /**
     * Constructor to create a behavior that allows an Actor to follow another Actor
     *
     * @param subject an actor to follow
     */
    public FollowBehaviour(Actor subject) {
        this.target = subject;
    }

    /**
     * Return the Action to be executed
     * Moves the Actor one step nearer to the targeted Actor to be followed
     *
     * @param actor the actor to be moved
     * @param map   the map which the actor is on
     * @return an Action that moves the actor or null to signify that the Actor does not require to follow the
     * targeted Actor
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