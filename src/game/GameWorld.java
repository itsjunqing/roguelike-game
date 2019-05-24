package game;

import edu.monash.fit2099.engine.*;

public class GameWorld extends World {


    // use this to access the map instead of accessing via the Application
    public GameWorld(Display display) {
        super(display);
    }

    @Override
    public void run() {
        if(player == null)
            throw new IllegalStateException();
        boolean cont;
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);
            for (Actor actor : actorLocations) {
                cont = false;
                for (Actor actor2 : actorLocations){
                    if (actor2 instanceof Player){
                        cont = true;
                    }
                }
                if (cont){
                    processActorTurn(actor);
                }
                else{
                    display.println(playerLose());
                    break;
                }
            }
        }
        display.println(endGameMessage());
    }

    public String playerLose(){
        return "You lose.";
    }
}
