package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.SpawnEnemyAction;
import game.item.Potion;

import java.util.ArrayList;

public class Necromancer extends Enemy {

    private static ArrayList<Item> deadEnemies = new ArrayList<>();

    /**
     * Constructor to create a Necromancer as a form of an Enemy.
     *
     * @param name a String representing the name of the Wizard
     */
    public Necromancer(String name) {
        super(name, '?', 20, 5);
        super.addItemToInventory(new Potion("Potion", 30));
    }

    /**
     * Necromancers are capable of resurrecting sleeping enemies as Zombies
     * if the sleeping enemy is beside the Necromancer.
     * Necromancers are unable to attack and can only move around at random or skip its run.
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

    public static void addDeadEnemy(Item deadEnemy) {
        deadEnemies.add(deadEnemy);
    }

}
