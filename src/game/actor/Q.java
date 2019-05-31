package game.actor;

import edu.monash.fit2099.engine.*;
import game.GameWorld;
import game.action.DisappearAction;
import game.action.GiveItemAction;
import game.action.TalkAction;
import game.behaviour.WanderBehaviour;
import game.item.RocketBody;

import java.util.ArrayList;

/**
 * Class representing Q as a Non-Playable Character.
 */
public class Q extends GameActor {

    private boolean passedItem = false;
    private static ArrayList<Item> rocketPlans = new ArrayList<>();
    private static ArrayList<Item> rocketBodies = new ArrayList<>();

    /**
     * Constructor to create Q as a Non-Playable Character with a name.
     * It has RocketBody in its inventory and has an ability of wandering around the map at random.
     *
     * @param name name of the Q
     */
    public Q(String name) {
        super(name, 'Q', 8, Integer.MAX_VALUE);
        addItemToInventory(new RocketBody("Rocket body"));
        addBehaviour(new WanderBehaviour());
    }


    /**
     * Returns the Action to be performed during its turn.
     * If it has passed the RocketBody to the Player, it disappears and skips its action.
     * Otherwise, it exchanges a RocketBody with a RocketPlans if the player next to it has a RocketPlans.
     * By default, it wanders around the map until player is next to it.
     *
     * @param actions collection of possible Actions for Q
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed, e.g. giving the item to the player when it is next to it
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        if (!passedItem) {
            Location qLocation = map.locationOf(this);
            actions.clear();
            for (Exit exit : qLocation.getExits()) {
                Location destination = exit.getDestination();
                GamePlayer player = GameWorld.getGamePlayer();
                if (map.locationOf(player).equals(destination)) {
                    Item plan = getRocketPlan();
                    if (plan != null) {
                        rocketPlans.remove(plan);
                        Item body = getRocketBody();
                        rocketBodies.remove(body);
                        passedItem = true;
                        return new GiveItemAction(player, body);
                    }
                }
            }

            Action action = executeBehaviours(map);
            return action;
        } else {
            return new DisappearAction();
        }
    }


    /**
     * Returns a collection of Action that player can do to the current Actor (Q).
     * If the player next to it has rocket plans, it returns a collection of TalkAction with a message of handing over rocket plans
     * and GiveItemAction for player to give the rocket plans to Q.
     * Or else, it returns a collection of TalkAction with a message of requesting rocket plans.
     *
     * @param otherActor the Actor that talks to Q
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        boolean hasRocketPlans = false;
        Actions actions = new Actions();
        if (passedItem) {
            return actions;
        }

        if (otherActor.equals(GameWorld.getGamePlayer())) {
            for (Item plan : otherActor.getInventory()) {
                if (rocketPlans.contains(plan)) {
                    actions.add(new TalkAction("Hand the rocket plans over, I don't have all day", this));
                    actions.add(new GiveItemAction(this, plan));
                    hasRocketPlans = true;
                    break;
                }
            }
        }
        if (!hasRocketPlans) {
            actions.add(new TalkAction("I can give you something that will help, come back to me when you've the plans.", this));
        }
        return actions;
    }

    /**
     * Gets the RocketBody Item in its inventory.
     *
     * @return the RocketBody
     */
    private Item getRocketBody() {
        for (Item item : this.getInventory()) {
            if (rocketBodies.contains(item)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Gets the RocketPlans in its inventory.
     *
     * @return the RocketPlans
     */
    private Item getRocketPlan() {
        for (Item item : this.getInventory()) {
            if (rocketPlans.contains(item)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Adds a RocketPlans into the list of recognizable rocketPlans as references.
     *
     * @param plan an Item of RocketPlans
     */
    public static void addRocketPlans(Item plan) {
        rocketPlans.add(plan);
    }

    /**
     * Adds a RocketBody into the list of recognizable rocketBodies as references.
     *
     * @param body an Item of RocketBody
     */
    public static void addRocketBody(Item body) {
        rocketBodies.add(body);
    }
}
