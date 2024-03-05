package com.ae2dms.BubbleBobble.UnitState;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.HeroCollide;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitState.NormalState} class is a {@link HeroState}
 */
public class NormalState implements HeroState{
    protected Hero hero;

    /**
     * The constructor of {@code NormalState}
     * @param hero indicates a hero instance
     */
    public NormalState(Hero hero){
        this.hero = hero;
    }

    /**
     * {@inheritDoc}
     * @see Hero#toShield()
     */
    @Override
    public void shield() {
        hero.setState(hero.getShieldingState());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stun() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void recover() {}

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
     */
    @Override
    public String toString(){
        return "NormalState";
    }
}
