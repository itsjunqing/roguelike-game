package game;

import edu.monash.fit2099.engine.*;

public class Ninja extends Enemy {

    private boolean stunThrown = false;
    private Actor player;

    public Ninja(String name, Actor player) {
        super(name, 'N', 15, 50);
        this.player = player;
        addBehaviour(new ThrowStunBehaviour(player));
    }

    /*
    Changing the attack name of the Ninja, overall implementation remain unchanged
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

    private boolean hasStunPowderBomb(GameMap map) {
        for (Item item : map.locationOf(player).getItems()) {
            if (item instanceof StunPowderBomb) {
                return true;
            }
        }
        return false;
    }


}
