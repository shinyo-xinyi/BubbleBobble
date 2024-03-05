package com.ae2dms.BubbleBobble.Unit.MapUnit;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.MapUnit.MoveObject} interface means the ability for map objects
 * to move updatable objects to a new position
 * @see MapObject
 */
public interface MoveObject {
    /**
     * move the unit above
     * @param obj indicates the unit instance
     */
    void moveAboveUnit(UpdatableObject obj);

    /**
     * move the unit below
     * @param obj indicates the unit instance
     */
    void moveBelowUnit(UpdatableObject obj);

    /**
     * move the unit to the left
     * @param obj indicates the unit instance
     */
    void moveLeftOfUnit(UpdatableObject obj);

    /**
     * move the unit to the right
     * @param obj indicates the unit instance
     */
    void moveRightOfUnit(UpdatableObject obj);
}
