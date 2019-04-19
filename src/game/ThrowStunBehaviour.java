package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class ThrowStunBehaviour extends Action implements ActionFactory {

    private Actor target;
    private Random random = new Random();

    public ThrowStunBehaviour(Actor target) {
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location ninjaLocation = map.locationOf(actor);
        Location playerLocation = map.locationOf(target);

        if (ninjaLocation.x() == playerLocation.x() || ninjaLocation.y() == playerLocation.y()) {
            Range xs = new Range(Math.min(ninjaLocation.x(), playerLocation.x()), Math.abs(ninjaLocation.x() - playerLocation.x()) + 1);
            Range ys = new Range(Math.min(ninjaLocation.y(), playerLocation.y()), Math.abs(ninjaLocation.y() - playerLocation.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if(map.at(x, y).getGround().blocksThrownObjects())
                        return null;
                }
            }
            return this;
        }
        return null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (random.nextDouble() <= 0.50) {
            return "Oh no, failed to stun " + actor;
        } else {
            getAction(actor, map);
            return "Successfully stunned " + actor + " for two turns";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return "";
    }

    @Override
    public String hotKey() {
        return "";
    }
}
