package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject;

/**
 * The {@code Alive} interface means the ability for {@link Creature}
 * to jump,die and shoot projectile
 */
public interface Alive {
    /**
     * handles die
     */
    void die();

    /**
     * handles jump
     */
    void jump();

    /**
     * shoot a {@link ProjectileObject}
     */
    void shootProjectile();
}
