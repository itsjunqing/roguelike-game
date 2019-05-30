package game.action;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class SpawnEnemyAction extends Action {

    private Actor enemy;
    private int x;
    private int y;

    public SpawnEnemyAction(Actor enemy, int x, int y) {
        this.enemy = enemy;
        this.x = x;
        this.y = y;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.addActor(enemy, x, y);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " spawns a " + actor;
    }

    @Override
    public String hotKey() {
        return "";
    }
}
