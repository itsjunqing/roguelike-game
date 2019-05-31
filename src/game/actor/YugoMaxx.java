package game.actor;

import edu.monash.fit2099.engine.*;
import game.GameSkills;
import game.GameWorld;
import game.action.BreakArmorAction;
import game.action.GameAttackAction;
import game.item.Armor;

/**
 * Class representing a YugoMaxx as a type of an Enemy.
 */
public class YugoMaxx extends Enemy {

    public static final char YUGO_MAXX_CHAR = 'Y';

    /**
     * Constructor to create a YugoMaxx.
     * By default, it has an Armor in its inventory.
     *
     * @param name name of the YugoMaxx.
     */
    public YugoMaxx(String name) {
        super(name, YUGO_MAXX_CHAR, 20, 10);
        addItemToInventory(new Armor("Exoskeleton"));
        addSkill(GameSkills.SPACEBOSSPOWER);
    }

    /**
     * Moves randomly in the map if it is not standing next to the player.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return an Action to be executed
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        super.addActions(actions, this, map);

        if (playerIsNextTo(map)) {
            for (Action action : actions) {
                if (action instanceof MoveActorAction) {
                    actions.remove(action);
                }
            }
        }

        return super.playTurn(actions, map, display);
    }

    /**
     * Returns a new IntrinsicWeapon with twice the damage of a base enemy damage and a new description when it attacks.
     *
     * @return an IntrinsicWeapon for YugoMaxx
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(Math.round(BASE_DAMAGE * 2), "pulverizes");
    }

    /**
     * Returns a collection of Action that other actor can do to YugoMaxx.
     * It allows other actors to break its armor if a WaterPistol is detected in the otherActor's inventory
     *
     * @param otherActor the GameActor beside the YugoMaxx
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of Actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();

        if (isInvulnerable()) {
            for (Item item : otherActor.getInventory()) {
                if (item.hasSkill(GameSkills.WATERSKILL)) {
                    actions.add(new BreakArmorAction(this, item, GameSkills.WATERSKILL, GameSkills.INVULNERABLE));
                }
            }
        } else {
            actions.add(new GameAttackAction(otherActor, this));
        }
        return actions;
    }


    /**
     * Returns if it is invulnerable to damage.
     *
     * @return true if it invulnerable to damage
     */
    private boolean isInvulnerable() {
        return this.hasSkill(GameSkills.INVULNERABLE);
    }

    /**
     * Returns true if player is next to it.
     *
     * @param map map which the YugoMaxx is on
     * @return true if player is next to the YugoMaxx
     */
    private boolean playerIsNextTo(GameMap map) {
        Location playerLocation = map.locationOf(GameWorld.getGamePlayer());
        Location yugoLocation = map.locationOf(this);

        for (Exit exit : yugoLocation.getExits()) {
            if (exit.getDestination().equals(playerLocation)) {
                return true;
            }
        }
        return false;
    }
}
