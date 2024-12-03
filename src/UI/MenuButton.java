package UI;

import GameStates.Gamestate;
import utils.loadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.constant.UI.Buttons.*;

public class MenuButton {
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = buttonWidth / 2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    public MenuButton (int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, buttonWidth, buttonHeight);
    }

    private void loadImgs() {
        imgs = new BufferedImage[5];
        BufferedImage temp = loadSave.getSpriteAtlas(loadSave.menuButtons);

        for(int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * buttonDefaultWidth, rowIndex * buttonDefaultHeight, buttonDefaultWidth, buttonDefaultHeight);
        }
    }

    public void draw(Graphics g){
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, buttonWidth, buttonHeight, null);
    }

    public void update(){
        index = 0;
        if(mouseOver) {
            index = 1;
        }
        if(mousePressed) {
            index = 2;
        }
    }

    public boolean isMouseOver(){
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed(){
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }

    public void applyGameState() {
        Gamestate.state = state;
    }

    public Gamestate gamestate(){
        return this.state;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds(){
        return bounds;
    }
}
