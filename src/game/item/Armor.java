package game.item;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;

public class Armor extends Item {

    public static final char ARMOR_CHAR = '|';

    public Armor(String name) {
        super(name, ARMOR_CHAR);
        addSkill(GameSkills.INVULNERABLE);
    }
}
