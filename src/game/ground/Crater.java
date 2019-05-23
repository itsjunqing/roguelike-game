package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.MoonSkills;

public class Crater extends Ground {
    public Crater(){
        super('o');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasSkill(MoonSkills.SPACETRAVELLER);
    }
}
