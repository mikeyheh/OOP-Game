package entities;

import Main.Game;
import utils.loadSave;
import static utils.constant.enemyConstants.*;
import java.util.ArrayList;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyManager {

    private BufferedImage[][] slimeArr;
    private ArrayList<Slime> slimes = new ArrayList<>();
    public EnemyManager(Game game){
        loadEnemy();
        addEnemies();
    }

    private void addEnemies() {
        slimes = loadSave.GetSlimes();
    }

    public void update(int[][] lvldata){
        for(Slime s : slimes){
            s.update(lvldata);
        }
    }

    public void draw(Graphics g){
        drawSlimes(g);

    }

    private void drawSlimes(Graphics g) {
        for(Slime s : slimes){
            g.drawImage(slimeArr[s.getEnemyState()][s.getAnimIndex()],(int)s.getHitbox().x - slimeOffsetX, (int)s.getHitbox().y -slimeOffsetY,slimeWidth,slimeHeight,null);
            //s.drawHitbox(g);
        }
    }

    private void loadEnemy() {
        slimeArr = new BufferedImage[3][4];
        BufferedImage temp = loadSave.getSpriteAtlas(loadSave.Slimes);
        for (int j = 0; j < slimeArr.length; j++){
            for(int i = 0; i < slimeArr[j].length; i++){
                slimeArr[j][i] = temp.getSubimage(i *slimeDefaultWidth, j*slimeDefaultHeight,slimeDefaultWidth, slimeDefaultHeight );
            }
        }
    }
}
