package com.ae2dms.BubbleBobble;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.HeroCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollideBehaviorTest {
    private static InteractableWorld world;
    private static Hero hero;
    private static int colNum;
    private static int rowNum;

    @BeforeEach
    void setUp(){
        world = new InteractableWorld(Main.UNIT_SIZE*Main.WIDTH,Main.UNIT_SIZE*Main.HEIGHT);
        colNum = (int) (Math.random()*Main.WIDTH);
        rowNum = (int) (Math.random()*Main.HEIGHT);
        hero = new Hero(world,colNum,rowNum);
        hero.setCollideBehavior(new HeroCollide(hero));
    }
    @Test
    void test_collideWithFloor() {
        hero.getCollideBehavior().collideWithFloor();
        assertEquals(0,hero.getyVelocity());
    }

    @Test
    void test_collideWithCeiling() {
        hero.getCollideBehavior().collideWithCeiling();
        assertEquals(0,hero.getyVelocity());
    }

    @Test
    void test_collideWithWall() {
        int direction = hero.getDirection();
        hero.getCollideBehavior().collideWithCeiling();
        assertEquals(direction*(-1),hero.getDirection());
    }
}