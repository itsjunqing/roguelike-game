package game.ground;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.GameSkills;

/**
 * Class representing the Crater on the moon.
 */
public class Crater extends Ground {

    /**
     * Constructor to create a Crater.
     */
    public Crater(){
        super('o');
    }

    /**
     * Allows Actor to move only if it has SPACETRAVELLER skill or SPACEENERGY skill.
     *
     * @param actor actor moving on crater
     * @return true or false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasSkill(GameSkills.SPACETRAVELLER) || actor.hasSkill(GameSkills.SPACEENERGY);
    }
}
