package Levels;

import Objects.Archer;
import Objects.Spikes;
import entities.Slime;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utils.helpMethods;
import static utils.helpMethods.GetLevelData;
import static utils.helpMethods.GetSlimes;

public class level {
    private BufferedImage img;
    private int[][] lvl;
    private ArrayList< Slime> slimes;
    private ArrayList<Spikes> spikes;
    private ArrayList<Archer> archers;
    public level(BufferedImage img){
        this.img = img;
        createLevelData();
        createEnemies();
        createSpikes();
        createArchers();
    }

    private void createArchers() {
        archers = helpMethods.GetArchers(img);
    }

    private void createSpikes() {
        spikes = helpMethods.GetSpikes(img);
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

    public ArrayList<Spikes> getSpikes() {
        return spikes;
    }
    public ArrayList<Archer> getArchers() {
        return archers;
    }
}
