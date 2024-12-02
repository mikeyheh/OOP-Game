package Levels;

import Main.Game;
import UI.Camera;
import utils.loadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class levelManager {
    private Game game;
    private BufferedImage[] level;
    private ArrayList<level> levels;
    private int lvlindex = 0;

    public levelManager(Game game){
        this.game = game;
        importOutsideSprite();
        levels = new ArrayList<>();
        buildAllLevels();
    }

    private void buildAllLevels() {
        BufferedImage[] allLevels = loadSave.GetAllLevels();
        for(BufferedImage img : allLevels){
            levels.add(new level(img));
        }
    }

    private void importOutsideSprite() {
        BufferedImage img = loadSave.getSpriteAtlas(loadSave.levelAtlas);
        BufferedImage img2 = loadSave.getSpriteAtlas(loadSave.levelAtlas2);

        // 48 levelAtlas, 60, levelAtlas2
        level = new BufferedImage[108];
        int currIndex = 0;

        // Atlas1
        for(int j = 0; j < 4; j++){
            for (int i = 0; i < 12; i++){
                level[currIndex] = img.getSubimage(i*32, j*32, 32, 32);
                currIndex++;
            }
        }

        // Atlas2
        for(int j = 0; j < 6; j++){
            for (int i = 0; i < 10; i++){
                level[currIndex] = img2.getSubimage(i*32, j*32, 32, 32);
                currIndex++;
            }
        }

    }

    public void draw(Graphics g, Camera camera) {
        int yOffset = -camera.getCameraY(); // Offset the level rendering based on the camera position

        for (level currentLevel : levels) {
            for (int j = 0; j < Game.tileHeight; j++) {
                for (int i = 0; i < Game.tileWidth; i++) {
                    int tileSet = currentLevel.getSpriteIndex(i, j);
                    g.drawImage(level[tileSet],
                            Game.tileSize * i,              // X position
                            Game.tileSize * j + yOffset,    // Y position with camera offset
                            Game.tileSize,
                            Game.tileSize,
                            null);
                }
            }
        }
    }


    public void update(){

    }

    public ArrayList<Levels.level> getLevels(){
        return levels;
    }

    public level getCurrentLevel(){
        return levels.get(lvlindex);
    }

    public int numberofLevels(){
        return levels.size();
    }

}
