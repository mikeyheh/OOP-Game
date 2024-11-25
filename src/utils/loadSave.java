package utils;

import Main.Game;
import entities.Slime;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import static utils.constant.enemyConstants.Slime;

public class loadSave {
    public static final String playerAtlas ="final_anims.png";
    public static final String levelAtlas ="outside_sprites.png";
    //public static final String mapAtlas ="level_one_data.png";
    public static final String mapAtlas ="level_one_data_long.png";
    public static final String Slimes = "slime-spritesheet.png";
    public static BufferedImage getSpriteAtlas(String file){
        BufferedImage img = null;
        InputStream is = loadSave.class.getResourceAsStream("/" + file);
        try {
              img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            try{
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return img;
    }

    public static ArrayList<Slime> GetSlimes(){
        BufferedImage img = getSpriteAtlas(mapAtlas);
        ArrayList<Slime> list = new ArrayList<>();

        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i <img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int val = color.getGreen();
                if(val ==  Slime) {
                    list.add(new Slime(i * Game.tileSize, j* Game.tileSize));
                }
            }
        }
        return list;
    }
    public static int[][] getLevelData(){
        BufferedImage img = getSpriteAtlas(mapAtlas);
        int[][] lvl = new int[img.getHeight()][img.getWidth()];

        for(int j = 0; j < img.getHeight(); j++){
            for(int i = 0; i <img.getWidth();i++){
                Color color = new Color(img.getRGB(i,j));
                int val = color.getRed();
                if(val >= 48){
                    val = 0;
                }
                lvl[j][i] = val;
            }
        }
        return lvl;
    }
}
