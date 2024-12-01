package GameStates;

import Main.Game;
import UI.MenuButton;
import utils.loadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[2];
    private BufferedImage backgroundImg, backgroundImg2;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        loadBackground();
        loadButtons();
    }

    private void loadBackground() {
        backgroundImg = loadSave.getSpriteAtlas(loadSave.menuBackground);
        backgroundImg2 = loadSave.getSpriteAtlas(loadSave.menuBackground2);
        menuWidth = (int)(backgroundImg.getWidth() * Game.scale * 6);
        menuHeight = (int)(backgroundImg.getHeight() * Game.scale * 6);
        menuX = (Game.gameWidth/2 - menuWidth/2) + (250 * (int)Game.scale);
        menuY = (int)(125*Game.scale);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(menuX + menuWidth/2, (int)(200 * Game.scale), 9, Gamestate.Playing );
        buttons[1] = new MenuButton(menuX + menuWidth/2, (int)(270 * Game.scale), 1, Gamestate.Quit);
    }

    @Override
    public void update() {
        for(MenuButton mb : buttons){
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg2, 0,0, Game.gameWidth, Game.gameHeight, null);

        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        for(MenuButton mb : buttons){
            mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isIn(e, mb)){
                mb.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb : buttons){
            if(isIn(e, mb)){
                if(mb.isMousePressed()){
                    mb.applyGameState();
                    break;
                }
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton mb : buttons){
            mb.resetBools();
        }
    }

    @Override
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

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            Gamestate.state = Gamestate.Playing;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
