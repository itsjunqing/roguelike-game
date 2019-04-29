package game;

import edu.monash.fit2099.engine.*;

public class GamePlayer extends Player {

    private int stunCount = 0;

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
