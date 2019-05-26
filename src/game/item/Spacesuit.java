package game.item;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;

public class Spacesuit extends Item {

    public static final char SPACESUIT_CHAR = '[';

    public Spacesuit() {
        super("Spacesuit", SPACESUIT_CHAR);
        addSkill(GameSkills.SPACETRAVELLER);
    }
}
