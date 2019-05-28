package game;

import edu.monash.fit2099.engine.*;
import game.actor.GamePlayer;

public class GameWorld extends World {

    private static GamePlayer gamePlayer;

    // use this to access the map instead of accessing via the Application
    public GameWorld(Display display) {
        super(display);
    }

    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();
        boolean cont;
        boolean win = false;
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);
            for (Actor actor : actorLocations) {
                cont = false;
                if (actorLocations.contains(player)) {
//                    for (Item item : actorLocations.locationOf(player).getItems()){
//                    Checks if Player has dropped the YugoMaxx 's dead body
//                        if (item.hasSkill(GameSkills.SPACEBOSSPOWER) && playersMap.equals(Application.getEarthMap())){
//                            win = true;
//                            break;
//                        }
                    if (player.hasSkill(GameSkills.SPACEBOSSPOWER) && playersMap == Application.getEarthMap()) {
                        win = true;
                        break;
                    }

                    if (!win){
                        cont = true;
                    }
                }

                if (cont) {
                    processActorTurn(actor);
                } else if (win){
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

    public String playerLose() {
        return "You lose.";
    }

    public void addPlayer(GamePlayer gamePlayer, GameMap map, int y, int x) {
        super.addPlayer(gamePlayer, map, y, x);
        GameWorld.gamePlayer = gamePlayer;
    }

    public static GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public String playerWin(){
        return "You win.";
    }
}
