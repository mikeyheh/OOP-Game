package Inputs;

import GameStates.Gamestate;
import Main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utils.constant.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel panel;

    public KeyboardInputs(GamePanel panel){
        this.panel= panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state){
            case Menu:
                panel.getGame().getMenu().keyPressed(e);
                break;
            case Playing:
                panel.getGame().getPlaying().keyPressed(e);
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state){
            case Menu:
                panel.getGame().getMenu().keyReleased(e);
                break;
            case Playing:
                panel.getGame().getPlaying().keyReleased(e);
            default:
                break;
        }
    }
}
