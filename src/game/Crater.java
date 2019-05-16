package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class Crater extends Ground {
    public Crater(){
        super('o');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasSkill(MoonSkills.SPACETRAVELLER);
    }
}
