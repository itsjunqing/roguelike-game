package game.actor;

import edu.monash.fit2099.engine.*;
import game.action.GenerateOxygenTankAction;
import game.action.PushDispenserButtonAction;

public class OxygenDispenser extends Actor {

    private int dispenserCount = 0;
    private Location tankLocation = null;
    private GamePlayer player;

    public OxygenDispenser(String name, GamePlayer player) {
        super(name, '!', 15, Integer.MAX_VALUE);
        this.player = player;
    }

    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        if (dispenserCount != 0) {
            dispenserCount--;
            if (dispenserCount == 0) {
                return new GenerateOxygenTankAction(tankLocation, player);
            }
        }
        return new SkipTurnAction();
    }

    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();

        if (otherActor instanceof Player) {
            actions.add(new PushDispenserButtonAction(this));
        }

        return actions;
    }

    public void setDispenserCount(int dispenserCount) {
        this.dispenserCount = dispenserCount;
    }

    public int getDispenserCount() {
        return dispenserCount;
    }

    public void setTankLocation(Location tankLocation) {
        this.tankLocation = tankLocation;
    }

    public Location getTankLocation() {
        return tankLocation;
    }
}
