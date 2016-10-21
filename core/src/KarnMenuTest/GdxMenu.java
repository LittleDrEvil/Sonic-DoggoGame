package KarnMenuTest;
import com.badlogic.gdx.Game;
import KarnMenuTest.Screens.ScrMenu;
import KarnMenuTest.Screens.ScrPlay;
import KarnMenuTest.Screens.ScrGameover;


/**
 * Created by luke on 2016-04-05.
 * This is the master "game" class that contains all the screens.
 */

public class GdxMenu extends Game {
    ScrMenu scrMenu;
    ScrPlay scrPlay;
    ScrGameover scrGameover;
    
    // the below enumeration clearly defines the list of permissible screens.
    public enum GameState {
        MENU, PLAY, OVER
    }
    public GameState gameState;

    public GameState currentState;
    // based on the current GameState, the screen is set.
    public void updateState() {
        if (currentState == gameState.MENU) {
            setScreen(scrMenu);
        } else if (currentState == gameState.PLAY) {
            setScreen(scrPlay);
        } else if (currentState == gameState.OVER) {
            setScreen(scrGameover);
        }
    }

    @Override
    public void create() {
        // notice that "this" is passed to each screen. Each screen now has access to methods within the "game" master program
        scrMenu = new ScrMenu(this);
        scrPlay = new ScrPlay(this);
        scrGameover = new ScrGameover(this);
        currentState = GameState.MENU;
        updateState();
    }


    @Override
    public void dispose() {
        super.dispose();
    }
}