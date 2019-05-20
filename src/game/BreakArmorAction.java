package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.util.Random;

public class BreakArmorAction extends Action {

    private Actor target;
    private Item pistol;
    private Random random = new Random();

    public BreakArmorAction(Actor target, Item pistol) {
        this.target = target;
        this.pistol = pistol;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        pistol.removeSkill(MoonSkills.WATERSKILL);

        if (random.nextDouble() <= 0.7) {
            for (Item item : target.getInventory()) {
                if (item.hasSkill(MoonSkills.INVULNERABLE)) {
                    target.removeItemFromInventory(item);
                    return target + "'s armor is destroyed";
                }
            }
        }
        return "Failed to destroy " + target + "'s armor";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoots " + target + " with the Water Pistol";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
