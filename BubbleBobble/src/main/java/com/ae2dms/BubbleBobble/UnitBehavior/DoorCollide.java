package com.ae2dms.BubbleBobble.UnitBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.SpecialUnit.DoorUnit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Bubble;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.DoorCollide} is a {@link CollideBehavior} of {@link DoorUnit}
 */
public class DoorCollide implements CollideBehavior{

    private DoorUnit doorUnit;

    /**
     * The constructor of {@code CreatureCollide}
     * @param doorUnit indicates a creature instance
     */
    public DoorCollide(DoorUnit doorUnit) {
        this.doorUnit = doorUnit;
    }

    /**
     * dealing with collision with hero
     * @param hero indicates a hero instance
     */
    public void collideWith(Hero hero) {
        if (doorUnit.overlaps(hero) && doorUnit.isOpen()) {
            doorUnit.getworld().getDataManager().nextLevel();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithFloor() {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithCeiling() {

    }

    @Override
    public void collideWithWall() {

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
