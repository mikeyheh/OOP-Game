package UI;

import Main.Game;
import entities.Player;
import Levels.levelManager;

public class Camera {
    private int cameraX, cameraY; // Camera position
    private int screenHeight, screenWidth; // Screen dimensions (or viewport size)

    private int levelHeight; // Height of a single level in pixels

    public Camera(int screenWidth, int screenHeight, int levelHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.levelHeight = levelHeight;
        this.cameraX = 0; // Initially aligned to (0, 0)
        this.cameraY = 0;
    }

    public void checkPlayerPos(Player player) {
        // Get player's current vertical position
        int playerY = player.getY();

        // Determine which level the player is in
        int currentLevel = playerY / levelHeight; // Integer division gives the current level index

        // Adjust the camera's Y position to the top of that level
        cameraY = currentLevel * levelHeight;


    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }
}
