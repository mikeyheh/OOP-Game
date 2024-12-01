package UI;

import GameStates.Gamestate;
import GameStates.Playing;
import Main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;

public class GameOverOverlay {
    private Playing playing;

    public GameOverOverlay(Playing playing){
        this.playing = playing;
    }
    public void draw(Graphics g){
        g.setColor(new Color (0,0,0,200));
        g.fillRect(0,0, Game.gameWidth, Game.gameHeight);

        g.setColor(Color.white);
        g.drawString("Game Over", Game.gameWidth /2, 150);
        g.drawString("Press Esc", Game.gameWidth/2,300);

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
}
