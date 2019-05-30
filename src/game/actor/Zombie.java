package game.actor;

import edu.monash.fit2099.engine.*;

public class Zombie extends Enemy {

    /**
     * Constructor to create a Zombie as a form of an Enemy.
     *
     * @param name a String representing the Zombie's name.
     */
    public Zombie(String name) {
        super(name, 'Z', 21, 5);
    }

    /**
     * Zombie are capable of performing all the actions of a basic Enemy which are Attacking the player, skipping its
     * turn and moving around the map.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed, e.g. attacking the player when it is next to it
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        super.addActions(actions, this, map);
        return super.playTurn(actions, map, display);
    }

    /**
     * Creates a new IntrinsicWeapon with a damage of 1 and a new description when it attacks.
     *
     * @return an IntrinsicWeapon for a Zombie
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(1, "bites");
    }

}
