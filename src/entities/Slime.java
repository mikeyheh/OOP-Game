package entities;

import Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utils.constant.Directions.RIGHT;
import static utils.constant.enemyConstants.*;

public class Slime extends Enemy{
    private Rectangle2D.Float attackbox;
    private int attackBoxOffsetX;

    public Slime(float x, float y) {
        super(x, y, slimeWidth, slimeHeight, Slime);
        initHitbox(x,y,26,18 * Game.scale);
        initattackbox();
    }

    private void initattackbox() {
        attackbox = new Rectangle2D.Float(x,y,(int)(26* Game.scale),(int)(18 * Game.scale));
        attackBoxOffsetX = (int) (Game.scale * 30);
    }

    public void update(int[][] lvldata, Player player){
        updateBehavior(lvldata,player);
        updateAnimTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackbox.x = hitbox.x;
        attackbox.y = hitbox.y;
    }

    private void updateBehavior(int[][] lvldata, Player player){
        if(firstupdate) {
            firstUpdateCheck(lvldata);
        }
        if(inAir){
            updateInAir(lvldata);
        }else{
            switch(enemyState){
                case idle:
                    newState(slimeRunning);
                    break;
                case slimeRunning:
                    move(lvldata);
                    attackChecked = false;
                    if(!attackChecked){
                        checkPlayerHit(hitbox,player);
                    }

                    break;
            }
        }
    }

    public void drawAttackBox(Graphics g){
        g.setColor(Color.red);
        g.drawRect((int)attackbox.x,(int)attackbox.y,(int)attackbox.width,(int)attackbox.height);
    }

    public int flipX(){
        return (walkDir == RIGHT) ? width : 0;
    }
    public int flipW(){
        return (walkDir == RIGHT) ? -1 : 1;
    }
}
