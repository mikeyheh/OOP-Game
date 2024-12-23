package Objects;

import GameStates.Playing;
import Levels.level;
import entities.Player;
import utils.loadSave;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Main.Game;

import static utils.constant.ObjectConstants.*;
import static utils.constant.Projectiles.*;
import static utils.helpMethods.projectileHitGround;

public class ObjectManager {
    private Playing playing;
    private BufferedImage[][] archerImgs; //For animations of objects
    private BufferedImage spikeImg, arrowImg;
    private ArrayList<Spikes> spikes;
    private ArrayList<Archer> archers;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private int dir;

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
        projectiles.clear();
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
        arrowImg = loadSave.getSpriteAtlas(loadSave.Arrow);
    }

    public void update(int[][] lvldata, Player player){
        for(Archer a : archers){
            a.update();
            if(a.getAnimIndex() == 6 && a.getAnimTick() == 0){
                dir = -1;
                if(a.getObjType() == archer_left){
                    dir = 1;
                }
                projectiles.add(new Projectile((int)a.getHitbox().x, (int)a.getHitbox().y,dir));
            }
        }
        updateProjectiles(lvldata,player);
    }

    private void updateProjectiles(int[][] lvldata, Player player) {
        for(Projectile p : projectiles){
            if(p.isActive()){
                p.updatePos();
                if(p.getHitbox().intersects(player.getHitbox())){
                    player.kill();
                    p.setActive(false);
                }else if(projectileHitGround(p,lvldata)){
                    p.setActive(false);
                }
            }
        }
    }


    public void draw(Graphics g){
        drawTraps(g);
        drawArchers(g);
        drawProjectiles(g);
    }

    private void drawProjectiles(Graphics g) {
        for(Projectile p : projectiles) {
            if(p.isActive()) {
                int width = arrowWidth;
                int xPos = (int)p.getHitbox().x;

                if(p.getDir() < 0) {
                    width = -arrowWidth;
                    xPos += arrowWidth;
                }

                g.drawImage(arrowImg, xPos, (int)p.getHitbox().y, width, arrowHeight, null);
                //Debugging of Hitbox
//                g.setColor(Color.RED);
//                g.drawRect((int)p.getHitbox().x, (int)p.getHitbox().y, (int)p.getHitbox().width, (int)p.getHitbox().height);
            }
        }
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
        loadObjects(playing.getLevelManager().getCurrentLevel());
        for(Archer a : archers){
            a.reset();
        }
    }
}
