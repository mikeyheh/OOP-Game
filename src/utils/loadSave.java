package utils;

import Main.Game;
import entities.Slime;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import static utils.constant.enemyConstants.Slime;

public class loadSave {
    public static final String playerAtlas ="final_anims.png";
    public static final String levelAtlas ="outside_sprites.png";
    //public static final String mapAtlas ="level_one_data.png";
    public static final String mapAtlas ="level_one_data_long.png";
    public static final String Slimes = "slime-spritesheet.png";
    public static final String Trap = "trap_atlas.png";
    public static final String Archer = "archer.png";
    public static final String Arrow = "projectile.png";
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

    public static BufferedImage[] GetAllLevels() {
        // List all resource files manually (e.g., 1.png, 2.png, etc.)
        String[] levelFiles = {"1.png"}; // Update with actual file names
        BufferedImage[] imgs = new BufferedImage[levelFiles.length];

        for (int i = 0; i < levelFiles.length; i++) {
            try (InputStream is = loadSave.class.getResourceAsStream("/lvls/" + levelFiles[i])) {
                if (is == null) {
                    throw new IllegalStateException("File not found in resources: /lvls/" + levelFiles[i]);
                }
                imgs[i] = ImageIO.read(is);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load image: " + levelFiles[i], e);
            }
        }

        return imgs;
    }


}
