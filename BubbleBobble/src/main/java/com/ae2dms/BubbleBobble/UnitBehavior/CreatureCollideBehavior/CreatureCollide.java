package com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Creature;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.CreatureCollide} is a {@link CollideBehavior} of {@link Creature}
 * @see HeroCollide
 * @see EnemyCollide
 */
public abstract class CreatureCollide implements CollideBehavior{
    /**
     * @see Creature
     */
    private Creature creature;

    /**
     * The constructor of {@code CreatureCollide}
     * @param creature indicates a creature instance
     */
    public CreatureCollide(Creature creature) {
        this.creature = creature;
    }

    /**
     * handles collision with floor
     */
    @Override
    public void collideWithFloor() {
        creature.setyVelocity(0);
        if (!creature.isOnAPlatform()) {
            creature.setOnAPlatform(true);
        }
    }

    /**
     * handles ceiling collision values
     */
    @Override
    public void collideWithCeiling() {
        creature.setyVelocity(0);
    }

    /**
     * handles what to do on collision with a wall
     */
    @Override
    public void collideWithWall() {
        creature.reverseDirection();
    }
}
