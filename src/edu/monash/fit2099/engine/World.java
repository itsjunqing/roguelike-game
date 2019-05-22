package edu.monash.fit2099.engine;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing the game world, including the locations of all Actors, the player, and the playing grid.
 */
public class World {
	protected Display display;
	protected List<GameMap> maps = new ArrayList<GameMap>();
	protected ActorLocations actorLocations = new ActorLocations();
	protected Actor player; // We only draw the particular map this actor is on.


	/**
	 *
	 * @param display
	 */
	public World(Display display) {
		Objects.requireNonNull(display);
		this.display = display;
	}

	/**
	 * Add a map to the world.
	 *
	 * @param map the GameMap to add
	 */
	public void addMap(GameMap map) {
		maps.add(map);
		map.setActorLocations(actorLocations);
	}

	/**
	 * Add the player to a map.
	 *
	 * The player is special: we only display the map that the player is on.
	 * 
	 * @param player the player to add
	 * @param map the map where the player is to be added
	 * @param y y coordinate of the player
	 * @param x x coordinate of the player
	 */
	public void addPlayer(Actor player, GameMap map, int y, int x) {
		this.player = player;
		map.addActor(player, x, y);
	}

	/**
	 * Run the game.
	 *
	 * On each iteration the gameloop does the following:
	 *  - displays the player's map
	 *  - processes the actions of every Actor in the game, regardless of map
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors.  We chose to
	 * process all the actors.
	 *
	 * @throws IllegalStateException if the player doesn't exist
	 */
	public void run() {
		if(player == null)
			throw new IllegalStateException();
		
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			for (Actor actor : actorLocations) {
				processActorTurn(actor);
			}
		}
		display.println(endGameMessage());
	}

	/**
	 * Gives an Actor its turn.
	 *
	 * The Actions an Actor can take include:
	 * <ul>
	 *  <li> those conferred by items it is carrying </li>
	 *  <li> movement actions for the current location and terrain </li>
	 *  <li> actions that can be done to Actors in adjacent squares </li>
	 *  <li> actions that can be done using items in the current location </li>
	 *  <li> skipping a turn</li>
	 * </ul>
	 *
	 * @param actor the Actor whose turn it is.
	 */
	protected void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		Actions actions = new Actions();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (actorLocations.isAnActorAt(destination)) {
				Actor adjacentActor = actorLocations.actorAt(destination);
				actions.add(adjacentActor.getAllowableActions(actor, exit.getName(), map));
			} else {
				Ground adjacentGround = map.groundAt(destination);
				actions.add(adjacentGround.allowableActions(actor, destination, exit.getName()));
				actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
			}
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
		}
		actions.add(new SkipTurnAction());
		
		Action action = actor.playTurn(actions, map, display);
		String result = action.execute(actor, map);
		display.println(result);
	}

	/**
	 * Returns true if the game is still running.
	 *
	 * The game is considered to still be running if the player is still around.
	 *
	 * @return true if the player is still on the map.
	 */
	protected boolean stillRunning() {
		return actorLocations.contains(player);
	}

	/**
	 * Return a string that can be displayed when the game ends.
	 *
	 * @return the string "Game Over"
	 */
	protected String endGameMessage() {
		// Not a bad idea, but not in keeping with the current theme.
		// return "It's game over, man.";
		return "Game Over";
	}
}
