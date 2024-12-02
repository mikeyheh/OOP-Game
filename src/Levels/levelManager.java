package Levels;

import Main.Game;
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

    public void draw(Graphics g) {
        int levelHeight = Game.tileHeight * Game.tileDefaultSize; // Total height of a single level

        for (int lvlIndex = 0; lvlIndex < levels.size(); lvlIndex++) { // Draw from the bottom level (index 0)
            level currentLevel = levels.get(lvlIndex); // Get the current level

            int yOffset = (levels.size() - 1 - lvlIndex) * levelHeight; // Calculate offset for stacking from the bottom

            for (int j = 0; j < Game.tileHeight; j++) { // Loop through rows
                for (int i = 0; i < Game.tileWidth; i++) { // Loop through columns
                    int tileSet = currentLevel.getSpriteIndex(i, j); // Get the sprite index for the tile
                    g.drawImage(level[tileSet],
                            Game.tileDefaultSize * i,              // X position
                            Game.tileDefaultSize * j + yOffset,    // Y position with calculated offset
                            Game.tileDefaultSize,                 // Tile width
                            Game.tileDefaultSize,                 // Tile height
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
