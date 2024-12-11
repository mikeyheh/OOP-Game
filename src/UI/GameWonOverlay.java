package UI;

import GameStates.Gamestate;
import GameStates.Menu;
import GameStates.Playing;
import Main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameWonOverlay {
    private Playing playing;
    private MenuButton[] buttons = new MenuButton[2];

    public GameWonOverlay(Playing playing) {
        this.playing = playing;
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.gameWidth / 2 - (int) (120 * Game.scale), (int) (200 * Game.scale), 10, Gamestate.Menu);
        buttons[1] = new MenuButton(Game.gameWidth / 2 + (int) (120 * Game.scale), (int) (200 * Game.scale), 5, Gamestate.Playing);
    }

    public void update() {
        for (MenuButton mb : buttons) {
            mb.update();
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.gameWidth, Game.gameHeight);

        g.setColor(Color.white);
        g.drawString("You Win!", Game.gameWidth / 2, 150);

        for (MenuButton mb : buttons) {
            mb.draw(g);
        }
    }

    public void keyPressed(KeyEvent e) {
        // Handle key events if needed
    }

    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed()) {
                    mb.applyGameState();
                    if (mb.gamestate() == Gamestate.Playing) {
                        playing.resetAll();
                    } else {
                        playing.resetAll();
                        playing.stopPlayingMusic();
                        Menu.playMenuMusic();
                    }
                    break;
                }
            }
        }
        resetButtons();
    }

    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons) {
            mb.setMouseOver(false);
        }

        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    private void resetButtons() {
        for (MenuButton mb : buttons) {
            mb.resetBools();
        }
    }

    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(), e.getY());
    }
}