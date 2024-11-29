package Objects;

import Main.Game;

public class Spikes extends GameObject{
    public Spikes(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(32,16);
        xOffset = 0;
        yOffset = (int)(Game.scale * 16);
        hitbox.y += yOffset;
    }
}
