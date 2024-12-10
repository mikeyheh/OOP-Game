package utils;

import Main.Game;

public class constant {
    public static class UI {
        public static class Buttons {
            public static final int buttonDefaultWidth = 64;
            public static final int buttonDefaultHeight = 16;
            public static final int buttonWidth = (int) (buttonDefaultWidth * Game.scale * 2.5);
            public static final int buttonHeight = (int) (buttonDefaultHeight * Game.scale * 2.5);
        }
    }

    public static class Projectiles{
        public static final int arrowDefaultWidth = 40;
        public static final int arrowDefaultHeight = 5;
        public static final int arrowWidth = (int)(arrowDefaultWidth * Game.scale);
        public static final int arrowHeight = (int)(arrowDefaultHeight * Game.scale);
        public static final float speed = 0.5f * Game.scale;
    }
    public static class ObjectConstants{
        public static final int Checkpoint = 0;
        public static final int Spike = 1;
        public static final int archer_right = 2;
        public static final int archer_left = 3;
        //Slime is 4
        public static final int Key = 5;
        public static final int Chest = 6;

        public static final int defaultSpikeWidth = 32;
        public static final int defaultSpikeHeight = 32;
        public static final int spikeWidth = (int)(Game.scale * defaultSpikeWidth);
        public static final int spikeHeight = (int)(Game.scale * defaultSpikeHeight);

        public static final int defaultArcherWidth = 64;
        public static final int defaultArcherHeight = 64;
        public static final int archerWidth = (int)(defaultArcherWidth*Game.scale);
        public static final int archerHeight = (int)(defaultArcherHeight*Game.scale);

        public static final int defaultCheckpointWidth = 64;
        public static final int defaultCheckpointHeight = 64;
        public static final int checkpointWidth = (int)(defaultCheckpointWidth*Game.scale);
        public static final int checkpointHeight = (int)(defaultCheckpointHeight*Game.scale);

        public static final int defaultKeyWidth = 20;
        public static final int defaultKeyHeight = 20;
        public static final int keyWidth = (int)(defaultKeyWidth*Game.scale);
        public static final int keyHeight = (int)(defaultKeyHeight*Game.scale);

        public static final int defaultChestWidth = 50;
        public static final int defaultChestHeight = 50;
        public static final int chestWidth = (int)(defaultChestWidth*Game.scale);
        public static final int chestHeight = (int)(defaultChestHeight*Game.scale);

        public static int getSpriteAmount(int objType){
            switch(objType){
                case archer_left, archer_right:
                    return 7;
                case Checkpoint:
                    return 7;
                case Key:
                    return 7;
                case Chest:
                    return 6;
            }
            return 1;
        }
    }

    public static class enemyConstants{
        public static final int Slime = 4;

        public static final int idle = 0;
        public static final int slimeRunning = 1;
        public static final int slimeRunningBack = 0;

        public static final int slimeDefaultWidth = 32;
        public static final int slimeDefaultHeight = 32;

        public static final int slimeWidth = (int) (slimeDefaultWidth * Game.scale);
        public static final int slimeHeight = (int) (slimeDefaultHeight * Game.scale);

        public static final int slimeOffsetX = (int)(6* Game.scale);
        public static final int slimeOffsetY = (int)(13 * Game.scale);

        public static int getSpriteAmount(int enemyType, int enemyState){
            switch(enemyType){
                case Slime:
                    switch(enemyState){
                        case idle:
                        case slimeRunning:
                            return 4;
                    }
            }
            return 0;
        }
        public static int enemyDMG(int enemyType){
            switch (enemyType){
                case Slime:
                    return 1;
                default:
                    return 0;
            }
        }
    }


    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }
    public static class playerConstants{
        public static final int running = 0;
        public static final int noWeaponIdle = 1;
        public static final int falling = 2;
        public static final int jumping = 3;
        public static final int runningBack = 4;
        public static final int noWeaponIdleBack = 5;
        public static final int jumpingBack = 6;
        public static final int fallingBack = 7;



        public static int getAnimAmount(int playerAction){
            switch(playerAction){
                case running:
                case runningBack:
                    return 6;
                case noWeaponIdle:
                case jumping:
                case noWeaponIdleBack:
                case jumpingBack:
                    return 4;
                case falling:
                case fallingBack:
                    return 2;
                default:
                    return 1;
            }
        }
    }
}
