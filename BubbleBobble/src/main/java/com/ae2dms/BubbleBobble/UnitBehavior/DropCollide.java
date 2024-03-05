package com.ae2dms.BubbleBobble.UnitBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.DropCollide} is a {@link CollideBehavior} of {@link Fruit}
 */
public class DropCollide implements CollideBehavior{
    private Fruit fruit;

    /**
     * The constructor of {@code DropCollide}
     * @param fruit indicates a fruit instance
     */
    public DropCollide(Fruit fruit){
        this.fruit = fruit;
    }

    /**
     * handles collide with {@link com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.FloorCollide}
     */
    @Override
    public void collideWithFloor() {
        fruit.setyAccel(0);
        if (!fruit.isCanRemove()) {
            fruit.setReadyToCollect(true);
        }
    }
    /**
     * handles collide with {@link Hero}
     */
    @Override
    public void collideWith(Hero hero) {
        if (fruit.overlaps(hero) && fruit.isReadyToCollect()){
            fruit.setReadyToCollect(false);
            fruit.markToRemove();
            fruit.collideEffect(hero);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithCeiling() {
        // Nothing happens
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithWall() {
        // Nothing happens
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
