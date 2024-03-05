package com.ae2dms.BubbleBobble.Factory;

import com.ae2dms.BubbleBobble.Main;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Potion;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Star;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Treasure;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Boss;
import com.ae2dms.BubbleBobble.Unit.SpecialUnit.DoorUnit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Bubble;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.Unit.MapUnit.CeilingUnit;
import com.ae2dms.BubbleBobble.Unit.MapUnit.FloorUnit;
import com.ae2dms.BubbleBobble.Unit.MapUnit.WallUnit;
import com.ae2dms.BubbleBobble.World.InteractableWorld;

/**
 * {@link com.ae2dms.BubbleBobble.Factory.UnitFactoryImpl} uses factory method pattern
 * it add {@link com.ae2dms.BubbleBobble.World.GameObject} into {@link com.ae2dms.BubbleBobble.World.InteractableWorld}
 */
public class UnitFactoryImpl implements UnitFactory{
    /**
     * he possibility for fruit to become potion
     */
    private static final double DROP_POTION_CHANCE = 0.2;
    /**
     * where to add units
     */
    private final InteractableWorld world;

    /**
     * @see InteractableWorld#getUnitFactory()
     * @param world indicates where to add the unit
     */
    public UnitFactoryImpl(InteractableWorld world) {
        this.world = world;
    }

    /**
     * {@inheritDoc}
     */
     @Override
     public void addUnit(String type,int colNum, int rowNum) {
            if (colNum<0||rowNum<0){
                throw new IllegalArgumentException("The position is invalid!");
            }
            if(type.equals("CeilingUnit")) {
                world.getCeilingUnits().add(new CeilingUnit(world, colNum, rowNum));
            }
            else if(type.equals("FloorUnit"))
                world.getFloorUnits().add(new FloorUnit(world,colNum,rowNum));
            else if(type.equals("WallUnit"))
                world.getWallUnits().add(new WallUnit(world,colNum,rowNum));
            else if(type.equals("Hero")) {
                world.getHeroes().add(new Hero(world,colNum,rowNum));
            }
            else if(type.equals("Enemy"))
                world.getEnemies().add(new Enemy(world,colNum,rowNum));
            else if(type.equals("Fruit")) {
                Fruit fruit = new Fruit(world, colNum, rowNum);
                if(Math.random() < DROP_POTION_CHANCE){
                    fruit = new Potion(fruit,world,colNum,rowNum); // the fruit is decorated to be potion
                }
                world.getFruits().add(fruit);
            }
            else if(type.equals("Bubble"))
                world.getBubbles().add(new Bubble(world, colNum, rowNum));
            else if(type.equals("DoorUnit"))
                world.getDoors().add(new DoorUnit(world, colNum, rowNum));
            else if(type.equals("Boss"))
                world.getEnemies().add(new Boss(world, colNum, rowNum));
            else if(type.equals("Star"))
                world.getFruits().add(new Star(new Fruit(world, colNum, rowNum),world, colNum, rowNum));
            else if(type.equals("Treasure"))
                world.getFruits().add(new Treasure(new Fruit(world, colNum, rowNum),world, colNum* Main.UNIT_SIZE, rowNum* Main.UNIT_SIZE));
            else
                throw new IllegalArgumentException("The type is invalid!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUnit(String type,int x, int y, int direction) {
        if (x<0||y<0){
            throw new IllegalArgumentException("The position is invalid!");
        }
        if(direction!=1&&direction!=-1){
            throw new IllegalArgumentException("The direction value is invalid!");
        }
        else if (type.equals("HeroProjectile"))
            world.getHeroProjectiles().add(new HeroProjectile(world, x, y, direction));
        else if (type.equals("EnemyProjectile"))
            world.getEnemyProjectiles().add(new EnemyProjectile(world, x, y, direction));
        else
            throw new IllegalArgumentException("The type is invalid!");
    }
}
