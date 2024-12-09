package utils;

import Main.Game;
import Objects.Archer;
import Objects.Checkpoint;
import Objects.Projectile;
import Objects.Spikes;
import entities.Slime;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utils.constant.ObjectConstants.*;

public class helpMethods {
    public static boolean canMove(float x, float y, float width, float height, int[][] lvl){
        if(!isSolid(x,y,lvl)){
            if(!isSolid(x+width, y+height, lvl)){
                if(!isSolid(x+width, y, lvl)){
                    if(!isSolid(x,y+height,lvl)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean isSolid(float x, float y, int[][] lvl){
        if(x < 0 || x >= Game.gameWidth){
            return true;
        }
        if( y < 0 || y >= Game.gameHeight){
            return false;
        }

        int xIndex = (int)x/Game.tileSize;
        int yIndex = (int)y/Game.tileSize;

        int val = lvl[yIndex][xIndex];

        if(val != 11){
            return true;
        }
        return false;
    }

    public static float getEntityPosToWall(Rectangle2D.Float hitbox, float xSpeed){
        int currTile = (int)(hitbox.x/Game.tileSize);
        if(xSpeed > 0){
            int xTile = currTile * Game.tileSize;
            int xOffset = (int)(Game.tileSize - hitbox.width);
            return xTile + xOffset - 1;
        }else{
            return currTile * Game.tileSize;
        }
    }
    public static float getEntityYpos(Rectangle2D.Float hitbox, float airSpeed){
        int currTile = (int)(hitbox.y/Game.tileSize);
        if(airSpeed > 0 ){
            int yTile = currTile * Game.tileSize;
            int yOffset = (int)(Game.tileSize - hitbox.height);
            return yTile + yOffset - 1;
        }else{
            return currTile * Game.tileSize;
        }
    }
    public static boolean grounded(Rectangle2D.Float hitbox, int[][]lvl){
        if(!isSolid(hitbox.x, hitbox.y+ hitbox.height+1,lvl)){
            if(!isSolid(hitbox.x+ hitbox.width, hitbox.y+ hitbox.height+1,lvl)){
                return false;
            }
        }
        return true;
    }

    public static boolean isFloor(Rectangle2D.Float hitbox, float xspeed,int[][]lvl ){
        if (xspeed > 00) {
            return isSolid(hitbox.x +  xspeed + hitbox.width , hitbox.y + hitbox.height + 1 , lvl);
        }else{
            return isSolid(hitbox.x + xspeed, hitbox.y + hitbox.height + 1 , lvl);
        }
    }
    public static int[][] GetLevelData(BufferedImage img){

        int[][] lvl = new int[img.getHeight()][img.getWidth()];

        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i <img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int val = color.getRed();
                if(val >= loadSave.tilesSum){
                    val = 11;
                }
                lvl[j][i] = val;
            }
        }
        return lvl;
    }

    public static boolean projectileHitGround(Projectile p, int[][] lvlData){
        return isSolid(p.getHitbox().x + p.getHitbox().width/2,p.getHitbox().y + p.getHitbox().height/2,lvlData);
    }
    public static ArrayList<Slime> GetSlimes(BufferedImage img){
        ArrayList<Slime> list = new ArrayList<>();

        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i <img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int val = color.getBlue();
                if(val ==  constant.enemyConstants.Slime) {
                    list.add(new Slime(i * Game.tileSize, j* Game.tileSize));
                }
            }
        }
        return list;
    }
    public static ArrayList<Spikes> GetSpikes(BufferedImage img){
        ArrayList<Spikes> list = new ArrayList<>();

        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i <img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int val = color.getBlue();
                if(val == Spike) {
                    list.add(new Spikes(i * Game.tileSize, j* Game.tileSize,Spike));
                }
            }
        }
        return list;
    }
    public static ArrayList<Archer> GetArchers(BufferedImage img){
        ArrayList<Archer> list = new ArrayList<>();

        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i <img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int val = color.getBlue();
                if(val == archer_left || val == archer_right) {
                    list.add(new Archer(i * Game.tileSize, j* Game.tileSize,val));
                }
            }
        }
        return list;
    }

    public static ArrayList<Checkpoint> GetCheckpoints(BufferedImage img) {
        ArrayList<Checkpoint> list = new ArrayList<>();

        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i <img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int val = color.getBlue();
                if(val == Checkpoint) {
                    list.add(new Checkpoint(i * Game.tileSize, j* Game.tileSize, Checkpoint));
                }
            }
        }
        return list;
    }
}
