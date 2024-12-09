package Levels;

import Main.Game;
import utils.loadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class levelManager {
    private Game game;
    private BufferedImage[] level;
    private ArrayList<level> levels;
    private int lvlindex = 0;

    private BufferedImage lvlImg;
    float bgOpacity = 0.7f;
    private final int[] lvlIndex_plains = {0,1};
    private final int[] lvlIndex_ice = {2,3};
    private final int[] lvlIndex_lava = {};
    private final int[] lvlIndex_final = {4};

    private final Map<Integer, BufferedImage> levelBackgrounds;

    // Map levels to their backgrounds
    BufferedImage plainsBackground = loadSave.getSpriteAtlas(loadSave.gameBackgroundPlains);
    BufferedImage iceBackground = loadSave.getSpriteAtlas(loadSave.gameBackgroundIce);
    BufferedImage lavaBackground = loadSave.getSpriteAtlas(loadSave.gameBackgroundLava);
    BufferedImage finalBackground = loadSave.getSpriteAtlas(loadSave.gameBackgroundFinal);

    public levelManager(Game game){
        this.game = game;
        importOutsideSprite();
        this.levels = new ArrayList<>();
        this.levelBackgrounds = preloadBackgroundImages();
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
        int pixelSize = 0;

        // Atlas1
        pixelSize = 32;
        for(int j = 0; j < 4; j++){
            for (int i = 0; i < 12; i++){
                level[currIndex] = img.getSubimage(i*pixelSize, j*pixelSize, pixelSize, pixelSize);
                currIndex++;
            }
        }

        // Atlas2
        pixelSize = 32;
        for(int j = 0; j < 6; j++){
            for (int i = 0; i < 10; i++){
                level[currIndex] = img2.getSubimage(i*pixelSize, j*pixelSize, pixelSize, pixelSize);
                currIndex++;
            }
        }
    }

    private Map<Integer, BufferedImage> preloadBackgroundImages() {
        Map<Integer, BufferedImage> backgrounds = new HashMap<>();

        for (int idx : lvlIndex_plains) {
            backgrounds.put(idx, plainsBackground);
        }
        for (int idx : lvlIndex_ice) {
            backgrounds.put(idx, iceBackground);
        }
        for (int idx : lvlIndex_lava) {
            backgrounds.put(idx, lavaBackground);
        }
        for (int idx : lvlIndex_final) {
            backgrounds.put(idx, finalBackground);
        }

        return backgrounds;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Get the background image for the current level
        BufferedImage lvlImg = levelBackgrounds.getOrDefault(lvlindex, null);
        if (lvlImg != null) {
            // Set opacity for the background
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, bgOpacity);
            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(alphaComposite);

            // Draw the background image
            g2d.drawImage(lvlImg, 0, 0, Game.gameWidth, Game.gameHeight, null);

            // Restore the original composite
            g2d.setComposite(originalComposite);
        }

        // Draw level tiles
        for (int j = 0; j < Game.tileHeight; j++) {
            for (int i = 0; i < levels.get(lvlindex).getLevelData()[0].length; i++) {
                int index = levels.get(lvlindex).getSpriteIndex(i, j);
                g2d.drawImage(level[index], Game.tileSize * i, Game.tileSize * j, Game.tileSize, Game.tileSize, null);
            }
        }
    }



    public void update(){
    }

    public int getCurrentLevelIndex() {
        return lvlindex;
    }


    public int[][] getNextLevelData(){
        return levels.get(lvlindex + 1).getLevelData();
    }

    public level getCurrentLevel(){
        return levels.get(lvlindex);
    }

    public int numberofLevels(){
        return levels.size();
    }
    public void loadNextLevel(){
        lvlindex++;
        if(lvlindex >= levels.size()){
            lvlindex = 0;
        }
        level newLevel = levels.get(lvlindex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
    }

    public void loadSelectedLevel(int level){
        lvlindex = level;
        if(lvlindex >= levels.size()){
            lvlindex = 0;
        }
        level newLevel = levels.get(lvlindex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
    }

    public void loadPrevLevel() {
        lvlindex--;
        if(lvlindex < 0){
            lvlindex = 0;
        }
        level newLevel = levels.get(lvlindex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
    }
}
