package Main;


import GameStates.Gamestate;
import GameStates.Menu;
import GameStates.Playing;
import entities.EnemyManager;
import entities.Player;
import Levels.levelManager;


import java.awt.*;

public class Game implements Runnable{
    private Window gameWindow;
    private GamePanel panel;
    private Thread gameThread;
    private final int FpsSet = 120;
    private final int UpsSet = 200;

    private Playing playing;
    private Menu menu;

    public final static int tileDefaultSize = 32;
    public final static float scale = 2f;
    public final static int tileWidth = 26;
    public final static int tileHeight = 14;
    public final static int tileSize = (int) (tileDefaultSize * scale);
    public final static int gameWidth = tileSize * tileWidth;
    public final static int gameHeight = tileSize * tileHeight;

    public Game(){
        initClasses();

        panel = new GamePanel(this);
        gameWindow = new Window(panel);
        panel.requestFocus();

        gameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void gameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch(Gamestate.state){
            case Menu:
                menu.update();
                break;
            case Playing:
                playing.update();
                break;
            default:
                break;
        }
    }
    public void render(Graphics g){
        switch(Gamestate.state){
            case Menu:
                menu.draw(g);
                break;
            case Playing:
                playing.draw(g);
                break;
            default:
                break;
        }

    }

    public void run(){
        double timePerFrame = 1000000000.0 / FpsSet;
        double timePerUpdate = 1000000000.0 / UpsSet;

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        long prevTime = System.nanoTime();
        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - prevTime) / timePerUpdate;
            deltaF += (currentTime - prevTime) / timePerFrame;
            prevTime = currentTime;

            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }
            if(deltaF >= 1){
                panel.repaint();
                frames++;
                deltaF--;
            }


            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("Fps: "+ frames + "| UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    public void windowFocusLost(){
        if(Gamestate.state == Gamestate.Playing){
            playing.getPlayer().resetDir();
        }
    }

    public Menu getMenu(){
        return menu;
    }
    public Playing getPlaying(){
        return playing;
    }

}
