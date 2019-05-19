package game;

import edu.monash.fit2099.engine.*;

public class OxygenDispenser extends Ground {

    private static int count = 0;
    private static boolean countdown = false;

    public OxygenDispenser() {
        super('!');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        if (actor instanceof Player) {
            actions.add(new GenerateOxygenTankAction(this, location));
        }

        if (count == 0 && countdown) {
            countdown = false;
            location.addItem(new OxygenTank("Tank"));
        }
        return actions;
    }

    public static void setCount(int newCount) {
        if (newCount > 0){
            countdown = true;
        }
        count = newCount;
    }

    public static int getCount() {
        return count;
    }

}
