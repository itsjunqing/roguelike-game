package game.item;

import edu.monash.fit2099.engine.Item;
import game.GameSkills;

/**
 * Class representing an Armor worn by an Actor to be invulnerable to damage.
 */
public class Armor extends Item {

    public static final char ARMOR_CHAR = '|';

    /**
     * Constructor to create an Armor.
     *
     * @param name name of the Armor
     */
    public Armor(String name) {
        super(name, ARMOR_CHAR);
        addSkill(GameSkills.INVULNERABLE);
    }
}
