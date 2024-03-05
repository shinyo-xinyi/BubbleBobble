package com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.CeilingUnit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.CeilingCollide} class is the {@link CollideBehavior} of {@link CeilingUnit}
 * It extends the {@link MapCollide}
 */
public class CeilingCollide extends MapCollide{
    private CeilingUnit ceilingUnit;

    /**
     * The constructor of {@code CeilingCollide}
     * @param ceilingUnit indicates a ceilingUnit instance
     */
    public CeilingCollide(CeilingUnit ceilingUnit) {
        super(ceilingUnit);
        this.ceilingUnit = ceilingUnit;
    }

    /**
     * handles collide with all the {@link UpdatableObject}
     */
    public void collide(UpdatableObject obj){
        if (ceilingUnit.overlaps(obj)) {
            ceilingUnit.moveBelowUnit(obj);
            obj.getCollideBehavior().collideWithCeiling();
        }
    }
}
