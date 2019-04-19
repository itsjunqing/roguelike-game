package edu.monash.fit2099.engine;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	private Actor actor;
	private Actor subject;
	private Random rand = new Random();

	public AttackAction(Actor actor, Actor subject) {
		this.actor = actor;
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
			map.removeActor(subject);
			result += System.lineSeparator() + subject + " is knocked out.";
		}

		return result;

	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + subject;
	}

	@Override
	public String hotKey() {
		return "";
	}
}
