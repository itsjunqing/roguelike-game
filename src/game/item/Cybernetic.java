package game.item;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;

public class Cybernetic extends Item {

    public static final char CYBERNETIC_CHAR = 'C';

    public Cybernetic(String name) {
        super(name, CYBERNETIC_CHAR);
        addSkill(GameSkills.SPACEENERGY);
    }
}
