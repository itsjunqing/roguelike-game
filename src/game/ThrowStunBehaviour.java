package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * A special Action that throws a StunPowderBomb to the player if player is within its visible range.
 */
public class ThrowStunBehaviour extends Action implements ActionFactory {

    private Actor target;
    private Random random = new Random();

    /**
     * Constructor to create an Action that throws a stun to a target Actor.
     *
     * @param target the target for the stun to be thrown
     */
    public ThrowStunBehaviour(Actor target) {
        this.target = target;
    }

    // pls rephrase this

    /**
     * Checks if the actor is able to throw a stun to the target
     * by verifying if target's position is within five squares of the actor's position.
     *
     * @param actor the actor performing the throwing stun
     * @param map   the map which the actor is on
     * @return this class if the stun is throwable and null if it is not
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        Location targetLocation = map.locationOf(target);

        boolean horizontalThrow = (actorLocation.y() == targetLocation.y()) && (Math.abs(actorLocation.x() - targetLocation.x()) <= 5);
        boolean verticalThrow = (actorLocation.x() == targetLocation.x()) && (Math.abs(actorLocation.y() - targetLocation.y()) <= 5);

        if (horizontalThrow || verticalThrow) {
            Range xs = new Range(Math.min(actorLocation.x(), targetLocation.x()), Math.abs(actorLocation.x() - targetLocation.x()) + 1);
            Range ys = new Range(Math.min(actorLocation.y(), targetLocation.y()), Math.abs(actorLocation.y() - targetLocation.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if (map.at(x, y).getGround().blocksThrownObjects()) {
                        return null;
                    }
                }
            }
            return this;
        }
        return null;
    }

    // rephrase this? idk

    /**
     * Throws a StunPowderBomb to the target with a chance of 50%.
     *
     * @param actor the actor performing the action.
     * @param map   the map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (random.nextDouble() <= 0.5) {
            Location playerLocation = map.locationOf(target);
            boolean stunExists = false;
            for (Item item : playerLocation.getItems()) {
                if (item instanceof StunPowderBomb) {
                    stunExists = true;
                }
            }
            if (!stunExists) {
                map.addItem(new StunPowderBomb("Stun Bomb"), playerLocation.x(), playerLocation.y());
                return menuDescription(actor);
            }
            return target + " is already stunned.";
        }
        return "Failed to stun " + target;
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor the actor performing the action.
     * @return a string, e.g. "Player is stunned for 2 turns"
     */
    @Override
    public String menuDescription(Actor actor) {
        return target + " is stunned for 2 turns";
    }

    /**
     * Returns an empty string, as throwing stun does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
