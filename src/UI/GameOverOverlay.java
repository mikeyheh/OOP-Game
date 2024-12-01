package UI;

import GameStates.Gamestate;
import GameStates.Playing;
import Main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.security.Key;

public class GameOverOverlay {
    private Playing playing;
    private MenuButton[] buttons = new MenuButton[2];
    private int menuWidth, menuHeight;


    public GameOverOverlay(Playing playing){

        this.playing = playing;
        loadButtons();

    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.gameWidth/2 - (int)(120 * Game.scale), (int)(200 * Game.scale), 10, Gamestate.Menu);
        buttons[1] = new MenuButton(Game.gameWidth/2 + (int)(120 * Game.scale), (int)(200 * Game.scale), 8, Gamestate.Playing);
    }

    public void update() {
        for(MenuButton mb : buttons){
            mb.update();
        }
    }

    public void draw(Graphics g){
        g.setColor(new Color (0,0,0,200));
        g.fillRect(0,0, Game.gameWidth, Game.gameHeight);

        g.setColor(Color.white);
        g.drawString("Game Over", Game.gameWidth /2, 150);
//        g.drawString("Press Esc", Game.gameWidth/2,300);

        for(MenuButton mb : buttons){
            mb.draw(g);
        }
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            playing.resetAll();
            Gamestate.state = Gamestate.Menu;
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            playing.returnCheckpoint();
            Gamestate.state = Gamestate.Playing;
        }
    }

    public void mousePressed(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isIn(e, mb)){
                mb.setMousePressed(true);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isIn(e, mb)){
                if(mb.isMousePressed()){
                    mb.applyGameState();
                    if(mb.gamestate() == Gamestate.Playing){
                        playing.returnCheckpoint();
                    }else{
                        playing.resetAll();
                    }
                    break;
                }
            }
        }
        resetButtons();
    }

    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb : buttons){
            mb.setMouseOver(false);
        }

        for(MenuButton mb : buttons){
            if(isIn(e, mb)){
                mb.setMouseOver(true);
                break;
            }
        }
    }

    private void resetButtons() {
        for(MenuButton mb : buttons){
            mb.resetBools();
        }
    }


    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }


}
