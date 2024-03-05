package com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior;

import com.ae2dms.BubbleBobble.Database.SoundEffect;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Boss;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.BossCollide} is a {@link CollideBehavior} of {@link Boss}
 */
public class BossCollide extends EnemyCollide{
    /**
     * @see Boss
     */
    private Boss boss;

    /**
     * The constructor of {@code BossCollide}
     * @param boss indicates a boss instance
     */
    public BossCollide(Boss boss) {
        super(boss);
        this.boss = boss;
    }

    /**
     * handles collide with hero projectile
     */
    @Override
    public void collideWith(HeroProjectile heroProjectile) {
        if (boss.overlaps(heroProjectile)) {
            heroProjectile.getCollideBehavior().collideWithBoss();
            boss.setLives(boss.getLives()-1);
        }
    }

    /**
     * the bubble is no use to the boss
     */
    @Override
    public void collideWithProjectile() {
    }
}