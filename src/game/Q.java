package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing Q as a Non-Playable Character
 */
public class Q extends Actor {

    private boolean pass = false;

    /**
     * Constructor to create Q as a Non-Playable Character with a name.
     * Q will have an instance of the RocketBody item in its inventory by default.
     *
     * @param name name of the Q
     */
    public Q(String name) {
        super(name, 'Q', 8, 1000);
        addItemToInventory(new RocketBody("Rocket body"));
    }

    // pls rephrase this

    /**
     * It first checks if Q has already given the RocketBody to an Actor. If Q has not given the RocketBody, then it
     * checks if there is an Actor is beside Q. If an Actor is beside Q, it then checks if the Actor contains
     * RocketPlans in its inventory. If the Actor contains RocketPlans, Q will then give the RocketBody to the Actor.
     * If the RocketBody has already been given, it removes Q from the map.
     *
     * @param actions collection of possible Actions for Q
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return  the Action to be performed, e.g. attacking the player when it is next to it
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        if (!pass) {
            Location qLocation = map.locationOf(this);
            actions.clear();

            for (Exit exit : qLocation.getExits()) {
                Location destination = exit.getDestination();
                if (map.isAnActorAt(destination)) {
                    Actor actor = map.actorAt(destination);
                    for (Item item : this.getInventory()) {
                        if (item instanceof RocketPlans) {
                            pass = true;
                            for (Item body : this.getInventory()) {
                                if (body instanceof RocketBody) {
                                    return new GiveItemAction(actor, body);
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
            display.println(disappear(map));
            return new SkipTurnAction();
        }
    }


    /**
     * Returns a collection of Action that otherActor can do to the current Actor (Q).
     * If the player next to it has rocket plans, it returns collection with TalkAction and GiveItemAction.
     * Or else, it just return a collection with TalkAction.
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
        for (Item itemInInventory : otherActor.getInventory()) {
            if (itemInInventory instanceof RocketPlans) {
                actions.add(new TalkAction("Hand the rocket plans over, I don't have all day", this));
                actions.add(new GiveItemAction(this, itemInInventory));
                hasRocketPlans = true;
                break;
            }
        }
        if (!hasRocketPlans) {
            actions.add(new TalkAction("I can give you something that will help, come back to me when you've the plans.", this));
        }
        return actions;
    }


    /**
     * Remove Q from the current map.
     *
     * @param map the map which the Q is in it
     * @return a string statement where Q disappears into the air
     */
    private String disappear(GameMap map) {
        map.removeActor(this);
        return this + " disappears into the air with a cheery wave ~~~~";
    }

}
