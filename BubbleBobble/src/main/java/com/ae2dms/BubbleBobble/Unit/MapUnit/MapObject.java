package com.ae2dms.BubbleBobble.Unit.MapUnit;

import com.ae2dms.BubbleBobble.Main;
import com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.MapCollide;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import com.ae2dms.BubbleBobble.World.GameObject;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject} class extends {@link GameObject}, which implements {@link MoveObject} interface.
 * @see CeilingUnit
 * @see FloorUnit
 * @see WallUnit
 */
public abstract class MapObject extends GameObject implements MoveObject{
    /**
     * The constructor
     * @param world indicates where to add the object
     * @param colNum indicates the unit horizontal coordination
     * @param rowNum indicates the unit vertical coordination
     */
    public MapObject(InteractableWorld world, int colNum, int rowNum){
        super(world, colNum, rowNum, Main.UNIT_SIZE,Main.UNIT_SIZE);
        this.setCollideBehavior(new MapCollide(this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(GraphicsContext g) {
        g.drawImage(image,x,y,width,height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveAboveUnit(UpdatableObject obj) {
        obj.moveTo(new Point2D(obj.getX(), y - obj.getHeight()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveBelowUnit(UpdatableObject obj) {
        obj.moveTo(new Point2D(obj.getX(), y + height));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void moveLeftOfUnit(UpdatableObject obj) {
        obj.moveTo(new Point2D(x - obj.getWidth(), obj.getY()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveRightOfUnit(UpdatableObject obj) {
        obj.moveTo(new Point2D(x + width, obj.getY()));
    }
}
