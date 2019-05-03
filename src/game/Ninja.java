package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing a Ninja.
 */
public class Ninja extends Enemy {

    private boolean stunThrown = false;
    private Actor player;

    // pls rephrase this, check line 14, is it necessary?

    /**
     * Constructor to create an Actor of type Enemy: Ninja with a name.
     * It takes in a Player of type Actor to represent the target for the Ninja to throw the stun.
     * By default, it has the ability of throwing a stun to a player.
     *
     * @param name   name of the Ninja
     * @param player the target player for Ninja to throw the stun
     */
    public Ninja(String name, Actor player) {
        super(name, 'N', 15, 50);
        this.player = player;
        addBehaviour(new ThrowStunBehaviour(player));
    }

    /**
     * Returns a new IntrinsicWeapon with the same BASE_DAMAGE of Enemy, but different attack name.
     * Overall implementation remains unchanged.
     *
     * @return a new IntrinsicWeapon with a different name
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(BASE_DAMAGE, "slices");
    }

    /*
    Ninja playturn has to check whether if he has thrown the stun or not, as every actor can only execute one action at one time,
    after ninja has thrown the stun, ninja has to check, if he has indeed thrown the stun, then he must move one space away from player
    this means that he will execute the moveactoraction randomly, it cannot be moved "exactly one space away from player" because
    in the case where if the ninja is at the top of the map, while player is positioned exactly below the ninja,
    then ninja cannot move further anymore (since it is at the top of the map). so ninja has to move in random direction
    after every stun being thrown

    if ninja has not thrown stun and unable to throw stun, means ninja is in hide, unable to see player, so ninja will only
    execute the skipturnaction.
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();

        if (hasStunPowderBomb(map) && stunThrown) {
            stunThrown = false;
            super.addActions(actions, player, map);

            for (Action action : actions) {
                if (action instanceof SkipTurnAction) {
                    actions.remove(action);
                } else if (action instanceof AttackAction) {
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
     * Checks f the player's location has a StunPowderBomb.
     *
     * @param map the map which the player is in it.
     * @return true if StunPowderBomb exists on the player's location and false otherwise
     */
    private boolean hasStunPowderBomb(GameMap map) {
        for (Item item : map.locationOf(player).getItems()) {
            if (item instanceof StunPowderBomb) {
                return true;
            }
        }
        return false;
    }


}
