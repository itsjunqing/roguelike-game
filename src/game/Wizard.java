package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing a Wizard.
 */
public class Wizard extends Enemy {

    /**
     * Constructor to create a Wizard as a form of an Enemy.
     *
     * @param name a String representing the name of the Wizard
     */
    public Wizard(String name){
        super(name, 'w', 20, 25);
    }

    /**
     * Wizards are capable of resurrecting sleeping enemies as Zombies if the sleeping enemy is beside the Wizard.
     * Wizards are unable to attack and can only move around at random or skip its run.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @returnthe Action to be performed, e.g. move the Wizard to another place
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        Location qLocation = map.locationOf(this);
        actions.clear();
        for (Exit exit : qLocation.getExits()) {
            Location destination = exit.getDestination();
            if (!map.isAnActorAt(destination)) {
                for (Item item : destination.getItems()) {
                    if (item.getDisplayChar() == '%') {
                        Zombie zombie = new Zombie("Zombie");
                        map.addActor(zombie, destination.x(), destination.y());
                        destination.removeItem(item);
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
}
