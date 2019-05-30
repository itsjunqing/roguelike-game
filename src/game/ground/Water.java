package game.ground;

import edu.monash.fit2099.engine.*;
import game.GameSkills;
import game.action.FillItemSkillAction;

import java.util.ArrayList;

public class Water extends Ground {

    private static ArrayList<Item> waterPistols = new ArrayList<>();

    public Water() {
        super('*');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        for (Item item : actor.getInventory()) {
            if (canRefill(item)) {
                actions.add(new FillItemSkillAction(item, GameSkills.WATERSKILL));
            }
        }
        return actions;
    }

    private boolean canRefill(Item item) {
        if (waterPistols.contains(item) && !item.hasSkill(GameSkills.WATERSKILL)) {
            return true;
        }
        return false;
    }

    public static void addWaterPistol(Item waterPistol) {
        waterPistols.add(waterPistol);
    }
}
