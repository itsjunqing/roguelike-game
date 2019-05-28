package game.action;

import edu.monash.fit2099.engine.*;
import game.GameSkills;

import java.util.Random;

public class BetterAttackAction extends AttackAction {

    private Actor subject;
    private Random rand = new Random();

    public BetterAttackAction(Actor actor, Actor subject){
        super(actor, subject);
        this.subject = subject;
    }

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
            if (subject.hasSkill(GameSkills.SPACEBOSSPOWER)){
                sleepingActor.addSkill(GameSkills.SPACEBOSSPOWER);
            }
            // Modified from original AttackAction

            map.removeActor(subject);
            result += System.lineSeparator() + subject + " is knocked out.";
        }

        return result;
    }
}
