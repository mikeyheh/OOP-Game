package entities;

import Main.Game;

import static utils.constant.Directions.LEFT;
import static utils.constant.enemyConstants.*;
import static utils.helpMethods.*;

public class Slime extends Enemy{
    public Slime(float x, float y) {
        super(x, y, slimeWidth, slimeHeight, Slime);
        initHitbox(x,y,(int)(26* Game.scale),(int)(18 * Game.scale));

    }

    public void update(int[][] lvldata){
        updateMove(lvldata);
        updateAnimTick();
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
}
