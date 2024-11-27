package Levels;

import entities.Slime;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static utils.helpMethods.*;

public class level {
    private BufferedImage img;
    private int[][] lvl;
    private ArrayList< Slime> slimes;
    public level(BufferedImage img){
        this.img = img;
        createLevelData();
        createEnemies();
    }

    private void createEnemies() {
        slimes = GetSlimes(img);
    }

    private void createLevelData() {
        lvl = GetLevelData(img);
    }

    public int getSpriteIndex(int x, int y){
        return lvl[y][x];
    }

    public int[][] getLevelData(){
        return lvl;
    }

    public ArrayList<Slime> getSlimes(){
        return slimes;
    }
}
