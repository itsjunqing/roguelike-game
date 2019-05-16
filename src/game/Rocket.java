package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Rocket.
 */
public class Rocket extends Item {

    public static final char ROCKET_CHAR = '^';
    private Actor player;

    /**
     * Constructor that creates a static rocket with a name.
     *
     * @param name the name of the rocket
     */
    public Rocket(String name, Actor player) {
        super(name, ROCKET_CHAR);
        this.player = player;
        allowableActions.clear();
    }

    @Override
    public Actions getAllowableActions() {
        for (Item item : player.getInventory()) {
            if (item instanceof Spacesuit) {
                return allowableActions;
            }
        }
        Actions actions = allowableActions;
        actions.clear();
        return actions;
    }
}
