package GameStates;

import Levels.levelManager;
import Main.Game;
import Objects.ObjectManager;
import UI.GameOverOverlay;
import entities.EnemyManager;
import entities.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {
    private Player player;
    private Levels.levelManager levelManager;
    private EnemyManager enemyManager;
    private ObjectManager objectManager;
    private boolean gameOver;
    private GameOverOverlay gameoveroverlay;

    private int yLvlOffset;
    private int maxTilesOffset;
    private int maxLvlOffsetY;

    public Playing(Game game) {
        super(game);
        initClasses();
        loadAllLevels();
    }

    private void loadAllLevels() {
        enemyManager.loadEnemies(levelManager.getLevels());
        objectManager.loadObjects(levelManager.getCurrentLevel());
    }

    private void initClasses(){
        levelManager = new levelManager(game);
        maxTilesOffset = Game.tileHeight - (getLevelManager().numberofLevels() * Game.tileHeight);
        maxLvlOffsetY = maxTilesOffset * Game.tileSize;
        enemyManager = new EnemyManager(game);
        objectManager = new ObjectManager(this);

        int levelHeight = Game.tileHeight * Game.tileDefaultSize; // Height of one level in pixels

        player = new Player(100 * Game.scale,100 * Game.scale,(int) (50*Game.scale), (int) (37*Game.scale),this);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        gameoveroverlay = new GameOverOverlay(this);
    }

    public void resetAll(){
        gameOver = false;
        player.resetAll();
        enemyManager.resetEnemies();
        objectManager.resetAllObjects();
    }

    public void returnCheckpoint() {
        gameOver = false;
        player.returnCheckpoint();
        enemyManager.resetEnemies();
        objectManager.resetAllObjects();
    }


    public void checkSpikesTouched(Player player) {
        objectManager.checkSpikesTouched(player);
    }
    public void checkCheckpointTouched(Player player) { objectManager.checkCheckpointTouched(player); }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    @Override
    public void update() {
        if(!gameOver){
            levelManager.update();
            objectManager.update(levelManager.getCurrentLevel().getLevelData(),player);
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(),player);
            player.update();

        }else{
            gameoveroverlay.update();
        }

    }

    private void checkPlayerPos() {
        int playerY = (int)player.getHitbox().y;

    }

    public void draw(Graphics g){
        levelManager.draw(g);
        player.render(g);
        enemyManager.draw(g);
        objectManager.draw(g);
        if(gameOver){
            gameoveroverlay.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(gameOver){
            gameoveroverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(gameOver){
            gameoveroverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(gameOver){
            gameoveroverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameOver){
            gameoveroverlay.keyPressed(e);
        }
            else{
                switch (e.getKeyCode()){
                    case KeyEvent.VK_W:
                        player.setJump(true);
                        break;
                    case KeyEvent.VK_A:
                        player.setLeft(true);
                        break;
                    case KeyEvent.VK_D:
                        player.setRight(true);
                        break;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                player.setJump(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;

        }
    }


    public void windowFocusLost(){
        player.resetDir();
    }

    public Player getPlayer() {
        return player;
    }

    public EnemyManager getEnemyManager(){
        return enemyManager;
    }
    public ObjectManager getObjectManager(){
        return objectManager;
    }

    public levelManager getLevelManager() {
        return levelManager;
    }
}



