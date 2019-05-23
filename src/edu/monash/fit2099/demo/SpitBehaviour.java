package edu.monash.fit2099.demo;

import game.behaviour.ActionFactory;
import edu.monash.fit2099.engine.*;

public class SpitBehaviour extends Action implements ActionFactory{

	private Actor target;

	public SpitBehaviour(Actor subject) {
		this.target = subject;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return actor + " spits a horrible green slime at " + target + "." + System.lineSeparator()+
				"It's gross, but harmless.";
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		Range xs, ys;
		if (here.x() == there.x() || here.y() == there.y()) {
			xs = new Range(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
			ys = new Range(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

			for (int x : xs) {
				for (int y : ys) {
					if(map.at(x, y)	.getGround().blocksThrownObjects())
						return null;
				}
			}
			return this;
		}
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "";
	}

	@Override
	public String hotKey() {
		return "";
	}
}
