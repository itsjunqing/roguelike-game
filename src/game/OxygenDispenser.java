package game;

import edu.monash.fit2099.engine.*;

public class OxygenDispenser extends Ground {

    private boolean generated = false;
    private static int counter = 0;
    public OxygenDispenser() {
        super('!');
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = super.allowableActions(actor, location, direction);
//        if (actor instanceof Player) {
//            actions.add(new GenerateOxygenTankAction(location));
//        }
//        return actions;

        // Check if player pressed button
        if (counter != 0) {
        //
            counter--;

            // Check if time to create tank
            if (counter == 0) {
            //

                location.addItem(new OxygenTank("Tank"));
            }
        }
        else{
            if (actor instanceof Player){
                actions.add(new GenerateOxygenTankAction(location));
            }
        }
        return actions;


    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public static void setCounter(int count){
        counter = count;
    }
}
