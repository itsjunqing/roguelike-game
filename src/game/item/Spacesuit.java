package game.item;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;

/**
 * Class representing a Spacesuit that gives an Actor the ability to travel in space.
 */
public class Spacesuit extends Item {

    public static final char SPACESUIT_CHAR = 'P';

    /**
     * Constructor to create a Spacesuit.
     *
     * @param name name of the Spacesuit
     */
    public Spacesuit(String name) {
        super(name, SPACESUIT_CHAR);
        addSkill(GameSkills.SPACETRAVELLER);
    }
}
