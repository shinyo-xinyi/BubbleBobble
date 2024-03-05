package com.ae2dms.BubbleBobble.UnitState;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitState.InvincibleState} class is a {@link HeroState}
 */
public class InvincibleState implements HeroState{
    protected Hero hero;

    /**
     * The constructor of {@code InvincibleState}
     * @param hero indicates a hero instance
     */
    public InvincibleState(Hero hero){
        this.hero = hero;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shield() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stun() {
    }

    /**
     * {@inheritDoc}
     * translate invincible state to normal state
     * @see Hero#update()
     * @see Hero#stopShield()
     */
    @Override
    public void recover() {
        hero.setState(hero.getNormalState());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invincible() {}
}


