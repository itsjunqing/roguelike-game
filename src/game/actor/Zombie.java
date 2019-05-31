package game.actor;

import edu.monash.fit2099.engine.*;

/**
 * Class representing a Zombie.
 */
public class Zombie extends Enemy {

    /**
     * Constructor to create a Zombie.
     *
     * @param name name of the Zombie
     */
    public Zombie(String name) {
        super(name, 'Z', 21, 5);
    }

    /**
     * Attack the player if it is detected next to it and moves randomly in the map.
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
