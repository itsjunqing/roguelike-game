package game;

import edu.monash.fit2099.engine.*;

import static game.RocketBody.ROCKET_BODY_CHAR;
import static game.RocketPlans.ROCKET_PLANS_CHAR;

/**
 * Class representing Q as a Non-Playable Character
 */
public class Q extends Actor {

    private boolean passedItem = false;

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
                    for (Item item : this.getInventory()) {
                        if (item.getDisplayChar() == ROCKET_PLANS_CHAR) {
                            passedItem = true;
                            for (Item body : this.getInventory()) {
                                if (body.getDisplayChar() == ROCKET_BODY_CHAR) {
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
        for (Item itemInInventory : otherActor.getInventory()) {
            if (itemInInventory.getDisplayChar() == ROCKET_PLANS_CHAR) {
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
     * Removes Q from the current map.
     *
     * @param map the map which the Q is in it
     * @return a string statement where Q disappears into the air
     */
    private String disappear(GameMap map) {
        map.removeActor(this);
        return this + " disappears into the air with a cheery wave ~~~~";
    }

}
