package game.actor;

import edu.monash.fit2099.engine.*;
import game.item.Key;

/**
 * An Enemy base class that generalizes the properties and methods which an enemy is capable of doing.
 */
public abstract class Enemy extends GameActor {

    protected static final int BASE_DAMAGE = 5;
//    protected static ArrayList<Actor> players = new ArrayList<>();
    protected static Actor player;


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

    /**
     * Returns an IntrinsicWeapon with the base damage set as the damage and a new default description as "scratches".
     *
     * @return an IntrinsicWeapon with the base damage set
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_DAMAGE, "scratches");
    }

    /**
     * Returns a collection of Actions an Enemy is able to perform by default.
     * These actions could be moving around the map and attacking the player if player is next to it.
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
                if (actor == player) {
//                if (players.contains(actor)) {
                    actions.add(new AttackAction(enemy, actor));
                }
            } else {
                Ground adjacentGround = map.groundAt(destination);
                actions.add(adjacentGround.getMoveAction(enemy, destination, exit.getName(), exit.getHotKey()));
            }
        }
        actions.add(new SkipTurnAction());
    }

    public static void setPlayer(Actor player) {
        Enemy.player = player;
    }

//    /**
//     * Adds a Player the list of recognizable players as references.
//     *
//     * @param player an Actor signifying the Player
//     */
//    public static void addPlayer(Actor player) {
//        players.add(player);
//    }



//    @Override
//    public void addBehaviour(ActionFactory behaviour) {
//        actionFactories.add(behaviour);
//    }
//
//    @Override
//    public Action executeBehaviours(GameMap map) {
//        for (ActionFactory factory : actionFactories) {
//            Action action = factory.getAction(this, map);
//            if (action != null)
//                return action;
//        }
//        return null;
//    }
}