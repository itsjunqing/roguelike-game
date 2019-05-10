package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing Ninja as a form of an Enemy.
 */
public class Ninja extends Enemy {

    public static final char NINJA_CHAR = 'N';
    private boolean stunThrown = false;
    private Item stunPowder = new StunPowder("Stun Powder");

    /**
     * Constructor to create an Enemy of type Ninja with a name.
     * It takes in a player of Actor type to represent the target for it to throw the stun.
     *
     * @param name   name of the Ninja
     */
    public Ninja(String name) {
        super(name, NINJA_CHAR, 15, 50);
        for (Actor player : players) {
            addBehaviour(new ThrowStunBehaviour(player, stunPowder));
        }
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

        if (hasStunPowder(map) && stunThrown) {
            stunThrown = false;
            super.addActions(actions, this, map);
            for (Action action : actions) {
                if (action instanceof AttackAction) {
                    actions.remove(action);
                }
            }
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
    private boolean hasStunPowder(GameMap map) {
        for (Actor player : players) {
            for (Item item : map.locationOf(player).getItems()) {
                if (item == stunPowder) {
                    return true;
                }
            }
        }
        return false;
    }
}
