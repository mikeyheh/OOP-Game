package Inputs;

import GameStates.Gamestate;
import Main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {
    GamePanel gamePanel;
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(Gamestate.state){
            case Menu:
                gamePanel.getGame().getMenu().mouseClicked(e);
                break;
            case Playing:
                gamePanel.getGame().getPlaying().mouseClicked(e);
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
