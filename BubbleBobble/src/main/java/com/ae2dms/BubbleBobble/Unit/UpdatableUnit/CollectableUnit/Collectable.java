package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Collectable} interface is
 * the ability for {@link Fruit} to be collected by {@link Hero}.
 * @see Fruit
 */
public interface Collectable{
    /**
     * The method to activate the readyToCollect attribute
     * @param readyToCollect indicates whether it can be collected by hero
     */
    void setReadyToCollect(boolean readyToCollect);

    /**
     * The method to show what would happen when it is collected by hero
     * @param hero indicates the instance of {@link Hero}
     */
    void collideEffect(Hero hero);
}
