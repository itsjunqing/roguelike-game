package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class UnlockDoorAction extends Action {

    private String direction;
    private Location doorLocation;
    private Key key;

    public UnlockDoorAction(String direction, Location doorLocation, Key key) {
        this.direction = direction;
        this.doorLocation = doorLocation;
        this.key = key;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.add(new Floor(), doorLocation);
        actor.removeItemFromInventory(key);
        return "The door is unlocked";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks the door to the " + direction;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
