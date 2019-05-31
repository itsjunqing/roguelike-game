package game.action;

import edu.monash.fit2099.engine.*;
import game.GameSkills;
import game.actor.Necromancer;

import java.util.Random;

/**
 * Special Action for attacking other Actors that transfers Actor skills to their unconscious body.
 */
public class GameAttackAction extends AttackAction {

    private Actor subject;
    private Random rand = new Random();

    /**
     * Constructor to create an Action to damage an Actor
     *
     * @param actor   the Actor that inflicts the damage
     * @param subject the Actor to be damaged
     */
    public GameAttackAction(Actor actor, Actor subject) {
        super(actor, subject);
        this.subject = subject;
    }

    /**
     * Damages the subject Actor based on the weapon selected by the Actor then checks if the subject is still
     * conscious. If the subject is no longer conscious, it will then create an Item to represent the unconscious body
     * of the subject and drops all Items in the subjects inventory. Then, it passes all Skills from the Actor to the
     * unconscious body.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();

        if (rand.nextBoolean()) {
            return actor + " misses " + subject + ".";
        }

        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + subject + " for " + damage + " damage.";

        subject.hurt(damage);
        if (!subject.isConscious()) {
            //Drop all the items that are droppable.
            Item sleepingActor = new Item("Sleeping " + subject, '%');
            Necromancer.addDeadEnemy(sleepingActor);
            map.locationOf(subject).addItem(sleepingActor);
            for (Item item : subject.getInventory()) {
                for (Action action : item.getAllowableActions()) {
                    if (action instanceof DropItemAction) {
                        action.execute(subject, map);
                        break;
                    }
                }
            }

            // Modified from original AttackAction. To add skills to the unconscious actors.
            for (GameSkills skill : GameSkills.values()) {
                if (subject.hasSkill(skill)) {
                    sleepingActor.addSkill(skill);
                    subject.removeSkill(skill);
                }
            }
            // Modified from original AttackAction

            map.removeActor(subject);
            result += System.lineSeparator() + subject + " is knocked out.";
        }

        return result;
    }
}


