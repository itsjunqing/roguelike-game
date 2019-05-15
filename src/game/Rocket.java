package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

import java.util.ArrayList;

/**
 * Class representing a Rocket.
 */
public class Rocket extends Item {

    public static final char ROCKET_CHAR = '^';

    /**
     * Constructor that creates a static rocket with a name.
     *
     * @param name the name of the rocket
     */
    public Rocket(String name) {
        super(name, ROCKET_CHAR);
        allowableActions.clear();
        allowableActions.add(new MoveActorAction(Application.getMoonMap().at(7,4), "to Moon!"));
//        allowableActions.add(new MoveActorAction(Application.getEarthMap().at(6,2), "to Earth!"));
    }



}
