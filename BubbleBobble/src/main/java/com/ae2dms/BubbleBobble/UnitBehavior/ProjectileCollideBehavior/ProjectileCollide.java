package com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior.ProjectileCollide} class is a abstract {@link CollideBehavior} of {@link ProjectileObject}
 * @see HeroProjectileCollide
 * @see EnemyProjectileCollide
 */
public abstract class ProjectileCollide implements CollideBehavior{
    /**
     * @see ProjectileObject
     */
    ProjectileObject projectile;

    /**
     * The constructor of {@code ProjectileCollide}
     * @param projectile indicates a projectile instance
     */
    public ProjectileCollide(ProjectileObject projectile) {
        this.projectile = projectile;
    }

    /**
     * handles collide with {@link com.ae2dms.BubbleBobble.Unit.MapUnit.FloorUnit}
     */
    @Override
    public void collideWithFloor() {
        projectile.reverseDirection();
    }

    /**
     * handles collide with {@link com.ae2dms.BubbleBobble.Unit.MapUnit.CeilingUnit}
     */
    @Override
    public void collideWithCeiling() {
        projectile.setyVelocity(0);
    }

    /**
     * handles collide with {@link com.ae2dms.BubbleBobble.Unit.MapUnit.WallUnit}
     */
    @Override
    public void collideWithWall() {
        projectile.reverseDirection();
    }
}