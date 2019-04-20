package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class TalkAction extends Action {

    private String message;
    private Actor target;

    public TalkAction(String message, Actor target) {
        this.message = message;
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return target + ": " + message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks to " + target;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
