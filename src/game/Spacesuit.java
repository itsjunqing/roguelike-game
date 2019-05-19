package game;

import edu.monash.fit2099.engine.Item;

public class Spacesuit extends Item {

    public static final char SPACESUIT_CHAR = '[';

    public Spacesuit(String name) {
        super(name, SPACESUIT_CHAR);
        addSkill(MoonSkills.SPACETRAVELLER);
    }
}
