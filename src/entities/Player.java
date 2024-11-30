package entities;

import GameStates.Playing;
import Main.Game;
import utils.loadSave;
import java.awt.*;
import java.awt.image.BufferedImage;
import static utils.constant.playerConstants.*;
import static utils.helpMethods.canMove;
import static utils.helpMethods.*;

public class Player extends Entity{

    private BufferedImage[][] animations;
    private int animTick, animIndex, animSpeed = 40; //For Animations
    private int playerAction = noWeaponIdle;
    private boolean moving = false;
    private boolean left,up,right,down, jump;
    private float playerSpeed = 1.0f * Game.scale;
    private int[][] lvl;
    private float xOffset = 15 * Game.scale;
    private float yOffset = 7 * Game.scale;
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.scale;
    private float jumpSpeed = - 2.25f * Game.scale;
    private float fallSpeedAfterCollision = 0.5f * Game.scale;
    private boolean inAir = false;
    private boolean facingRight = true;

    private int maxHealth = 1;
    private int currentHealth = 1;
    private Playing playing;
    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width,height);
        this.playing = playing;
        loadAnimations();
        initHitbox(x,y, 20, 28);
    }

    public void update(){
        if(currentHealth <= 0){
            playing.setGameOver(true);
            return;
        }
        checkSpikesTouched();
        updatePosition();
        updateAnimationTick();
        setAnimation();
    }

    private void checkSpikesTouched() {
        playing.checkSpikesTouched(this);
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][animIndex],(int)(hitbox.x - xOffset),(int)(hitbox.y - yOffset), width,height,null);
        //drawHitbox(g);
    }

    private void updateAnimationTick() {
        animTick++;
        if(animTick >= animSpeed){
            animTick = 0;
            animIndex++;
            if(animIndex >= getAnimAmount(playerAction)){
                animIndex = 0;
            }
        }
    }
    private void setAnimation() {
        int startAnim = playerAction;

        if(left){
            facingRight = false;
        }else if (right){
            facingRight = true;
        }

        if(facingRight){
            playerAction = (moving) ? running : noWeaponIdle;

            if(inAir){
                if(airSpeed < 0){
                    playerAction = jumping;
                }else{
                    playerAction = falling;
                }
            }
        }else{  //Face Left
            playerAction = (moving) ? runningBack : noWeaponIdleBack;

            if(inAir){
                if(airSpeed < 0){
                    playerAction = jumpingBack;
                }else{
                    playerAction = fallingBack;
                }
            }
        }
        if(left && right && !inAir){
            playerAction = noWeaponIdle;
        }

        if(startAnim != playerAction){
            resetAnimTick();
        }
    }

    private void resetAnimTick() {
        animTick = 0;
        animIndex = 0;
    }

    private void updatePosition() {
        float xSpeed = 0;
        boolean hasMoved = false;
        moving = false;
        if(jump){
            jump();
        }
        if(!left && !right && !inAir){
            return;
        }


        if(left){
            xSpeed -= playerSpeed;

        }
        if(right){
            xSpeed += playerSpeed;
        }

        if(!inAir){
            if(!grounded(hitbox,lvl)){
                inAir = true;
            }
        }

        if(inAir){
            if(canMove(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvl)){
                hitbox.y += airSpeed;
                airSpeed += gravity;
                hasMoved = updateXpos(xSpeed);
            }else{
                hitbox.y = getEntityYpos(hitbox,airSpeed);
                if(airSpeed > 0){
                    airReset();
                }else{
                    airSpeed = fallSpeedAfterCollision;
                }
                hasMoved = updateXpos(xSpeed);
            }
        }else{
            hasMoved = updateXpos(xSpeed);
        }
        moving = hasMoved ;
    }

    private void jump() {
        if(inAir){
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void airReset() {
        inAir = false;
        airSpeed = 0;
    }

    private boolean updateXpos(float xSpeed) {
        if(canMove(hitbox.x+xSpeed, hitbox.y, hitbox.width, hitbox.height, lvl)){
            hitbox.x += xSpeed;
            return true;
        }else{
            hitbox.x = getEntityPosToWall(hitbox,xSpeed);
            return false;
        }

    }

    public void changeHealth(int val){
        currentHealth += val;
        if(currentHealth <= 0){
           currentHealth = 0;
        }
        else if(currentHealth >= maxHealth){
            currentHealth = maxHealth;
        }
    }

    public void kill(){
        currentHealth = 0;
    }

    private void loadAnimations() {
            BufferedImage img = loadSave.getSpriteAtlas(loadSave.playerAtlas);

            animations = new BufferedImage[8][6];
            for(int j = 0; j<animations.length;j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * 50, j * 37, 50, 37);
                }
            }

    }
    public void loadLvlData(int [][] lvl){
        this.lvl = lvl;
        if(!grounded(hitbox,lvl)){
            inAir = true;
        }
    }

    public void resetDir(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setJump(boolean jump){
        this.jump = jump;
    }

    public void resetAll(){
        resetDir();
        inAir = false;
        moving = false;
        playerAction = noWeaponIdle;
        currentHealth = maxHealth;

        hitbox.x = x;
        hitbox.y = y;
        if(!grounded(hitbox,lvl)){
            inAir = true;
        }
    }
}


