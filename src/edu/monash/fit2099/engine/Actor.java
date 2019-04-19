package edu.monash.fit2099.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Actor implements Skilled, Comparable<Actor>, Printable {

	private Random rand = new Random();
	private Integer priority;
	private Skills skills = new Skills();
	protected String name;
	protected char displayChar;
	protected List<Item> inventory = new ArrayList<Item>();
	protected int maxHitPoints;
	protected int hitPoints;

	public Actor(String name, char displayChar, int priority, int hitPoints) {
		this.name = name;
		this.displayChar = displayChar;
		this.priority = priority;
		this.maxHitPoints = hitPoints;
		this.hitPoints = hitPoints;
	}

	/**
	 * Compare the priorities of the current Actor and another Actor.
	 *
	 * @param a the other Actor
	 * @return the result of comparing the priorities (as Integers)
	 */
	@Override
	public int compareTo(Actor a) {
		return this.priority.compareTo(a.priority);
	}

	@Override
	public char getDisplayChar() {
		return displayChar;
	}

	@Override
	public String toString() {
		return name;
	}

	public void addItemToInventory(Item item) {
		inventory.add(item);
	}

	public void removeItemFromInventory(Item item) {
		inventory.remove(item);
	}

	/**
	 * Returns an unmodifiable shallow copy of the player's inventory. 
         *
         * This is one of the rare times a copy is better than an unmodifiable wrapper.
	 * There's a good chance that whatever is iterating the list might also want to
	 * change the list. Do that with removeItemFromInventory and addItemToInventory. Not through here.
	 * 
	 * @return A copy of the inventory
	 */
	public List<Item> getInventory() {
		return Collections.unmodifiableList(new ArrayList<Item>(inventory));
	}

	/**
     * Select and return an action to perform on the current turn.
     *
     * By default, non-player Actors act randomly.  To change that, override this method.
     *
     * @param actions collection of possible Actions for this Actor
	 * @param map     the map containing the Actor
	 * @param display the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	public Action playTurn(Actions actions, GameMap map, Display display) {
		return actions.get(rand.nextInt(actions.size()));
	}

    /**
     * Returns a collection of the Actions containing an AttackAction that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return A collection of Actions.
	 */
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		return new Actions(new AttackAction(otherActor, this));
	}

	/**
	 * Returns true if the current Actor has positive hit points.
	 *
	 * Actors on zero hit points are deemed to be unconscious.
	 *
	 * @return true if and only if hitPoints is positive.
	 */
	public boolean isConscious() {
		return hitPoints > 0;
	}

	/**
	 * Add points to the current Actor's hitpoint total.
	 *
	 * This cannot take the hitpoints over the Actor's maximum. If there is an
	 * overflow, hitpoints are silently capped at the maximum.
	 *
	 * Does not check for consciousness: unconscious Actors can still be healed.
	 *
	 * @param points number of hitpoints to add.
	 */
	public void heal(int points) {
		hitPoints += points;
		hitPoints = Math.min(hitPoints, maxHitPoints);
	}

	/**
	 * Do some damage to the current Actor.
	 *
	 * If the Actor's hitpoints go down to zero, it will be knocked out.
	 *
	 * @param points number of hitpoints to deduct.
	 */
	public void hurt(int points) {
		hitPoints -= points;
	}

    /**
     * If the current Actor is carrying weapons, returns the first one in the inventory.  Otherwise, returns the
     * Actor's natural fighting equipment e.g. fists.
     *
	 * @return the Actor's weapon or natural
	 */
	public Weapon getWeapon() {
		for (Item item : inventory) {
			if (item.asWeapon() != null)
				return item.asWeapon();
		}
		return getIntrinsicWeapon();
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
     * By default, the Actor 'punches' for 5 damage.  Override this method to create an Actor with
     * more interesting descriptions and/or damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(5, "punches");
	}

	/**
	 * Returns true if and only if the current Actor has the required skill.
	 *
	 * @param skill the skill required
	 * @return true if and only if the current Actor has the required skill
	 */
	public boolean hasSkill(Enum skill) {

		for (Item item : inventory) {
			if (item.hasSkill(skill)) {
				return true;
			}
		}
		return skills.hasSkill(skill);
	}

	public void addSkill(Enum skill) {
		skills.addSkill(skill);
	}

	public void removeSkill(Enum skill) {
		skills.removeSkill(skill);
	}
}
