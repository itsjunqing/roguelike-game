package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing a RocketPad that the player is able to build a Rocket on.
 */
public class RocketPad extends Ground {

    private static ArrayList<Item> rocketBodies = new ArrayList<>();
    private static ArrayList<Item> rocketEngines = new ArrayList<>();

    /**
     * Constructor to create a rocket pad.
     */
    public RocketPad() {
        super('=');
    }

    /**
     * Allows the actor to build a rocket when the RocketPad detects that it has both RocketBody and RocketEngine on it.
     *
     * @param actor     the Actor beside the RocketPad
     * @param location  the Location of the RocketPad
     * @param direction the direction the Actor is beside the RocketPad
     * @return a collection of Action which the actor adjacent to it can perform
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);

        Item rocketBody = null;
        Item rocketEngine = null;

        for (Item item : location.getItems()) {
            if (rocketBodies.contains(item)) {
                rocketBody = item;
            } else if (rocketEngines.contains(item)) {
                rocketEngine = item;
            }
        }
        if ((rocketEngine != null) && (rocketBody != null)) {
            actions.add(new BuildRocketAction(rocketBody, rocketEngine, location));
            return actions;
        }
        return actions;
    }

    public static void addRocketBody(Item rocketBody) {
        rocketBodies.add(rocketBody);
    }

    public static void addRocketEngine(Item rocketEngine) {
        rocketEngines.add(rocketEngine);
    }

    public static void removeRocketBody(Item rocketBody) {
        rocketBodies.remove(rocketBody);
    }

    public static void removeRocketEngine(Item rocketEngine) {
        rocketEngines.remove(rocketEngine);
    }
}
