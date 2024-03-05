package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.UnitBehavior.DropCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;

/**
 * The {@code Treasure} class extends {@link DropDecorator}.
 * <p>
 * It is a special kind of {@link Fruit} which could which add extra 80 scores.
 * </p>
 * @see DropCollide
 */
public class Treasure extends DropDecorator{

    /**
     * constructor
     * @param fruit indicates its original type
     * @param world indicate the position
     * @param x indicates the horizontal coordination
     * @param y indicates the vertical coordination
     */
    public Treasure(Fruit fruit, InteractableWorld world, int x, int y) {
        super(world,x,y);
        this.fruit=fruit;
        image = new Image("file:src/main/resources/picture/treasure.gif");
    }

    /**
     * Collide effect of Treasure
     */
    @Override
    public void collideEffect(Hero hero){
        world.getDataManager().setCurrentScore(world.getDataManager().getCurrentScore()+80);// the score is added by extra 80
    }
}
