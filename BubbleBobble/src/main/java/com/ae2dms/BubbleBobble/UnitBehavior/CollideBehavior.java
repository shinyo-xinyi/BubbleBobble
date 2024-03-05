package com.ae2dms.BubbleBobble.UnitBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.CreatureCollide;
import com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior.ProjectileCollide;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior} interface uses strategy pattern.
 * <p>
 * It contains collide behaviours with different kinds of {@link com.ae2dms.BubbleBobble.World.GameObject}
 * for all the {@link com.ae2dms.BubbleBobble.World.GameObject}.
 * </p>
 * @see CreatureCollide
 * @see DropCollide
 * @see BubbleCollide
 * @see ProjectileCollide
 */
public interface CollideBehavior {
    /**
     * Collide behavior with {@link com.ae2dms.BubbleBobble.Unit.MapUnit.FloorUnit}
     */
    void collideWithFloor();

    /**
     * Collide behavior with {@link com.ae2dms.BubbleBobble.Unit.MapUnit.CeilingUnit}
     */
    void collideWithCeiling();

    /**
     * Collide behavior with {@link com.ae2dms.BubbleBobble.Unit.MapUnit.WallUnit}
     */
    void collideWithWall();

    /**
     * handles colliding with a mook
     * @see com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.EnemyCollide
     */
    void collideWithMook();

    /**
     * handles colliding with {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Star}
     */
    void collideWithStar();

    /**
     * handles colliding with {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject}
     */
    void collideWithProjectile();

    /**
     * handles colliding with {@link HeroProjectile}
     */
    void collideWith(HeroProjectile heroProjectile);

    /**
     * handles colliding with {@link Enemy}
     */
    void collideWith(Enemy enemy);

    /**
     * handles colliding with {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Boss}
     */
    void collideWithBoss();

    /**
     * handles colliding with {@link Hero}
     */
    void collideWith(Hero hero);

    /**
     * handles colliding with {@link MapObject}
     * @see com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.MapCollide
     */
    void collideWith(MapObject obj);

    /**
     * handles colliding with {@link EnemyProjectile}
     */
    void collideWith(EnemyProjectile enemyPrjectile);

    /**
     * handles colliding with {@link Fruit}
     */
    void collideWith(Fruit fruit);
}