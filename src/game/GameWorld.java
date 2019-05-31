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
                    if (player.hasSkill(GameSkills.SPACEBOSSPOWER) && playersMap == Application.getEarthMap()) {
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
     * Returns a player losing string.
     * @return a string
     */
    public String playerLose() {
        return "You lose.";
    }

    /**
     * Adds a GamePlayer into the GameWorld.
     * @param gamePlayer a GamePlayer to add
     * @param map the map where the GamePlayer is to be added
     * @param y y coordinate of the GamePlayer
     * @param x x coordinate of the GamePlayer
     */
    public void addPlayer(GamePlayer gamePlayer, GameMap map, int y, int x) {
        super.addPlayer(gamePlayer, map, y, x);
        GameWorld.gamePlayer = gamePlayer;
    }

    /**
     * Gets the GamePlayer
     * @return
     */
    public static GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public String playerWin() {
        return "You win.";
    }
}
