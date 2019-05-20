package game;

import edu.monash.fit2099.engine.Item;

public class InvulnerablityStone extends Item {

    public InvulnerablityStone() {
        super("Wonder Stone", 'S');
        addSkill(MoonSkills.INVULNERABLE);
    }
}
