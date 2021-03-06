package game.actor;

import edu.monash.fit2099.engine.*;
import game.GameWorld;
import game.behaviour.ThrowStunBehaviour;
import game.item.StunPowder;

/**
 * Class representing Ninja as a form of an Enemy.
 */
public class Ninja extends Enemy {

    public static final char NINJA_CHAR = 'N';
    private boolean stunThrown = false;
    private Item stunPowder = new StunPowder("Stun Powder");

    /**
     * Constructor to create an Enemy of type Ninja with a name.
     * It has an ability of throwing stun to a player.
     *
     * @param name name of the Ninja
     */
    public Ninja(String name) {
        super(name, NINJA_CHAR, 15, 50);
        addBehaviour(new ThrowStunBehaviour(GameWorld.getGamePlayer(), stunPowder));
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

        Action action = executeBehaviours(map);
        if (action != null) {
            stunThrown = true;
            return action;
        }
        actions.add(new SkipTurnAction());
        return super.playTurn(actions, map, display);
    }

    /**
     * Returns true if a StunPowder is detected on the player's location.
     *
     * @param map the map containing the player
     * @return true if and only if the StunPowder exists on the player's location
     */
    private boolean hasStunPowder(GameMap map) {
        for (Item item : map.locationOf(GameWorld.getGamePlayer()).getItems()) {
            if (item.equals(stunPowder)) {
                return true;
            }
        }
        return false;
    }

}
