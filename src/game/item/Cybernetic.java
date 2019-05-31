package game.item;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;

/**
 * Class representing a Cybernetic implanted to an Actor to have energy to move in space.
 */
public class Cybernetic extends Item {

    public static final char CYBERNETIC_CHAR = 'C';

    /**
     * Constructor to create a Cybernetic.
     *
     * @param name name of the Cybernetic
     */
    public Cybernetic(String name) {
        super(name, CYBERNETIC_CHAR);
        addSkill(GameSkills.SPACEENERGY);
    }
}
