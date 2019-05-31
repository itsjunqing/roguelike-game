package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An Action that allows the player to end the game at any time.
 */
public class EndGameAction extends Action {

    /**
     * Constructor to create an Action that will end the Game.
     */
    public EndGameAction() {
    }

    /**
     * Ends the Game by removing the Player from the Map thus resulting in the player loses the Game.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a String stating that the player decided to end the Game.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Player ends the game";
    }

    /**
     * A string suitable for describing the action in the UI display.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return "End the game.";
    }

    /**
     * A string to determine if the Player wants to end the game.
     *
     * @return a string
     */
    @Override
    public String hotKey() {
        return "=";
    }
}
