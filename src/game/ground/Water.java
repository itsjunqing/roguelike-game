package game.ground;

import edu.monash.fit2099.engine.*;
import game.GameSkills;
import game.action.FillItemSkillAction;

import java.util.ArrayList;

/**
 * Class representing a Water.
 */
public class Water extends Ground {

    private static ArrayList<Item> waterPistols = new ArrayList<>();

    /**
     * Constructor to create a Water.
     */
    public Water() {
        super('*');
    }

    /**
     * Stops the actor from moving on the Water.
     *
     * @param actor actor approaching the Water
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Allows the actor to refill a water pistol with a WATERSKILL if it is not refilled.
     *
     * @param actor     the Actor beside the Water
     * @param location  the Location beside the Water
     * @param direction the direction of the Water from the Actor
     * @return a collection of Action which the actor adjacent to it can perform
     */
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

    /**
     * Returns true if the item is a WaterPistol and it has not been refilled.
     *
     * @param item item to be refilled
     * @return true or false
     */
    private boolean canRefill(Item item) {
        if (waterPistols.contains(item) && !item.hasSkill(GameSkills.WATERSKILL)) {
            return true;
        }
        return false;
    }

    /**
     * Adds a WaterPistol into the list of recognizable waterPistols as references.
     *
     * @param waterPistol a WaterPistol
     */
    public static void addWaterPistol(Item waterPistol) {
        waterPistols.add(waterPistol);
    }
}
