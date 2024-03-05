package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.UnitBehavior.DropCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;

/**
 * The {@code Star} class extends {@link DropDecorator}.
 * <p>
 * It is a special kind of {@link Fruit} which could make hero become invincible.
 * </p>
 * @see DropCollide
 */
public class Star extends DropDecorator{
    /**
     * constructor
     * @param fruit indicates its original type
     * @param world indicate the position
     * @param x indicates the horizontal coordination
     * @param y indicates the vertical coordination
     */
    public Star(Fruit fruit, InteractableWorld world, int x, int y) {
        super(world,x,y);
        this.fruit=fruit;
        image = new Image("file:src/main/resources/picture/star.png");
    }

    /**
     * Collide effect of Star
     */
    @Override
    public void collideEffect(Hero hero){
        hero.getCollideBehavior().collideWithStar();
    }
}
