package game.ground;

import edu.monash.fit2099.engine.*;
import game.action.FillPistolAction;

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
            if (waterPistols.contains(item)) {
                actions.add(new FillPistolAction(item));
            }
        }
//        if (actor.hasSkill(MoonSkills.WATERSKILL)) {
//            for (Item item : actor.getInventory()) {
//                if (item.hasSkill(MoonSkills.WATERSKILL)) {
//                    actions.add(new FillPistolAction(item));
//                }
//            }
//        }
        return actions;
    }

    public static void addWaterPistol(Item waterPistol) {
        waterPistols.add(waterPistol);
    }
}
