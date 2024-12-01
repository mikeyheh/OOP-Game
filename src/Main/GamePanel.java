package Main;

import Inputs.KeyboardInputs;
import Inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import static Main.Game.gameHeight;
import static Main.Game.gameWidth;

public class GamePanel extends JPanel {

    private Game game;
    private MouseInputs mouseInputs;
    public GamePanel(Game game){
        this.game = game;
        setPanelSize();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(gameWidth,gameHeight);
        setPreferredSize(size);
        System.out.println("Size: "+ gameWidth + "x" + gameHeight);
    }

    public void paintComponent(Graphics g ){
        super.paintComponent(g);
        game.render(g);
    }
    public Game getGame(){
        return game;
    }
}
