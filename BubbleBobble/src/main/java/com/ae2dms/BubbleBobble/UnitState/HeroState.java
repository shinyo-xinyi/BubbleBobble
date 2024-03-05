package com.ae2dms.BubbleBobble.UnitState;

/**
 * The {@code HeroState} interface uses state pattern.
 * <p>
 * It shows the state of {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero},
 * and contains four state transition methods.
 * </p>
 * @see InvincibleState
 * @see NormalState
 * @see ShieldingState
 * @see StunnedState
 */

public interface HeroState {
    /**
     * translates current state into shield state
     */
    void shield();

    /**
     * translates current state into stunned state
     */
    void stun();

    /**
     * translates current state into normal state
     */
    void recover();

    /**
     * translates current state into invincible state
     */
    void invincible();

    /**
     * a literal representation of the current state.
     */
    @Override
    String toString();
}
