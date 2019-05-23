package game.item;

import edu.monash.fit2099.engine.*;
import game.GamePlayer;
import game.MoonSkills;

/**
 * Class representing a Rocket.
 */
public class Rocket extends Item {

    public static final char ROCKET_CHAR = '^';
    private Actor player;
    private Location earthLocation;
    private Location moonLocation;

    /**
     * Constructor that creates a static rocket with a name.
     *
     * @param name the name of the rocket
     */
    public Rocket(String name, Actor player, Location earthLocation, Location moonLocation) {
        super(name, ROCKET_CHAR);
        this.player = player;
        this.earthLocation = earthLocation;
        this.moonLocation = moonLocation;
        allowableActions.clear();
        GamePlayer.setRocketLocation(earthLocation);
    }

    @Override
    public Actions getAllowableActions() {
        allowableActions.clear();

        if (player.hasSkill(MoonSkills.SPACETRAVELLER)) {
//            if (earthLocation.getActor() == player && player.hasSkill(MoonSkills.OXYGENSUPPLY)) {
            if (earthLocation.getActor() == player) {
                allowableActions.add(new MoveActorAction(moonLocation, "to Moon!"));
            } else if (moonLocation.getActor() == player) {
                allowableActions.add(new MoveActorAction(earthLocation, "to Earth!"));
            }
        }
        return allowableActions;
    }
}