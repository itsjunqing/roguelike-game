package game;

import edu.monash.fit2099.engine.*;

public class DoctorMaybe extends Enemy {

    /**
     * Constructor to create a new Enemy of type DoctorMaybe with a name
     *
     * @param name the name of the DoctorMaybe
     */
    public DoctorMaybe(String name) {
        super(name, 'D', 10, 10);
        super.addItemToInventory(new RocketEngine("Rocket Engine"));
    }

    /**
     * Creates a new IntrinsicWeapon that halves the base damage with a new Description.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon((int) Math.round(BASE_DAMAGE * 0.5), "knocks");
    }

    // pls rephrase the description

    /**
     * Allows the DoctorMaybe to attack if the actor next to it is a Player and
     * does not allow it to move anywhere.
     *
     * @param actions collection of possible Actions for this Actor
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed, e.g. attacking the player when it is next to it
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        super.addActions(actions, this, map);

        for (Action action : actions) {
            if (action instanceof MoveActorAction) {
                actions.remove(action);
            }
        }

//        Location qLocation = map.locationOf(this);
//        actions.clear();
//        for (Exit exit : qLocation.getExits()) {
//            Location destination = exit.getDestination();
//            if (map.isAnActorAt(destination)) {
//                Actor actor = map.actorAt(destination);
//                if (actor instanceof Player){
//                    actions.add(new AttackAction(this, actor));
//                }
//            }
//        }

        return super.playTurn(actions, map, display);
    }
}
