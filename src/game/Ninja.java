package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing Ninja as a form of an Enemy.
 */
public class Ninja extends Enemy {

    private boolean stunThrown = false;
    private Actor player;
    private static ArrayList<Item> stunPowderBombs = new ArrayList<>();

    // pls rephrase this, check line 14, is it necessary?

    /**
     * Constructor to create an Enemy of type Ninja with a name.
     * It takes in a Player of type Actor to represent the target for the Ninja to throw the stun.
     * Adds an ability for Ninja to throw a stun on the Player.
     *
     * @param name   name of the Ninja
     * @param player the target actor for Ninja to throw the stun
     */
    public Ninja(String name, Actor player) {
        super(name, 'N', 15, 50);
        this.player = player;
        addBehaviour(new ThrowStunBehaviour(player));
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

    /**
     * Checks if the player is stunned and checks if it threw the stun the previous turn. If the player is stunned and
     * it stunned the player in the previous turn, then it will move 1 step in a random direction. If the player is not
     * stunned, it checks if it is able to stun the player and stuns the player if it is able to. It will just skip
     * its turn for any other scenarios.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return  the Action to be performed, e.g. attacking the player when it is next to it
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
     * @param map   the map containing the player
     * @return  a boolean stating if the stun powder bomb exists
     */
    private boolean hasStunPowderBomb(GameMap map) {
        for (Item item : map.locationOf(player).getItems()) {
            if (stunPowderBombs.contains(item)) {
                return true;
            }
        }
        return false;
    }

//    @Override
//    protected void addActions(Actions actions, GameMap map) {
//        Location location = map.locationOf(this);
//        for (Exit exit : location.getExits()) {
//            Location destination = exit.getDestination();
//            Ground adjacentGround = map.groundAt(destination);
//            actions.add(adjacentGround.getMoveAction(this, destination, exit.getName(), exit.getHotKey()));
//        }
//    }


    public static void addStunPowderBomb(Item stunPowderBomb) {
        stunPowderBombs.add(stunPowderBomb);
    }

    public static void removeStunPowderBomb(Item stunPowderBomb) {
        stunPowderBombs.remove(stunPowderBomb);
    }
}
