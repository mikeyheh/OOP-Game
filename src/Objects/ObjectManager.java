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
    private BufferedImage[][] archerImgs; //For animations of objects
    private BufferedImage spikeImg;
    private ArrayList<Spikes> spikes;
    private ArrayList<Archer> archers;

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
//        for(Archer a: archers){
//            if(a.getHitbox().intersects(player.getHitbox())){
//                player.kill();
//            }
//        }
    }

    public void loadObjects(level newLevel){
        spikes = newLevel.getSpikes();
        archers = newLevel.getArchers();
    }

    private void loadImgs(){
        spikeImg = loadSave.getSpriteAtlas(loadSave.Trap);

        archerImgs = new BufferedImage[8][7];
        BufferedImage temp = loadSave.getSpriteAtlas(loadSave.Archer);
        for(int j = 0; j < archerImgs.length;j++){
            for(int i = 0; i < archerImgs[j].length;i++){
                archerImgs[j][i] = temp.getSubimage(i*64, j*64, 64 ,64);
            }
        }
    }

    public void update(){
        for(Archer a : archers){
            a.update();
        }
    }




    public void draw(Graphics g){
        drawTraps(g);
        drawArchers(g);
    }

    private void drawArchers(Graphics g) {
        for(Archer a: archers){
            int x = (int)(a.getHitbox().x);
            int width = archerWidth;
            if(a.getObjType() == archer_right){
                x+=width;
                width *= -1;
            }
            g.drawImage(archerImgs[3][a.getAnimIndex()],x,(int)(a.getHitbox().y),width,archerHeight,null);
        }
    }

    private void drawTraps(Graphics g) {
        for(Spikes s: spikes){
            g.drawImage(spikeImg, (int)(s.getHitbox().x- s.getxOffset()) ,(int)(s.getHitbox().y - s.getyOffset()),spikeWidth,spikeHeight,null);
        }
    }

    public void resetAllObjects(){
        for(Archer a : archers){
            a.reset();
        }
    }
}
