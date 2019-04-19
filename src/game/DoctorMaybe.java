package game;

import edu.monash.fit2099.engine.*;

public class DoctorMaybe extends Enemy {

    public DoctorMaybe(String name) {
        super(name, 'D', 10, 25);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon( (int) Math.round(super.getDamage() * 0.5), "knocks");
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        return new Actions(new AttackAction(otherActor, this));
    }

}
