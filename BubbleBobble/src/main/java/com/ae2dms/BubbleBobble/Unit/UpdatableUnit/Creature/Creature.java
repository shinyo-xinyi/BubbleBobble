package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;

/**
 * The {@link Creature} class extends {@link UpdatableObject}, which implements {@link Alive} interface.
 * It is the superclass of {@link Enemy} and {@link Hero}
 * Every {@link Creature} has a jumpSpeed, can shoot and will die.
 * @see Enemy
 * @see Hero
 * @see com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.CreatureCollide
 */
public abstract class Creature extends UpdatableObject implements Alive {
    protected boolean isOnAPlatform;
    protected double jumpSpeed;
    protected static final int SIZE = 40;
    protected Image reverseImage;

    /**
     * The constructor to initialize the Creature
     * @param world indicates where to add the object
     * @param colNum indicates the unit horizontal coordination
     * @param rowNum indicates the unit vertical coordination
     */
    public Creature(InteractableWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum, SIZE, SIZE);
    }

    /**
     * @return isOnAPlatform
     */
    public boolean isOnAPlatform() {
        return isOnAPlatform;
    }

    /**
     * @param onAPlatform indicates whether is on a platform
     */
    public void setOnAPlatform(boolean onAPlatform) {
        isOnAPlatform = onAPlatform;
    }

    /**
     * {@inheritDoc}
     */
    public abstract void die();

    /**
     * {@inheritDoc}
     */
    public abstract void jump();

    /**
     * {@inheritDoc}
     */
    public abstract void shootProjectile();
}
