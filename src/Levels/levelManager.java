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
        level = new BufferedImage[48];
        for(int j = 0; j < 4; j++){
            for (int i = 0; i < 12; i++){
                int index = j*12 + i;
                level[index] = img.getSubimage(i*32, j*32, 32, 32);
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
