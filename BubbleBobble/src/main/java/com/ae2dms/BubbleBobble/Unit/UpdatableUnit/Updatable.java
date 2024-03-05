package com.ae2dms.BubbleBobble.Unit.UpdatableUnit;

import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.World.InteractableWorld;

/**
 * The {@code com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Updatable} interface means the ability
 * for {@link UpdatableObject} to update its attributes
 */
public interface Updatable {
    /**
     * general update method of every updatable game object
     */
    void update();
    /**
     * sets whether the object can be removed
     */
    void markToRemove();
    /**
     * deal with off-screen problem
     */
    void isOffScreen();
    /**
     * reverses game object's direction
     */
    void reverseDirection();
    /**
     * updates game object's velocity
     */
    void updateVelocity();
}
