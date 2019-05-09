package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing DoctorMaybe as a type of an Enemy.
 */
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
     * Creates a new IntrinsicWeapon with half the damage of a base enemy damage with a new description.
     *
     * @return an IntrinsicWeapon suitable for DoctorMaybe
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon((int) Math.round(BASE_DAMAGE * 0.5), "zaps");
    }

    /**
     * Allows DoctorMaybe to only attack the Player or skips its turn. If the player is not beside DoctorMaybe,
     * DoctorMaybe is only allows to skip its turn.
     *
     * @param actions collection of possible Actions for DoctorMaybe in the turn
     * @param map     the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed, e.g. attacking the player when it is next to it
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {
        actions.clear();
        Location location = map.locationOf(this);

        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (map.isAnActorAt(destination)) {
                Actor actor = map.actorAt(destination);
                if (players.contains(actor)) {
                    actions.add(new AttackAction(this, actor));
                }
            }
        }
        actions.add(new SkipTurnAction());



//        super.addActions(actions, this, map);

//        for (Action action : actions) {
//            if (action instanceof MoveActorAction) {
//                actions.remove(action);
//            }
//        }
        return super.playTurn(actions, map, display);
    }
}
