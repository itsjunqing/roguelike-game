package game.ground;

import edu.monash.fit2099.engine.*;
import game.Application;
import game.action.BuildRocketAction;

import java.util.ArrayList;

/**
 * Class representing a RocketPad that the player is able to build a Rocket on.
 */
public class RocketPad extends Ground {

    private static ArrayList<Item> rocketBodies = new ArrayList<>();
    private static ArrayList<Item> rocketEngines = new ArrayList<>();

    /**
     * Constructor to create a RocketPad.
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
            Location rocketMoonLocation = Application.getMoonMap().at(7,4);
            actions.add(new BuildRocketAction(rocketBody, rocketEngine, location, rocketMoonLocation));
            return actions;
        }
        return actions;
    }

    /**
     * Adds a RocketBody into the list of recognizable rocketBodies as references.
     *
     * @param rocketBody an Item signifying the RocketBody
     */
    public static void addRocketBody(Item rocketBody) {
        rocketBodies.add(rocketBody);
    }

    /**
     * Adds a RocketEngine into the list of recognizable rocketEngines as references.
     *
     * @param rocketEngine an Item signifying the RocketEngine
     */
    public static void addRocketEngine(Item rocketEngine) {
        rocketEngines.add(rocketEngine);
    }

    /**
     * Removes a RocketBody from the list of recognizable rocketBodies as references.
     *
     * @param rocketBody an Item signifying the RocketBody
     */
    public static void removeRocketBody(Item rocketBody) {
        rocketBodies.remove(rocketBody);
    }

    /**
     * Removes a RocketEngine from the list of recognizable rocketEngines as references.
     *
     * @param rocketEngine an Item signifying the RocketEngine
     */
    public static void removeRocketEngine(Item rocketEngine) {
        rocketEngines.remove(rocketEngine);
    }
}
