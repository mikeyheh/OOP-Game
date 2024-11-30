package Objects;

import Main.Game;

public class Archer extends GameObject {
    public Archer(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(64,64);
        hitbox.x -=(int)(16* Game.scale);
        hitbox.y -=(int)(16*Game.scale);
    }

    public void update(){
            updateAnimTick();
    }
}
