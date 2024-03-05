package com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior;

import com.ae2dms.BubbleBobble.Database.SoundEffect;
import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.HeroCollide} is a {@link CollideBehavior} of {@link Hero}
 */
public class HeroCollide extends CreatureCollide {
    /**
     * @see Hero
     */
    private Hero hero;
    /**
     * The constructor of {@code HeroCollide}
     * @param hero indicates a hero instance
     */
    public HeroCollide(Hero hero) {
        super(hero);
        this.hero = hero;
    }

    /**
     * handles colliding with a mook
     */
    @Override
    public void collideWithMook() {
        if (hero.getState()!=hero.getShieldingState()&&hero.getState()!=hero.getInvincibleState()) {
            hero.die();
        }
    }

    /**
     * handles colliding with a star
     */
    @Override
    public void collideWithStar() {
        if (hero.getState()!=hero.getInvincibleState()) {
            hero.getState().invincible();
        }
    }

    /**
     * handles collision with projectiles
     */
    @Override
    public void collideWithProjectile() {
        if (hero.getState()!=hero.getShieldingState()&&hero.getState()!=hero.getInvincibleState()) {
            hero.die();
        }
    }

    /**
     * handles collision with floor
     */
    @Override
    public void collideWithFloor() {
        hero.setyVelocity(0);
        if (!hero.isOnAPlatform()) {
            hero.setOnAPlatform(true);
            SoundEffect.LAND.play();
        }
    }

    @Override
    public void collideWith(HeroProjectile heroProjectile) {

    }

    @Override
    public void collideWith(Enemy enemy) {

    }

    @Override
    public void collideWithBoss() {
    }

    @Override
    public void collideWith(Hero hero) {
    }

    @Override
    public void collideWith(MapObject obj) {
    }

    @Override
    public void collideWith(EnemyProjectile enemyPrjectile) {

    }

    @Override
    public void collideWith(Fruit fruit) {
    }
}
