package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class TalkAction extends Action {

    private String message;

    public TalkAction(String message) {
        this.message = message;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return actor + ": " + message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
