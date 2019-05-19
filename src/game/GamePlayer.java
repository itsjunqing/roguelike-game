package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing the GamePlayer.
 */
public class GamePlayer extends Player {

    private static ArrayList<Item> stunPowders = new ArrayList<>();
    public static final char GAME_PLAYER_CHAR = '@';
    private int stunCount = 0;

    /**
     * Constructor to create a GamePlayer.
     *
     * @param name      name of the player in the UI
     * @param priority  how early in the turn the player can act
     * @param hitPoints player's starting number of hitpoints
     */
    public GamePlayer(String name, int priority, int hitPoints) {
        super(name, GAME_PLAYER_CHAR, priority, hitPoints);
        Enemy.addPlayer(this);
        LockedDoor.addPlayer(this);
    }

    /**
     * Play a turn by displaying a menu to the user and getting their selected Action returned.
     * If it is stunned, can only skip its current action until the stun effect is gone (after 2 stuns).
     * Otherwise, it runs the default implementation of a Player.
     *
     * @param actions the actions to display
     * @param map     the map to display
     * @param display the object that performs the console I/O
     * @return a list of actions that the player is able to perform
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        Location playerLocation = map.locationOf(this);

        if (OxygenDispenser.getCount() != 0){
            OxygenDispenser.setCount(OxygenDispenser.getCount()-1);
        }
        for (Item item : playerLocation.getItems()) {
            if (stunPowders.contains(item)) {
                if (stunCount != 2) {
                    actions.clear();
                    actions.add(new SkipTurnAction());
                    stunCount++;
                    break;
                } else {
                    stunCount = 0;
                    playerLocation.removeItem(item);
                    break;
                }
            }
        }
        return super.playTurn(actions, map, display);
    }

    /**
     * Adds a StunPowder into the list of recognizable stunPowders as references.
     *
     * @param stunPowder an Item signifying the StunPowder
     */
    public static void addStunPowder(Item stunPowder) {
        stunPowders.add(stunPowder);
    }
}
