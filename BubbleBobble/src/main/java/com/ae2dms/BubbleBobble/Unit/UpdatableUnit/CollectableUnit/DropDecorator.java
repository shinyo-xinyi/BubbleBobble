package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit;

import com.ae2dms.BubbleBobble.World.InteractableWorld;

/**
 * The {@code DropDecorator} class use decorator pattern to decorate {@link Fruit}.
 * @see Potion
 * @see Treasure
 * @see Star
 */
public abstract class DropDecorator extends Fruit{
    /**
     * @see Fruit
     */
    Fruit fruit;

    /**
     * constructor
     * @param world indicate the position
     * @param x indicates the horizontal coordination
     * @param y indicates the vertical coordination
     */
    public DropDecorator(InteractableWorld world, int x, int y) {
        super(world, x, y);
    }
}
