package game.actor;

import edu.monash.fit2099.engine.*;
import game.item.RocketEngine;

/**
 * Class representing DoctorMaybe as a type of an Enemy.
 */
public class DoctorMaybe extends Enemy {

    public static final char DOCTOR_MAYBE_CHAR = 'D';

    /**
     * Constructor to create an Enemy of type DoctorMaybe with a name.
     * By default, it has RocketEngine in its inventory.
     *
     * @param name the name of the DoctorMaybe
     */
    public DoctorMaybe(String name) {
        super(name, DOCTOR_MAYBE_CHAR, 10, 100);
        addItemToInventory(new RocketEngine("Rocket Engine"));
    }

    /**
     * Returns a new IntrinsicWeapon with half the damage of a base enemy damage and a new description.
     *
     * @return an IntrinsicWeapon suitable for DoctorMaybe
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon((int) Math.round(BASE_DAMAGE * 0.5), "zaps");
    }

    /**
     * Returns the action to be performed during its turn.
     * It attacks the player if it is next to it. Otherwise, it does nothing.
     *
     * @param actions collection of possible Actions for DoctorMaybe in the turn
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed, e.g. attacking the player when it is next to it
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        super.addActions(actions, this, map);
        for (Action action : actions) {
            if (action instanceof MoveActorAction) {
                actions.remove(action);
            }
        }
        return super.playTurn(actions, map, display);
    }
}
