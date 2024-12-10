package Objects;

import Main.Game;

import static utils.constant.ObjectConstants.getSpriteAmount;

public class Chest extends GameObject {

    public Chest(int x, int y, int objType){
        super(x,y,objType);

        initHitbox(50/2, 50/2);
        xOffset = (int)((Game.tileSize - (Game.scale * 18)) / 2);
        yOffset = (int)((Game.tileSize - (Game.scale * 18)) / 2);
        hitbox.y += yOffset;
        hitbox.x += xOffset;

        this.animSpeed = 30;
    }

    public boolean isAnimationComplete() {
        return animIndex == getSpriteAmount(objType) - 1;
    }

    public void update() {
        updateAnimTick(1);
    }
}
