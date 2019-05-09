package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Class representing the GamePlayer.
 */
public class GamePlayer extends Player {

    private int stunCount = 0;
    public static final char GAME_PLAYER_CHAR = '@';
    private static ArrayList<Item> stunPowderBombs = new ArrayList<>();

    /**
     * Constructor to create a GamePlayer.
     *
     * @param name        name of the player in the UI
     * @param priority    how early in the turn the player can act
     * @param hitPoints   player's starting number of hitpoints
     */
    public GamePlayer(String name, int priority, int hitPoints) {
        super(name, GAME_PLAYER_CHAR, priority, hitPoints);
        Enemy.addTarget(this);
    }

    /**
     * Play a turn by displaying a menu to the user and getting their selected Action returned.
     * If it is stunned, the player can only skip its current action.
     * If it is not, the player runs the default implementation.
     *
     * @param actions the actions to display
     * @param map     the map to display
     * @param display the object that performs the console I/O
     * @return a list of actions that the player is able to perform
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        Location playerLocation = map.locationOf(this);
        for (Item item : playerLocation.getItems()) {
            if (stunPowderBombs.contains(item)) {
                if (stunCount != 2) {
                    actions.clear();
                    actions.add(new SkipTurnAction());
                    stunCount++;
                    break;
                } else {
                    stunCount = 0;
                    playerLocation.removeItem(item);
                    Ninja.removeStunPowderBomb(item);
                    stunPowderBombs.remove(item);
                    break;
                }
            }
        }
        return super.playTurn(actions, map, display);
    }

    public static void addStunPowderBomb(Item stunPowderBomb) {
        stunPowderBombs.add(stunPowderBomb);
    }
}
