package entities;
import Main.Game;

import java.awt.geom.Rectangle2D;

import static utils.constant.enemyConstants.*;
import static utils.helpMethods.*;
import static utils.constant.Directions.*;

public abstract class Enemy extends Entity {
    protected int animIndex, enemyState, enemyType;
    protected int animTick, animSpeed = 50;
    protected boolean firstupdate = true, inAir;
    protected float fallSpeed, gravity = 0.04f * Game.scale;
    protected float walkspeed = 0.45f * Game.scale;
    protected int walkDir = LEFT;
    protected Rectangle2D.Float detection;
    protected boolean attackChecked;

    public Enemy(float x, float y, int width, int height, int enemyType){
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x,y,width,height);
    }
    private void hitDetection(){
        detection = new Rectangle2D.Float(x,y,(int)(26* Game.scale),(int)(18 * Game.scale));
    }

    protected void firstUpdateCheck(int[][] lvldata){
        if (!grounded(hitbox, lvldata)) {
            inAir = true;
            firstupdate = false;
        }
    }

    protected void updateInAir(int[][] lvldata){
        if(canMove(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvldata)){
            hitbox.y += fallSpeed;
            fallSpeed += gravity;
        }else{
            inAir = false;
            hitbox.y = getEntityYpos(hitbox, fallSpeed);
        }
    }

    protected void move(int[][] lvldata){
        float xspeed = 0;

        if(walkDir == LEFT){
            xspeed = -walkspeed;
        }else{
            xspeed = walkspeed;
        }

        if(canMove(hitbox.x + xspeed, hitbox.y, hitbox.width, hitbox.height, lvldata))
            if(isFloor(hitbox,xspeed,lvldata)){
                hitbox.x += xspeed;
                return;
            }

        changeWalkDir();
    }
    protected void checkPlayerHit(Rectangle2D.Float attackbox, Player player){
        if(attackbox.intersects(player.hitbox)){
            player.changeHealth(-enemyDMG(enemyType));
            attackChecked = true;
        }
    }

    protected void newState(int enemyState){
        this.enemyState = enemyState;
        animTick = 0;
        animIndex = 0;
    }

    protected void updateAnimTick(){
        animTick++;
        if(animTick >= animSpeed){
            animTick = 0;
            animIndex++;
            if(animIndex >= getSpriteAmount(enemyType, enemyState)){
                animIndex = 0;
            }
        }
    }



    private void updateMove(int[][] lvldata){
        if(firstupdate) {
            if (!grounded(hitbox, lvldata)) {
                inAir = true;
                firstupdate = false;
            }
        }
        if(inAir){
            if(canMove(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, lvldata)){
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            }else{
                inAir = false;
                hitbox.y = getEntityYpos(hitbox, fallSpeed);
            }
        }else{
                switch(enemyState){
                    case idle:
                        enemyState = slimeRunning;
                        break;
                    case slimeRunning:
                        float xspeed = 0;

                        if(walkDir == LEFT){
                            xspeed = -walkspeed;
                        }else{
                            xspeed = walkspeed;
                        }

                        if(canMove(hitbox.x + xspeed, hitbox.y, hitbox.width, hitbox.height, lvldata))
                            if(isFloor(hitbox,xspeed,lvldata)){
                                hitbox.x += xspeed;
                                return;
                            }

                        changeWalkDir();
                        break;
                }
        }
    }

    protected void changeWalkDir() {
        if(walkDir == LEFT){
            walkDir = RIGHT;
        }else{
            walkDir = LEFT;
        }
    }

    public int getAnimIndex(){
        return animIndex;
    }
    public int getEnemyState(){
        return enemyState;
    }

    public void resetEnemy(){
        hitbox.x =x;
        hitbox.y = y;
        firstupdate = true;
        newState(idle);
        fallSpeed = 0;
    }
}
