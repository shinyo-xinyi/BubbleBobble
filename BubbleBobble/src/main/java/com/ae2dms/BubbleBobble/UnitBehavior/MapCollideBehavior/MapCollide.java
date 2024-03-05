package com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.CeilingUnit;
import com.ae2dms.BubbleBobble.Unit.MapUnit.FloorUnit;
import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.MapUnit.WallUnit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.MapCollide} is a {@link CollideBehavior} of {@link MapObject}
 * @see FloorCollide
 * @see WallCollide
 * @see CeilingCollide
 */
public class MapCollide implements CollideBehavior{
    /**
     * @see MapObject
     */
    private MapObject mapObject;

    /**
     * The constructor of {@code MapCollide}
     * @param mapObject indicates a map object instance (wall/ceiling/floor)
     */
    public MapCollide(MapObject mapObject) {
        this.mapObject = mapObject;
    }

    /**
     * handles collide with all the {@link UpdatableObject}
     * @see CeilingCollide#collide(UpdatableObject)
     * @see FloorCollide#collide(UpdatableObject)
     * @see WallCollide#collide(UpdatableObject)
     */
    public void collide(UpdatableObject obj){
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(EnemyProjectile enemyPrjectile) {
        collide(enemyPrjectile);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(HeroProjectile heroProjectile) {
        collide(heroProjectile);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(Enemy enemy) {
        collide(enemy);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(Hero hero) {
        collide(hero);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(Fruit fruit) {
        collide(fruit);
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
        //nothing happens
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithStar() {
        //nothing happens
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithProjectile() {
        //nothing happens
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithBoss() {
        //nothing happens
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(MapObject obj) {
        //nothing happens
    }
}
