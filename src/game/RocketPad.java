package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class RocketPad extends Ground {

    public RocketPad(Actor player, Location location, String direction) {
        super('=');
        allowableActions(player, location, direction).add(new BuildRocketAction());
    }
}
