package game.actor;

import edu.monash.fit2099.engine.*;
import game.MoonSkills;
import game.action.BreakArmorAction;
import game.item.Exoskeleton;
import game.item.Spacesuit;

public class YugoMaxx extends Enemy {

    public static final char YUGO_MAXX_CHAR = 'Y';
    // will refactor player later on, the game is a single player, we must remove the concept of multiplayers, it is tough to operate on multiplyers
    // cause the world can allow one player to play only, if second player is instantiated, it will override the old player, check world.addPlayer() method
    private Actor player;

    public YugoMaxx(String name, Actor player) {
        super(name, YUGO_MAXX_CHAR, 20, 10);
        this.player = player;

        addItemToInventory(new Exoskeleton());
        // spacesuit to allow to move on moon
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
                if (item.hasSkill(MoonSkills.WATERSKILL)) {
                    actions.add(new BreakArmorAction(this, item, MoonSkills.WATERSKILL, MoonSkills.INVULNERABLE));
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
        return this.hasSkill(MoonSkills.INVULNERABLE);
    }

    private boolean playerIsNextTo(GameMap map) {
        Location playerLocation = map.locationOf(player);
        Location yugoLocation = map.locationOf(this);

        for (Exit exit : yugoLocation.getExits()) {
            if (exit.getDestination() == playerLocation) {
                return true;
            }
        }
        return false;

    }
}
