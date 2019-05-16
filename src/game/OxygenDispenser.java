package game;

import edu.monash.fit2099.engine.*;

public class OxygenDispenser extends Ground {

    private int count = 0;

    public OxygenDispenser() {
        super('!');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
        if (actor instanceof Player) {
            actions.add(new GenerateOxygenTankAction(this, location));
        }

        if (count != 0) {
            count--;
            if (count == 0) {
                location.addItem(new OxygenTank("Tank"));
            }
        }
        return actions;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
