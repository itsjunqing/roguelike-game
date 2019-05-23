package edu.monash.fit2099.demo;

import java.util.*;

import game.behaviour.ActionFactory;
import edu.monash.fit2099.engine.*;


public class Bug extends Actor {

	private Random rand = new Random();
	public List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	public Bug() {
		super("Feature", 'x', 2, 10);
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return actions.get(rand.nextInt(actions.size()));
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.add(new KickAction(otherActor, this));
		return list;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(1, "stings");
	}
}
