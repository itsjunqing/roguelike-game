package game.item;

import edu.monash.fit2099.engine.*;
import game.GameWorld;

/**
 * Class representing a Rocket.
 */
public class Rocket extends Item {

    public static final char ROCKET_CHAR = '^';
    private Location earthLocation;
    private Location moonLocation;

    /**
     * Constructor that creates a static rocket with a name.
     *
     * @param name the name of the rocket
     */
    public Rocket(String name, Location earthLocation, Location moonLocation) {
        super(name, ROCKET_CHAR);
        this.earthLocation = earthLocation;
        this.moonLocation = moonLocation;
        allowableActions.clear();
    }

    /**
     * Allows Actor to move to Moon while it is on Earth and move to Earth while it is on Moon.
     *
     * @return a collection of Action which the actor can perform
     */
    @Override
    public Actions getAllowableActions() {
        allowableActions.clear();

        if (earthLocation.containsActor() && earthLocation.getActor().equals(GameWorld.getGamePlayer())) {
            allowableActions.add(new MoveActorAction(moonLocation, "to Moon!"));
        } else if (moonLocation.containsActor() && moonLocation.getActor().equals(GameWorld.getGamePlayer())) {
            allowableActions.add(new MoveActorAction(earthLocation, "to Earth!"));
        }
        return allowableActions;
    }
}
