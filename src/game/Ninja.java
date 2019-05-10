package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing Ninja as a form of an Enemy.
 */
public class Ninja extends Enemy {

    private boolean stunThrown = false;
    private static ArrayList<Item> stunPowders = new ArrayList<>();

    /**
     * Constructor to create an Enemy of type Ninja with a name.
     * It takes in a player of Actor type to represent the target for it to throw the stun.
     *
     * @param name   name of the Ninja
     */
    public Ninja(String name) {
        super(name, 'N', 15, 50);
        for (Actor player : players) {
            addBehaviour(new ThrowStunBehaviour(player));
        }
    }


    /**
     * Creates a new IntrinsicWeapon with the base enemy damage and a new description when it attacks.
     *
     * @return an IntrinsicWeapon suitable for Ninja
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_DAMAGE, "slices");
    }


    /**
     * Return the action to be performed during its turn.
     * It stuns the player and moves one step in a random direction in the following turn.
     * Otherwise, it will stay at one place (i.e., not moving).
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed, e.g. moving one step in a random direction
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();

        if (hasStunPowderBomb(map) && stunThrown) {
            stunThrown = false;
            super.addActions(actions, this, map);
            return super.playTurn(actions, map, display);
        }

        for (ActionFactory factory : getActionFactories()) {
            Action action = factory.getAction(this, map);
            if (action != null) {
                stunThrown = true;
                return action;
            }
        }
        actions.add(new SkipTurnAction());
        return super.playTurn(actions, map, display);
    }

    /**
     * Checks the location of the player if there exists a stun powder bomb.
     *
     * @param map the map containing the player
     * @return a boolean stating if the stun powder bomb exists
     */
    private boolean hasStunPowderBomb(GameMap map) {

        for (Actor player : players) {
            for (Item item : map.locationOf(player).getItems()) {
                if (stunPowders.contains(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addStunPowder(Item stunPowder) {
        stunPowders.add(stunPowder);
    }

    public static void removeStunPowder(Item stunPowder) {
        stunPowders.remove(stunPowder);
    }
}
