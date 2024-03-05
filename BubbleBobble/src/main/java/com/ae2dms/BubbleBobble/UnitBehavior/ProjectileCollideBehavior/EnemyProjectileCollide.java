package com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;
import com.ae2dms.BubbleBobble.World.InteractableWorld;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior.EnemyProjectileCollide} class is the {@link CollideBehavior} of {@link EnemyProjectile}
 */
public class EnemyProjectileCollide extends ProjectileCollide{
    /**
     * @see ProjectileObject
     */
    ProjectileObject enemyProjectile;

    /**
     * The constructor of {@code ProjectileCollide}
     * @param enemyProjectile indicates a projectile instance
     */
    public EnemyProjectileCollide(ProjectileObject enemyProjectile) {
        super(enemyProjectile);
        this.enemyProjectile = enemyProjectile;
    }

    /**
     * handles collide with {@link Hero}
     * @see InteractableWorld #initialCollisions()
     */
    public void collideWith(Hero hero) {
        if (enemyProjectile.overlaps(hero) && enemyProjectile.isActive()) {
            hero.getCollideBehavior().collideWithProjectile();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(MapObject obj) {

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
    public void collideWith(Enemy enemy) {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithBoss() {

    }
}
