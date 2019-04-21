package game;

import edu.monash.fit2099.engine.*;

public class Ninja extends Enemy {

    boolean stunThrown = false;
    private Actor player;

    public Ninja(String name, Actor player) {
        super(name, 'N', 15, 50);
        addBehaviour(new ThrowStunBehaviour(player));
        this.player = player;
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(super.getDamage(), "slices");
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

        if (hasStunPowderBomb(map) && stunThrown) {
            stunThrown = false;

            /*
            I might be thinking to remove the skipturnaction here. because playturn executes random actions,
            there might be possibility where the ninja has thrown stun and does not move one step.
            so i was thinking to remove the skipturnaction, but it didn't work, as the new Skipturnaction initialize a new instance and
            it can't be removed from the action (different memory).

            my approach is that clearing off the actions and repeat the find exit to get the list of possible movements
            without the skipturn
             */

            for (Action action : actions) {
                if (action instanceof SkipTurnAction) {
                    actions.remove(action);
                } else if (action instanceof DropItemAction) {
                    actions.remove(action);
                }
            }

            return super.playTurn(actions, map, display);
        }

        actions.clear();
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

    private boolean hasStunPowderBomb(GameMap map) {
        for (Item item : map.locationOf(player).getItems()) {
            if (item instanceof StunPowderBomb) {
                return true;
            }
        }
        return false;
    }


}
