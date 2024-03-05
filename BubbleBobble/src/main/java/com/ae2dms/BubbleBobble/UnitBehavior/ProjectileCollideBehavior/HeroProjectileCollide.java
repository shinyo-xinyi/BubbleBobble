package com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior.HeroProjectileCollide} class is the {@link CollideBehavior} of {@link HeroProjectile}
 */
public class HeroProjectileCollide extends ProjectileCollide{
    /**
     * @see ProjectileObject
     */
    ProjectileObject heroProjectile;

    /**
     * The constructor of {@code ProjectileCollide}
     * @param heroProjectile indicates a projectile instance
     */
    public HeroProjectileCollide(ProjectileObject heroProjectile) {
        super(heroProjectile);
        this.heroProjectile = heroProjectile;
    }

    /**
     * handles collide with {@link Enemy}
     */
    @Override
    public void collideWith(Enemy enemy) {
        if (heroProjectile.overlaps(enemy) && heroProjectile.isActive()) {
            enemy.getCollideBehavior().collideWithProjectile();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithBoss() {
        heroProjectile.markToRemove();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithMook() {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithStar() {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithProjectile() {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(HeroProjectile heroProjectile) {

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(Hero hero) {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(MapObject obj){

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(EnemyProjectile enemyPrjectile) {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(Fruit fruit) {

    }
}