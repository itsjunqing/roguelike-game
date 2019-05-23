package game.item;

import edu.monash.fit2099.engine.Item;
import game.MoonSkills;

public class Exoskeleton extends Item {

    public Exoskeleton() {
        super("Skeleton", 'S');
        addSkill(MoonSkills.INVULNERABLE);
    }
}
