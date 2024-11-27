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

    public static BufferedImage[] GetAllLevels(){
        URL url = loadSave.class.getResource("/lvls");
        File file = null;

        try {
            assert url != null;
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        assert file != null;
        File[] files = file.listFiles();
        assert files != null;
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < filesSorted.length; i++)
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".png"))
                    filesSorted[i] = files[j];

            }

        BufferedImage[] imgs = new BufferedImage[filesSorted.length];

        for (int i = 0; i < imgs.length; i++)
            try {
                imgs[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return imgs;
    }


}
