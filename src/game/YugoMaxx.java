package game;

import edu.monash.fit2099.engine.*;

public class YugoMaxx extends Enemy {

    public static final char YUGO_MAXX_CHAR = 'Y';

    public YugoMaxx(String name) {
        super(name, YUGO_MAXX_CHAR, 20, 10);
        addItemToInventory(new InvulnerablityStone());
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(Math.round(BASE_DAMAGE * 2), "pulverizes");
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (this.hasSkill(MoonSkills.INVULNERABLE)) {
            for (Item item : otherActor.getInventory()) {
                if (item.hasSkill(MoonSkills.WATERSKILL)) {
                    actions.add(new BreakArmorAction(this, item));
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

        Location location = map.locationOf(this);

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor player = map.actorAt(destination);
                if (players.contains(player)) {
                    for (Action action : actions) {
                        if (action instanceof MoveActorAction) {
                            actions.remove(action);
                        }
                    }
                }
            }
        }

//        for (Action action : actions) {
//            if (action instanceof MoveActorAction) {
//                actions.remove(action);
//            }
//        }
        return super.playTurn(actions, map, display);
    }
}
