package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An Action that creates a new Enemy at a Location.
 */
public class SpawnEnemyAction extends Action {

    private Actor enemy;
    private int x;
    private int y;

    /**
     * Constructor to create an Action that creates a new Enemy at a given Location.
     *
     * @param enemy the Actor to create
     * @param x the X Location to create the Enemy
     * @param y the Y Location to create the Enemy
     */
    public SpawnEnemyAction(Actor enemy, int x, int y) {
        this.enemy = enemy;
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new Enemy at the Location.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.addActor(enemy, x, y);
        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a string, e.g. "Necromances spawns a Zombie"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " spawns a " + actor;
    }

    /**
     * Returns an empty string, as spawning a new Enemy does not have a dedicated hotkey.
     *
     * @return an empty string
     */
    @Override
    public String hotKey() {
        return "";
    }
}
