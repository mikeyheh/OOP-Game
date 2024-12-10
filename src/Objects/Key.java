package Objects;

import Main.Game;

public class Key extends  GameObject {


    public Key(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(20,20);
        xOffset = (int)((Game.tileSize - (Game.scale * 20)) / 2);
        yOffset = (int)(Game.scale * 12);
        hitbox.y += yOffset;
        hitbox.x += xOffset;
        this.animSpeed = 30;
    }

    public void update() {
        updateAnimTick(1);
    }
}
