package com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.FloorUnit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.FloorCollide} class is the {@link CollideBehavior} of {@link FloorUnit}
 * It extends the {@link MapCollide}
 */
public class FloorCollide extends MapCollide{
    /**
     * @see FloorUnit
     */
    private FloorUnit floorUnit;

    /**
     * The constructor of {@code CeilingCollide}
     * @param floorUnit indicates CeilingUnit instance
     */
    public FloorCollide(FloorUnit floorUnit) {
        super(floorUnit);
        this.floorUnit = floorUnit;
    }

    /**
     * handles collide with all the {@link UpdatableObject}
     */
    public void collide(UpdatableObject obj){
        double top = obj.getY();
        double bottom = top + obj.getHeight();
        if (floorUnit.overlaps(obj) && obj.getyVelocity() > 0) {
            if (bottom < floorUnit.getY() + floorUnit.getHeight()) {
                floorUnit.moveAboveUnit(obj);
                obj.getCollideBehavior().collideWithFloor();
            }
            if (top > floorUnit.getY()) {
                floorUnit.moveBelowUnit(obj);
                obj.getCollideBehavior().collideWithCeiling();
            }
        }
    }
}
