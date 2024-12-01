package Objects;

import Main.Game;

public class Checkpoint extends GameObject {
    public Checkpoint(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(32,32);
        xOffset = 0;
        yOffset = (int)(Game.scale * 16);
    }

    public void update() {
        updateAnimTick();
    }
}
