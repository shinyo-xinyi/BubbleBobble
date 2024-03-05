package com.ae2dms.BubbleBobble.UnitBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Bubble;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.BubbleCollide} is a {@link CollideBehavior} of {@link Bubble}
 */
public class BubbleCollide implements CollideBehavior{
    Bubble bubble;

    /**
     * The constructor of {@code BubbleCollide}
     * @param bubble indicates a bubble instance
     */
    public BubbleCollide(Bubble bubble) {
        this.bubble = bubble;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithFloor() {
        //nothing happens
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithCeiling() {
        //nothing happens
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithWall() {
        //nothing happens
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
        if (bubble.overlaps(enemy)) {
            enemy.getCollideBehavior().collideWithProjectile();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithBoss() {

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
}
