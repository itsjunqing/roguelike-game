package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.IntrinsicWeapon;

public class Goon extends Enemy {

    public Goon(String name, Actor player) {
        super(name, 'o', 10, 50);
        addBehaviour(new FollowBehaviour(player));
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(super.getDamage() * 2, "punches");
    }

}
