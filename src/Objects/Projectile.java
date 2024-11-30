package Objects;

import Main.Game;

import static utils.constant.Projectiles.*;
import java.awt.geom.Rectangle2D;

public class Projectile {
    private Rectangle2D.Float hitbox;
    private int dir;
    private boolean active = true;

    public Projectile(int x, int y, int dir){
        int xOffset = (int) (-20 * Game.scale);
        int yOffset = (int) (23 * Game.scale);
        if(dir == 1){
            xOffset = (int) (29*Game.scale); //If archer facing right
        }

        hitbox = new Rectangle2D.Float(x+xOffset,y+yOffset,arrowWidth,arrowHeight);
        this.dir = dir;
    }
    public void updatePos(){
        hitbox.x += dir * speed;
    }

    public void setPos(int x, int y){
        hitbox.x =x;
        hitbox.y = y;
    }

    public Rectangle2D.Float getHitbox(){
        return hitbox;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public boolean isActive(){
        return active;
    }

    public int getDir(){
        return dir;
    }
}
