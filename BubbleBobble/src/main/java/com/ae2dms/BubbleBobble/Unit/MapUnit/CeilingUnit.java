package com.ae2dms.BubbleBobble.Unit.MapUnit;

import com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.CeilingCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.MapUnit.CeilingUnit} class extends{@link MapObject}.
 * It creates ceiling units to be used for the world.
 * <p>
 * A ceiling unit is a unit shaped like a square that is treated as a ceiling, with collision on all four sides.
 * The ceiling collides with any kind of game object.
 * Even if a game object is on top of a ceiling, the game object will be pushed down.
 * </p>
 * @see CeilingCollide
 */
public class CeilingUnit extends MapObject {
	/**
	 * The constructor
	 * @param world indicates where to add the object
	 * @param colNum indicates the unit horizontal coordination
	 * @param rowNum indicates the unit vertical coordination
	 */
	public CeilingUnit(InteractableWorld world, int colNum, int rowNum) {
		super(world,colNum,rowNum);
		image = new Image("file:src/main/resources/picture/wall.png");
		this.setCollideBehavior(new CeilingCollide((this)));
	}
}
