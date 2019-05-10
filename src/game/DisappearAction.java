package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class DisappearAction extends Action {

    public DisappearAction() {
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " disappears into the air with a cherry wave ~~~";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
