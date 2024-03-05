package com.ae2dms.BubbleBobble.UnitState;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitState.ShieldingState} class is a {@link HeroState}
 */
public class ShieldingState implements HeroState{
    protected Hero hero;

    /**
     * The constructor of {@code ShieldingState}
     * @param hero indicates a hero instance
     */
    public ShieldingState(Hero hero){
        this.hero = hero;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shield() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void invincible() {}

    /**
     * {@inheritDoc}
     * @see Hero#update()
     */
    @Override
    public void stun() {
        hero.setState(hero.getStunnedState());
    }

    /**
     * {@inheritDoc}
     * @see Hero#stopShield()
     * @see Hero#update()
     */
    @Override
    public void recover() {
        hero.setState(hero.getNormalState());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        return "ShieldingState";
    }
}