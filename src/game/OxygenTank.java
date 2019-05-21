package game;

import edu.monash.fit2099.engine.Item;

public class OxygenTank extends Item {

    public static final char OXYGENTANK_CHAR = 'T';
    public static final int oxygenCount = 10;

    public OxygenTank(String name) {
        super(name, OXYGENTANK_CHAR);
        addSkill(MoonSkills.OXYGENSUPPLY);
        GamePlayer.addTank(this);
        GamePlayer.addOCount(10);
    }
}
