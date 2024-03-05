package com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior;

import com.ae2dms.BubbleBobble.Unit.MapUnit.WallUnit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.WallCollide} class is the {@link CollideBehavior} of {@link WallUnit}
 * It extends the {@link MapCollide}
 */
public class WallCollide extends MapCollide{
    /**
     * @see WallUnit
     */
    private WallUnit wallUnit;

    /**
     * The constructor of {@code WallCollide}
     * @param wallUnit indicates a wallUnit instance
     */
    public WallCollide(WallUnit wallUnit) {
        super(wallUnit);
        this.wallUnit = wallUnit;
    }

    /**
     * handles collide with all the {@link UpdatableObject}
     */
    public void collide(UpdatableObject obj){
            double center = obj.getHitbox().getMaxX()-obj.getHitbox().getWidth()/2;
            if (wallUnit.overlaps(obj)) {
                if (center > wallUnit.getHitbox().getMaxX()-wallUnit.getHitbox().getWidth()/2) {
                    wallUnit.moveRightOfUnit(obj);
                    obj.getCollideBehavior().collideWithWall();
                } else if (center < wallUnit.getHitbox().getMaxX()-wallUnit.getHitbox().getWidth()/2){
                    wallUnit.moveLeftOfUnit(obj);
                    obj.getCollideBehavior().collideWithWall();
                } else {
                    wallUnit.moveBelowUnit(obj);
                    obj.getCollideBehavior().collideWithCeiling();
                }
            }
    }
}
