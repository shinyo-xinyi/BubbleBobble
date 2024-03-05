package com.ae2dms.BubbleBobble;

import com.ae2dms.BubbleBobble.Factory.UnitFactoryImpl;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Bubble;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class UnitFactoryImplTest{
        private static InteractableWorld world;
        private UnitFactoryImpl unitFactory;
        private static ArrayList<Bubble> bubbles;
        private static int colNum;
        private static int rowNum;

        @BeforeEach
        void setUp(){
            world = new InteractableWorld(Main.UNIT_SIZE*Main.WIDTH,Main.UNIT_SIZE*Main.HEIGHT);
            colNum = (int) (Math.random()*Main.WIDTH);
            rowNum = (int) (Math.random()*Main.HEIGHT);
            unitFactory= new UnitFactoryImpl(world);
        }

        @Test
        @DisplayName("Test add bubble unit")
        @ParameterizedTest
        @CsvSource( value={"Bubble"} )
        void test_addUnit(String type){
            unitFactory.addUnit(type,colNum,rowNum);
            assertTrue(world.getBubbles().get(0) instanceof Bubble);
        }

        @Test
        @DisplayName("Test invalid direction")
        @ParameterizedTest
        @CsvSource( value={"6","0"} )
        void test_addUnit_IllegalDirection(int direction){
            try{
                unitFactory.addUnit("Hero",colNum,rowNum,direction);
            }
            catch(IllegalArgumentException e){
                assertEquals("The direction value is invalid!",e.getMessage());
            }
        }

        @Test
        @DisplayName("Test invalid unit type")
        @ParameterizedTest
        @CsvSource( value={"Projectile"} )
        void test_addUnit_IllegalType(String type){
            try{
                unitFactory.addUnit("Projectile",colNum,rowNum);
            }
            catch(IllegalArgumentException e){
                assertEquals("The type is invalid!",e.getMessage());
            }
        }

        @Test
        @DisplayName("Test invalid position")
        @ParameterizedTest
        @CsvSource( value={"-5"} )
        void test_addUnit_IllegalColNum(int colNum){
            try{
                unitFactory.addUnit("Enemy",colNum,rowNum);
            }
            catch(IllegalArgumentException e){
                assertEquals("The position is invalid!",e.getMessage());
            }
        }

        @Test
        @DisplayName("Test invalid position")
        @ParameterizedTest
        @CsvSource( value={"-1"} )
        void test_addUnit_IllegalrowNum(int rowNum){
            try{
                unitFactory.addUnit("Enemy",colNum,rowNum);
            }
            catch(IllegalArgumentException e){
                assertEquals("The position is invalid!",e.getMessage());
            }
        }
}