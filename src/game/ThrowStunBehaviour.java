package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

public class ThrowStunBehaviour extends Action implements ActionFactory {

    private Actor target;
    private Random random = new Random();

    public ThrowStunBehaviour(Actor target) {
        this.target = target;
    }

    // this getAction checks whether if it is possible to throw a stun, if unable to throw, it returns null
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location ninjaLocation = map.locationOf(actor);
        Location playerLocation = map.locationOf(target);

        boolean horizontalThrow = (ninjaLocation.y() == playerLocation.y()) && (Math.abs(ninjaLocation.x()-playerLocation.x()) <= 5);
        boolean verticalThrow = (ninjaLocation.x() == playerLocation.x()) && (Math.abs(ninjaLocation.y()-playerLocation.y()) <= 5);

        if (horizontalThrow || verticalThrow) {
            Range xs = new Range(Math.min(ninjaLocation.x(), playerLocation.x()), Math.abs(ninjaLocation.x() - playerLocation.x()) + 1);
            Range ys = new Range(Math.min(ninjaLocation.y(), playerLocation.y()), Math.abs(ninjaLocation.y() - playerLocation.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if (map.at(x, y).getGround().blocksThrownObjects()) {
                        return null;
                    }
                }
            }
            return this;
        }
        return null;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (random.nextDouble() <= 0.5) {
            Location playerLocation = map.locationOf(target);
            boolean stunExists = false;
            for (Item item : playerLocation.getItems()) {
                if (item instanceof StunPowderBomb) {
                    stunExists = true;
                }
            }
            if (!stunExists) {
                map.addItem(new StunPowderBomb("Stun Bomb"), playerLocation.x(), playerLocation.y());
                return target + " is stunned for 2 turns.";
            }
            return target + " is already stunned.";
        }
        return "Failed to stun " + target;
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
