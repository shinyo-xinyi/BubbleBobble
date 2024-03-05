package com.ae2dms.BubbleBobble;


import com.ae2dms.BubbleBobble.Database.DataManager;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.Assert.assertEquals;

class DataManagerTest {
    private static InteractableWorld world;
    private DataManager dataManager;

    @BeforeEach
    void setUp(){
        world = new InteractableWorld(Main.UNIT_SIZE*Main.WIDTH,Main.UNIT_SIZE*Main.HEIGHT);
        DataManager.init(world);
        dataManager = DataManager.getInstance();
    }

    @Test
    void test_getLevel() {
        assertEquals(1,dataManager.getLevel());
    }

    @Test
    void test_getScore() {
        assertEquals(0,dataManager.getScore());
    }

    @Test
    void test_getLives() {
        assertEquals(5,dataManager.getLives());
    }

    @Test
    void test_getCurrentScore() {
        assertEquals(0,dataManager.getCurrentScore());
    }

    @Test
    @ParameterizedTest
    @CsvSource( value={"4"} )
    void test_setLives(int lives) {
        dataManager.setLives(lives);
        assertEquals(lives,dataManager.getLives());
    }

    @Test
    @ParameterizedTest
    @CsvSource( value={"6"} )
    void test_setLives_invalid(int lives) {
        try{
            dataManager.setLives(lives);
        }
        catch(IllegalArgumentException e){
            assertEquals("The lives value is invalid!",e.getMessage());
        }
    }

    @Test
    @ParameterizedTest
    @CsvSource( value={"56512135451220","-4"} )
    void test_setCurrentScore(int score) {
        try{
            dataManager.setCurrentScore(score);
        }
        catch(IllegalArgumentException e){
            assertEquals("The score value is invalid!",e.getMessage());
        }
    }
}