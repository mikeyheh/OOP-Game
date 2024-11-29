package entities;

import Levels.level;
import Main.Game;
import utils.loadSave;
import static utils.constant.enemyConstants.*;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyManager {

    private BufferedImage[][] slimeArr;
    private ArrayList<Slime> slimes = new ArrayList<>();
    public EnemyManager(Game game){
        loadEnemy();
    }

    public void loadEnemies(level level) {
        slimes =level.getSlimes() ;
    }


    public void update(int[][] lvldata, Player player){
        for(Slime s : slimes){
            s.update(lvldata, player);
        }
    }

    public void draw(Graphics g){
        drawSlimes(g);

    }

    private void drawSlimes(Graphics g) {
        for(Slime s : slimes){
            g.drawImage(slimeArr[s.getEnemyState()][s.getAnimIndex()],(int)s.getHitbox().x - slimeOffsetX + s.flipX(), (int)s.getHitbox().y -slimeOffsetY,slimeWidth *s.flipW(),slimeHeight,null);
            s.drawHitbox(g);
            //s.drawAttackBox(g);
        }
    }

//    public void checkEnemyHit(Rectangle2D.Float attackbox){
//        for(Slime s : slimes){
//            if(attackbox.intersects(s.getHitbox())){
//                System.out.println("HIT!");
//            }
//        }
//    }

    private void loadEnemy() {
        slimeArr = new BufferedImage[3][4];
        BufferedImage temp = loadSave.getSpriteAtlas(loadSave.Slimes);
        for (int j = 0; j < slimeArr.length; j++){
            for(int i = 0; i < slimeArr[j].length; i++){
                slimeArr[j][i] = temp.getSubimage(i *slimeDefaultWidth, j*slimeDefaultHeight,slimeDefaultWidth, slimeDefaultHeight );
            }
        }
    }

    public void resetEnemies(){
        for(Slime s : slimes){
            s.resetEnemy();
        }
    }
}
