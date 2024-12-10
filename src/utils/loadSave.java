package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class loadSave {
    //menus & ui
    public static final String menuButtons = "ui/menu_buttons.png";
    public static final String menuBackground = "ui/menu_background.png";
    public static final String menuBackground2 = "backgroundImgs/menu_backgroundImg.png";
    public static final String menuLogo = "ui/logo.png";

    //game backgrounds
    public static final String gameBackgroundPlains = "backgroundImgs/gameBackground_plains.jpg";
    public static final String gameBackgroundLava = "backgroundImgs/gameBackground_lava.jpg";
    public static final String gameBackgroundIce = "backgroundImgs/gameBackground_ice.png";
    public static final String gameBackgroundFinal = "backgroundImgs/gameBackground_final.jpg";

    //player anims
    public static final String playerAtlas ="sprites_objects/final_anims.png";

    // tile sets
    public static final String levelAtlas ="tileSets/plains.png";
    public static final String levelAtlas2 = "tileSets/ice.png";
    public static final String levelAtlas3 = "tileSets/lava.png";
    public static final int tilesSum = 154;

    //object anims
    public static final String Slimes = "sprites_objects/slime-spritesheet.png";
    public static final String Trap = "sprites_objects/trap_atlas.png";
    public static final String Archer = "sprites_objects/archer.png";
    public static final String Arrow = "sprites_objects/projectile.png";
    public static final String Checkpoint = "sprites_objects/checkpoint.png";
    public static final String Key = "sprites_objects/key.png";
    public static final String Chest = "sprites_objects/chest.png";


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
        String[] levelFiles = {"1.png", "2.png", "3.png", "4.png", "5.png"}; // Update with actual file names
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
