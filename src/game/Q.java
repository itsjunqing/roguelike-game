package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing Q as a Non-Playable Character
 */
public class Q extends Actor {

    private boolean passedItem = false;
    private static ArrayList<Item> rocketPlans = new ArrayList<>();
    private static ArrayList<Item> rocketBodies = new ArrayList<>();
    private Actor player;

    /**
     * Constructor to create Q as a Non-Playable Character with a name.
     * Q will have an instance of the RocketBody item in its inventory by default.
     *
     * @param name name of the Q
     */
    public Q(String name, Actor player) {
        super(name, 'Q', 8, 1000);
        this.player = player;
        addItemToInventory(new RocketBody("Rocket body"));
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
                if (map.isAnActorAt(destination)) {
                    Actor actor = map.actorAt(destination);
                    if (actor == player) {
                        for (Item plan : this.getInventory()) {
                            if (rocketPlans.contains(plan)) {
                                // this means that player has passed the plan to Q and Q should no longer need to recognize this plan anymore
                                // because Q will disappear along with the plan
                                // so for memory efficient, the list of recognizable rocket plans should be removed also
                                rocketPlans.remove(plan);
                                for (Item body : this.getInventory()) {
                                    if (rocketBodies.contains(body)) {
                                        // same argument as above, remove from the list of recognizable rocketbodies, as Q no longer owns the body
                                        passedItem = true;
                                        rocketBodies.remove(body);
                                        return new GiveItemAction(actor, body);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    Ground adjacentGround = map.groundAt(destination);
                    actions.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
                }
            }
            actions.add(new SkipTurnAction());
            return super.playTurn(actions, map, display);

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

        if (otherActor == player) {
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
     * Adds the rocket plans taken in as a parameter to ensure that Q recognises the rocket plans and has to check for
     * the rocket plans added to provide an action
     * @param plan an Item that represents the Rocket Plans that will be given to Q
     */
    public static void addRocketPlans(Item plan) {
        rocketPlans.add(plan);
    }

    /**
     * Adds the Rocket Body taken in as a parameter to ensure that Q recognises the rocket body and can give the rocket
     * body away.
     * @param body
     */
    public static void addRocketBody(Item body) {
        rocketBodies.add(body);
    }
}
