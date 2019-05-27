package game.actor;

import edu.monash.fit2099.engine.*;
import game.GameSkills;
import game.GameWorld;
import game.action.BreakArmorAction;
import game.item.Exoskeleton;
import game.item.Spacesuit;

public class YugoMaxx extends Enemy {

    public static final char YUGO_MAXX_CHAR = 'Y';

    public YugoMaxx(String name) {
        super(name, YUGO_MAXX_CHAR, 20, 10);
        addItemToInventory(new Exoskeleton());
        addItemToInventory(new Spacesuit());
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(Math.round(BASE_DAMAGE * 2), "pulverizes");
    }

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
            actions.add(new AttackAction(otherActor, this));
        }
        return actions;
    }


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

    private boolean isInvulnerable() {
        return this.hasSkill(GameSkills.INVULNERABLE);
    }

    private boolean playerIsNextTo(GameMap map) {
        Location playerLocation = map.locationOf(GameWorld.getGamePlayer());
        Location yugoLocation = map.locationOf(this);

        for (Exit exit : yugoLocation.getExits()) {
            if (exit.getDestination() == playerLocation) {
                return true;
            }
        }
        return false;
    }
}
