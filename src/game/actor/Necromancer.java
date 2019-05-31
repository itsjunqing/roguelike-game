package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.SpawnEnemyAction;
import game.item.Potion;

import java.util.ArrayList;

/**
 * Class representing a Necromancer as a type of an Enemy who is able to resurrecting sleeping enemies as Zombies.
 */
public class Necromancer extends Enemy {

    private static ArrayList<Item> deadEnemies = new ArrayList<>();

    /**
     * Constructor to create a Necromancer.
     *
     * @param name name of the Necromancer
     */
    public Necromancer(String name) {
        super(name, '?', 20, 5);
        super.addItemToInventory(new Potion("Potion", 30));
    }

    /**
     * Resurrect a sleeping Enemy as a Zombie if a sleeping Enemy is beside the Necromancer.
     * By default, it is unable to attack and can only move around the map at random and resurrect an Enemy.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed, e.g. move the Wizard to another place
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        Location qLocation = map.locationOf(this);
        actions.clear();
        for (Exit exit : qLocation.getExits()) {
            Location destination = exit.getDestination();
            if (!map.isAnActorAt(destination)) {
                for (Item item : destination.getItems()) {
                    if (deadEnemies.contains(item)) {
                        Enemy zombie = new Zombie("Zombie");
                        destination.removeItem(item);
                        return new SpawnEnemyAction(zombie, destination.x(), destination.y());
                    }
                }
            }
        }

        super.addActions(actions, this, map);
        for (Action action : actions) {
            if (action instanceof AttackAction) {
                actions.remove(action);
            }
        }
        return super.playTurn(actions, map, display);
    }

    /**
     * Adds a dead sleeping Enemy to the list of recognizable deadEnemy items.
     *
     * @param deadEnemy a sleeping dead Enemy
     */
    public static void addDeadEnemy(Item deadEnemy) {
        deadEnemies.add(deadEnemy);
    }

}
