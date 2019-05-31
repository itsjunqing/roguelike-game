package game;

import edu.monash.fit2099.engine.*;
import game.actor.GamePlayer;

/**
 * Class representing the GameWorld that extends from the World.
 */
public class GameWorld extends World {

    private static GamePlayer gamePlayer;

    /**
     * Constructor to create a GameWorld.
     *
     * @param display the display of the GameWorld
     */
    public GameWorld(Display display) {
        super(display);
    }

    /**
     * Adds a GamePlayer into the GameWorld.
     *
     * @param gamePlayer a GamePlayer to add
     * @param map        the map where the GamePlayer is to be added
     * @param y          y coordinate of the GamePlayer
     * @param x          x coordinate of the GamePlayer
     */
    public void addPlayer(GamePlayer gamePlayer, GameMap map, int y, int x) {
        super.addPlayer(gamePlayer, map, y, x);
        GameWorld.gamePlayer = gamePlayer;
    }

    /**
     * Run the game.
     *
     * On each iteration the game loop does the following:
     * - displays the player's map
     * - processes the actions of every Actor in the game, regardless of map
     *
     * It stops the game if detects that the player has brought back the unconscious YugoMaxx body to earth.
     * Otherwise, it runs the game continuously until player is knocked out.
     *
     * We could either only process the actors on the current map, which would make
     * time stop on the other maps, or we could process all the actors.  We chose to
     * process all the actors.
     *
     * @throws IllegalStateException if the player doesn't exist
     */
    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();
        boolean resume;
        boolean win = false;
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);
            for (Actor actor : actorLocations) {
                resume = false;
                if (actorLocations.contains(player)) {
                    if (player.hasSkill(GameSkills.SPACEBOSSPOWER) && playersMap.equals(Application.getEarthMap())) {
                        win = true;
                    }

                    if (!win) {
                        resume = true;
                    }
                }

                if (resume) {
                    processActorTurn(actor);
                } else if (win) {
                    display.println(playerWin());
                    actorLocations.remove(player);
                    break;
                } else {
                    display.println(playerLose());
                    break;
                }
            }
        }
        display.println(endGameMessage());
    }

    /**
     * Gets the GamePlayer that is running in the world.
     *
     * @return the gamePlayer
     */
    public static GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    /**
     * Returns a string stating player losing.
     *
     * @return a string
     */
    public String playerLose() {
        return "You lose.";
    }

    /**
     * Returns a string stating player winning.
     *
     * @return a string
     */
    public String playerWin() {
        return "You win.";
    }
}
