package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.UnitBehavior.DropCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;

/**
 * The {@code Potion} class extends {@link DropDecorator}.
 * <p>
 * It is a special kind of {@link Fruit} which could recover 1 life of hero.
 * </p>
 * @see DropCollide
 */
public class Potion extends DropDecorator{
    /**
     * constructor
     * @param fruit indicates its original type
     * @param world indicate the position
     * @param x indicates the horizontal coordination
     * @param y indicates the vertical coordination
     */
    public Potion(Fruit fruit,InteractableWorld world, int x, int y) {
        super(world,x,y);
        this.fruit=fruit;
        image = new Image("file:src/main/resources/picture/potion.png");
    }

    /**
     * Collide effect of Potion
     */
    @Override
    public void collideEffect(Hero hero){
        fruit.collideEffect(hero);
        if(world.getDataManager().getLives()<5) {
            world.getDataManager().setLives(world.getDataManager().getLives() + 1);
        }
    }
}
