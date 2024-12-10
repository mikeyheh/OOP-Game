package GameStates;

import Audio.AudioManager;
import Levels.levelManager;
import Main.Game;
import Objects.Chest;
import Objects.ObjectManager;
import UI.GameOverOverlay;
import UI.GameWonOverlay;
import entities.EnemyManager;
import entities.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utils.helpMethods.GetLevelData;
import static utils.helpMethods.canMove;

public class Playing extends State implements Statemethods {
    private Player player;
    private Levels.levelManager levelManager;
    private EnemyManager enemyManager;
    private ObjectManager objectManager;
    private boolean gameOver;
    private boolean gameWon;
    private GameOverOverlay gameoveroverlay;
    private GameWonOverlay gamewonoverlay;

    public final String backgroundMusic = "/audio/gameMusic.wav";
    public final AudioManager audioManager = new AudioManager();

    public Playing(Game game) {
        super(game);
        initClasses();
        loadStartLevel();
    }

    public void playGameMusic(String path){
        audioManager.playMusic(path);
        audioManager.setVolume(0.7f);
    }

    public void stopPlayingMusic() {
        audioManager.stopCurrentMusic(); // Stop music when leaving the playing state
    }

    public void loadNextLevel() {
        if (levelManager.getCurrentLevelIndex() < levelManager.numberofLevels() - 1) {
            int[][] nextLevelData = levelManager.getNextLevelData();

            if (canMove(player.getHitbox().x, player.getHitbox().y/Game.scale + Game.gameHeight, player.getHitbox().width, Game.gameHeight+Game.tileSize, nextLevelData)) {
                player.newLevel(1, player);
                enemyManager.resetEnemies();
                objectManager.resetAllObjects();

                levelManager.loadNextLevel();
                enemyManager.loadEnemies(levelManager.getCurrentLevel());
                objectManager.loadObjects(levelManager.getCurrentLevel(), levelManager.getCurrentLevelIndex()
                );
            } else {
                player.setAirSpeed();
            }
        } else {
            player.setAirSpeed();
        }
    }

    public void loadPrevLevel() {

        if(levelManager.getCurrentLevelIndex() > 0){
            player.newLevel(-1, player);
            enemyManager.resetEnemies();
            objectManager.resetAllObjects();
            levelManager.loadPrevLevel();
            enemyManager.loadEnemies(levelManager.getCurrentLevel());
            objectManager.loadObjects(levelManager.getCurrentLevel(), levelManager.getCurrentLevelIndex());
        }else{
            gameOver = true;
        }

    }

    private void loadStartLevel() {
        enemyManager.loadEnemies(levelManager.getCurrentLevel());
        objectManager.loadObjects(levelManager.getCurrentLevel(), levelManager.getCurrentLevelIndex());
    }

    private void initClasses(){
        levelManager = new levelManager(game);
        enemyManager = new EnemyManager(game);
        objectManager = new ObjectManager(this);
        player = new Player(Game.startingX * Game.scale,Game.startingY * Game.scale,(int) (50*Game.scale), (int) (37*Game.scale),this);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        gameoveroverlay = new GameOverOverlay(this);
        gamewonoverlay = new GameWonOverlay(this);
    }


    public void resetAll(){
        gameOver = false;
        gameWon = false;

        enemyManager.resetEnemies();
        objectManager.resetAllObjects();

        levelManager.loadSelectedLevel(0);
        enemyManager.loadEnemies(levelManager.getCurrentLevel());
        objectManager.loadObjects(levelManager.getCurrentLevel(), levelManager.getCurrentLevelIndex());
        player.resetAll();
    }

    public void returnCheckpoint() {
        gameOver = false;
        gameWon = false;

        enemyManager.resetEnemies();
        objectManager.resetAllObjects();

        levelManager.loadSelectedLevel(player.getSpawnLevel());
        enemyManager.loadEnemies(levelManager.getCurrentLevel());
        objectManager.loadObjects(levelManager.getCurrentLevel(), levelManager.getCurrentLevelIndex());
        player.returnCheckpoint();

    }

    public void checkSpikesTouched(Player player) {
        objectManager.checkSpikesTouched(player);
    }
    public void checkCheckpointTouched(Player player) {
        objectManager.checkCheckpointTouched(player);
    }
    public void checkKeyTouched(Player player) {objectManager.checkKeyTouched(player);}
    public void checkReachedEdge(Player player){
        objectManager.checkReachedEdge(player);
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
    public void setGameWon(boolean gameWon){this.gameWon = gameWon;}

    @Override
    public void update() {
        if (gameWon) {
                gamewonoverlay.update();
        }else{
            if(!gameOver){
                levelManager.update();
                objectManager.update(levelManager.getCurrentLevel().getLevelData(),player);

                player.update();
                enemyManager.update(levelManager.getCurrentLevel().getLevelData(),player);
            }else{
                gameoveroverlay.update();
            }
        }
    }

    public void draw(Graphics g){
        levelManager.draw(g);
        enemyManager.draw(g);
        objectManager.draw(g);
        player.render(g);
        if(gameOver){
            gameoveroverlay.draw(g);
        }
        if(gameWon){
            gamewonoverlay.draw(g);
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
        if(gameWon){
            gamewonoverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(gameOver){
            gameoveroverlay.mouseReleased(e);
        }
        if(gameWon){
            gamewonoverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(gameOver){
            gameoveroverlay.mouseMoved(e);
        }
        if(gameWon){
            gamewonoverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(gameWon){
            gamewonoverlay.keyPressed(e);
        }else{
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

    public boolean isGameWon() {
        return gameWon;
    }

    private boolean allChestsAnimationComplete() {
        for (Chest chest : objectManager.getChests()) {
            if (!chest.isAnimationComplete()) {
                return false;
            }
        }
        return true;
    }
}



