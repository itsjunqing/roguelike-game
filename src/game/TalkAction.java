package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An Action that allows the Actor to 'speak' by printing a message on the display.
 */
public class TalkAction extends Action {

    private String message;
    private Actor target;

    /**
     * Constructor to create an Action that takes in the Actor that performs the talk and the other Actor that is talking to.
     *
     * @param message a message to talk to the actor
     * @param target  the actor to be talked
     */
    public TalkAction(String message, Actor target) {
        this.message = message;
        this.target = target;
    }

    /**
     * Prints out the target's talk message.
     *
     * @param actor the actor performing the action
     * @param map   the map the actor is on
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return target + ": " + message;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player talks to Q"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + target;
    }

    /**
     * Returns an empty string, as talking does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
