package Levels;

import GameStates.Gamestate;
import Main.Game;
import entities.EnemyManager;
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

        // Atlas2
        for(int j = 0; j < 6; j++){
            for (int i = 0; i < 10; i++){
                level[currIndex] = img2.getSubimage(i*32, j*32, 32, 32);
                currIndex++;
            }
        }

        // Atlas1
        for(int j = 0; j < 4; j++){
            for (int i = 0; i < 12; i++){
                level[currIndex] = img.getSubimage(i*32, j*32, 32, 32);
                currIndex++;
            }
        }

    }

    public void draw(Graphics g){
        for(int j = 0; j < Game.tileHeight; j++) {
            for (int i = 0; i < levels.get(lvlindex).getLevelData()[0].length; i++) {
                int index = levels.get(lvlindex).getSpriteIndex(i, j);
                g.drawImage(level[index], Game.tileSize * i, Game.tileSize * j, Game.tileSize, Game.tileSize, null);
            }
        }
    }
    public void update(){

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
            Gamestate.state = Gamestate.Menu;
        }
        level newLevel = levels.get(lvlindex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
    }

}
