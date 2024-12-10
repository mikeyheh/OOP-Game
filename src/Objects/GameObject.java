package Objects;

import Main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utils.constant.ObjectConstants.*;

public class GameObject {
    protected int x,y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean doAnim;
    protected int animTick, animIndex;
    protected int xOffset, yOffset;
    public int animSpeed = 80;

    public GameObject(int x, int y, int objType){
        this.x = x;
        this.y =y;
        this.objType = objType;

    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x,y,(int)(width* Game.scale),(int)(height*Game.scale));
    }

    public void drawHitbox(Graphics g){
        //Debug
        g.setColor(Color.red);
        g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }

    protected void updateAnimTick(float multiplier){
        animTick++;
        if(animTick >= animSpeed / multiplier){
            animTick = 0;
            animIndex++;
            if(animIndex >= getSpriteAmount(objType)){
                animIndex = 0;
            }
        }
    }

    public void reset(){
        animIndex = 0;
        animTick = 0;
        doAnim = true;
    }

    public int getxOffset() {
        return xOffset;
    }


    public int getyOffset() {
        return yOffset;
    }

    public void setAnimation(boolean doAnim){
        this.doAnim = doAnim;
    }

    public int getObjType() {
        return objType;
    }

    public int getAnimIndex(){
        return animIndex;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public int getAnimTick() {
        return animTick;
    }

}
