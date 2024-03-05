package com.ae2dms.BubbleBobble.UnitState;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.HeroCollide;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitState.StunnedState} class is a {@link HeroState}
 */
public class StunnedState implements HeroState{
    protected Hero hero;

    /**
     * The constructor of {@code StunnedState}
     * @param hero indicates a hero instance
     */
    public StunnedState(Hero hero){
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
    public void stun() { }

    /**
     * {@inheritDoc}
     * @see Hero#die()
     * @see HeroCollide#collideWithStar()
     */
    @Override
    public void invincible() {
        hero.setState(hero.getInvincibleState());
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
        return "StunnedState";
    }
}