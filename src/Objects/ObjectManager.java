package Objects;

import GameStates.Playing;
import Levels.level;
import entities.Player;
import utils.loadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static Objects.GameObject.*;

import static utils.constant.ObjectConstants.*;

public class ObjectManager {
    private Playing playing;
    //private BufferedImage[][] test; //For animations of objects
    private BufferedImage spikeImg;
    private ArrayList<Spikes> spikes;

    public ObjectManager(Playing playing){
        this.playing = playing;
        loadImgs();
    }
    public void checkSpikesTouched(Player player){
        for(Spikes s: spikes){
            if(s.getHitbox().intersects(player.getHitbox())){
                player.kill();
            }
        }
    }

    public void loadObjects(level newLevel){
        spikes = newLevel.getSpikes();
    }

    private void loadImgs(){
        spikeImg = loadSave.getSpriteAtlas(loadSave.Trap);
    }

    public void draw(Graphics g){
        drawTraps(g);
    }

    private void drawTraps(Graphics g) {
        for(Spikes s: spikes){
            g.drawImage(spikeImg, (int)(s.getHitbox().x- s.getxOffset()) ,(int)(s.getHitbox().y - s.getyOffset()),spikeWidth,spikeHeight,null);
        }
    }
}
