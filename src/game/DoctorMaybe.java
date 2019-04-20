package game;

import edu.monash.fit2099.engine.*;

public class DoctorMaybe extends Enemy {

    public DoctorMaybe(String name) {
        super(name, 'D', 10, 25);
        super.addItemToInventory(new RocketEngine("Rocket Engine"));
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon( (int) Math.round(super.getDamage() * 0.5), "knocks");
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        Location qLocation = map.locationOf(this);
        actions.clear();
        for (Exit exit : qLocation.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor actor = map.actorAt(destination);
                if (actor instanceof Player){
                    actions.add(new AttackAction(this, actor));
                }
            }
        }
        actions.add(new SkipTurnAction());
        return super.playTurn(actions, map, display);
    }
}
