package com.ae2dms.BubbleBobble.Database;

import com.ae2dms.BubbleBobble.Controller.GameController;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;
import java.io.*;

/**
 * The {@link com.ae2dms.BubbleBobble.Database.DataManager} handles operations related with game data:
 * <p>
 * (1) updating level,score, lives in the world
 * (2) check the finish condition of the game
 * (3) store the hero appearance
 * </p>
 * @see InteractableWorld
 */
public class DataManager {
    /**
     * the world
     */
    private final InteractableWorld world;
    /**
     * the current level
     */
    private int level;
    /**
     * the total score
     */
    private int score;
    /**
     * the score in current level
     */
    private int currentScore;
    /**
     * the remaining lives of the hero
     */
    private int lives;
    /**
     * whether game finishes
     */
    private boolean isFinish;
    /**
     * the hero image
     */
    private Image heroImage;
    /**
     * the single instance of {@code DataManager}
     */
    private static DataManager instance;

    /**
     * @return the current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return accumulated score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param currentScore  indicates scores gained in current level
     */
    public void setCurrentScore(int currentScore) {
        if (currentScore<0||currentScore>2147483647){
            throw new IllegalArgumentException("The score value is invalid!");
        }
        this.currentScore = currentScore;
    }

    /**
     * @return scores gained in current level
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * @param lives indicates the remaining lives of the game
     */
    public void setLives(int lives) {
        if(lives>5||lives<0){
            throw new IllegalArgumentException("The lives value is invalid!");
        }
        this.lives = lives;
    }

    /**
     * @return the remaining lives of the game
     */
    public int getLives() {
        return lives;
    }

    /**
     * @return whether the game finshes
     */
    public boolean isFinish() {
        return isFinish;
    }

    /**
     * @return the appearance of the hero stored
     */
    public Image getHeroImage() {
        return heroImage;
    }

    /**
     * @param heroImage indicates the appearance of the hero chosen by the player
     * @see GameController
     */
    public void setHeroImage(Image heroImage){
        this.heroImage = heroImage;
    }

    /**
     * The constructor to create a singleton {@code DataManager} nstance
      * @param world indicates the owner of the {@code DataManager}
     */
    private DataManager(InteractableWorld world){
        this.world = world;
        level=1;
        score=0;
        currentScore=0;
        lives=5; // the hero could die for at most 5 times
        DataManager.instance=this;
        isFinish=false;
    }

    /**
     * gets the instance of DataManager
     * @return the single instance of the {@code DataManager}
     */
    public static DataManager getInstance() {
        return instance;
    }

    /**
     * initializes the single {@code DataManager} instance
     * @param world indicates the owner of the data manager
     */
    public static void init(InteractableWorld world) {
        if (DataManager.instance ==  null ) {
            new DataManager(world);
        }
    }

    /**
     * The method to reset the data
     */
    public void restart() {
        level=1;
        score=0;
        currentScore=0;
        lives=5;
        try {
            world.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to calculate the score and add level
     * @see com.ae2dms.BubbleBobble.Unit.SpecialUnit.DoorUnit
     */
    public void nextLevel() {
        score = currentScore+score; // accumulate the score
        currentScore=0;
        if(level==4) {
            isFinish = true; // the game finished
            return;
        }
        level++;
        if(level==3){
            world.setRandomDrop();
        }
        try {
            world.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
