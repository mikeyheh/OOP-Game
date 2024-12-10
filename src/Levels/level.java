package Levels;

import Objects.*;
import entities.Slime;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utils.helpMethods;
import static utils.helpMethods.GetLevelData;
import static utils.helpMethods.GetSlimes;

public class level {
    private static BufferedImage img;
    private int[][] lvl;
    private ArrayList< Slime> slimes;
    private ArrayList<Spikes> spikes;
    private ArrayList<Archer> archers;
    private ArrayList<Checkpoint> checkpoints;
    private ArrayList<Key> keys;
    private ArrayList<Chest> chests;

    public level(BufferedImage img){
        this.img = img;
        createLevelData();
        createEnemies();
        createSpikes();
        createArchers();
        createCheckpoints();
        createKeys();
        createChests();
    }

    private void createChests() {chests = helpMethods.GetChests(img);}

    private void createKeys() {keys = helpMethods.GetKeys(img); }

    private void createCheckpoints(){ checkpoints = helpMethods.GetCheckpoints(img); }

    private void createArchers() {
        archers = helpMethods.GetArchers(img);
    }

    private void createSpikes() {
        spikes = helpMethods.GetSpikes(img);
    }

    private void createEnemies() {
        slimes = GetSlimes(img);
    }

    public void createLevelData() {
        lvl = GetLevelData(img);
    }

    public BufferedImage getImg() {
        return img;
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
    public ArrayList<Checkpoint> getCheckpoints() { return checkpoints; }
    public ArrayList<Key> getKeys() { return keys; }
    public ArrayList<Chest> getChests(){ return chests; }
}
