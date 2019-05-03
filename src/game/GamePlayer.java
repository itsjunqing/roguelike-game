package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class GamePlayer extends Player {

    private int stunCount = 0;

    /**
     * Constructor.
     *
     * @param name Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param priority How early in the turn the player can act
     * @param hitPoints Player's starting number of hitpoints
     */
    public GamePlayer(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
    }

    /*
    when player is executing its turn, he has to check whether if he is already stunned or not,
    if he's already stunned, then he will not be able to move
    this checking of stun MUST be done in the player's playturn method itself, because playturn method itself
    returns the action to be executed during that turn. in other words, playturn functions to check the action
    that it can be executed in that particular turn. this can't be put outside of this class
    */

    // Check on the description of the return statement.
    /**
     * Checks if the player is able to move. If the player is 'stunned' and unable to move, then it forces the Player
     * to skip a turn. If the player is not 'stunned', then it displays a menu to the user gets their selected option.
     *
     * @param actions the actions to display
     * @param map the map to display
     * @param display the object that performs the console I/O
     * @return a list of actions that the player is able to perform
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        Location playerLocation = map.locationOf(this);
        for (Item item : playerLocation.getItems()) {
            if (item instanceof StunPowderBomb) {

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

}
