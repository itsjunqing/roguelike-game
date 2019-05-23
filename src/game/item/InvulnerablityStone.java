package game.item;

import edu.monash.fit2099.engine.Item;
import game.MoonSkills;

public class InvulnerablityStone extends Item {

    public InvulnerablityStone() {
        super("Wonder Stone", 'S');
        addSkill(MoonSkills.INVULNERABLE);
    }
}
