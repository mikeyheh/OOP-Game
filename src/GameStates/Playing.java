package GameStates;

import Levels.levelManager;
import Main.Game;
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
    private boolean gameOver;
    private GameOverOverlay gameoveroverlay;

    public Playing(Game game) {
        super(game);
        initClasses();
        loadStartLevel();
    }
    public void loadNextLevel(){
        resetAll();
        levelManager.loadNextLevel();
    }

    private void loadStartLevel() {
        enemyManager.loadEnemies(levelManager.getCurrentLevel());
    }

    private void initClasses(){
        levelManager = new levelManager(game);
        enemyManager = new EnemyManager(game);
        player = new Player(200,200,(int) (50*Game.scale), (int) (37*Game.scale),this);
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
        gameoveroverlay = new GameOverOverlay(this);
    }

    public void resetAll(){
        gameOver = false;
        player.resetAll();
        enemyManager.resetEnemies();
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    @Override
    public void update() {
        if(!gameOver){
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLevelData(),player);
        }
    }

    public void draw(Graphics g){
        if(gameOver){
            gameoveroverlay.draw(g);
        }
        levelManager.draw(g);
        player.render(g);
        enemyManager.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
}



