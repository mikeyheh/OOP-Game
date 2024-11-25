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
            firstUpdateCheck(lvldata);
        }
        if(inAir){
            updateInAir(lvldata);
        }else{
            switch(enemyState){
                case idle:
                    enemyState = slimeRunning;
                    break;
                case slimeRunning:
                    move(lvldata);
                    break;
            }
        }
    }
}
