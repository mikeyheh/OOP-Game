package Objects;

import Main.Game;

public class Archer extends GameObject {
    private float speedMult = (float).8;

    public Archer(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(64,64);
        hitbox.x -=(int)(16* Game.scale);
        hitbox.y -=(int)(16*Game.scale);
    }

    public void increaseSpeed(){
        this.speedMult += (float)0.01;
    }

    public void update(){
            updateAnimTick(speedMult);
    }
}
