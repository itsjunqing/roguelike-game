package game;

import edu.monash.fit2099.engine.Item;

public class Spacesuit extends Item {

    public static final char SPACESUIT_CHAR = '[';

    public Spacesuit() {
        super("Spacesuit", SPACESUIT_CHAR);
        addSkill(MoonSkills.SPACETRAVELLER);
    }
}
