package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An Enemy base class that generalizes the properties and methods which an enemy is capable of doing.
 */
public class Enemy extends Actor {

    static final int BASE_DAMAGE = 5;
    private List<ActionFactory> actionFactories = new ArrayList<>();
    protected static ArrayList<Actor> players = new ArrayList<>();

    /**
     * Constructor that creates a new enemy and adds a Key item in its inventory.
     *
     * @param name        a String the resembles the name of the enemy
     * @param displayChar a Character to identify the enemy on the GameMap
     * @param priority    an integer that determines when the enemy is able to take its turn in a round
     * @param hitPoints   an integer that determines the amount of damage it can take before being unconscious
     */
    protected Enemy(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
        addItemToInventory(new Key("Key"));
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        return super.playTurn(actions, map, display);
    }

    /**
     * Allows enemies to add special behaviours that the enemy may possess
     *
     * @param behaviour an ActionFactory that contains a set of rules that may determine what the enemy does
     */
    protected void addBehaviour(ActionFactory behaviour) {
        actionFactories.add(behaviour);
    }

    /**
     * Obtains a list of ActionFactory that contains the behaviours that the enemy possesses
     *
     * @return a list of ActionFactory of the enemy
     */
    protected List<ActionFactory> getActionFactories() {
        return actionFactories;
    }

    /**
     * Sets the base damage for enemies and sets the description as "scratches" after it attacks
     *
     * @return an IntrinsicWeapon with the base damage for all enemies.
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_DAMAGE, "scratches");
    }

        /**
     * Returns a collection of Actions an Enemy is able to perform by default.
     * These actions could be moving around the map and attack the player if player is next to it.
     *
     * @param actions a collection of Action
     * @param enemy   the enemy itself
     * @param map     the map which the enemy is in it
     */
    protected void addActions(Actions actions, Actor enemy, GameMap map) {
        Location location = map.locationOf(this);

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor actor = map.actorAt(destination);
                if (players.contains(actor)) {
                    actions.add(new AttackAction(enemy, actor));
                }

//                if (actor instanceof Player) {
//                    actions.add(new AttackAction(enemy, actor));
//                }
            } else {
                Ground adjacentGround = map.groundAt(destination);
                actions.add(adjacentGround.getMoveAction(enemy, destination, exit.getName(), exit.getHotKey()));
            }
        }
        actions.add(new SkipTurnAction());
    }

    public static void addTarget(Actor player) {
        players.add(player);
    }
}
