package com.ae2dms.BubbleBobble.Unit.MapUnit;

import com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.FloorCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.MapUnit.FloorUnit} class extends{@link MapObject}.
 * It creates floor units to be used for the world.
 * <p>
 * A floor unit is a unit shaped like a square that is treated as a floor,
 * with collision on the top, left, and right sides.
 * The floor collides with any kind of game object.
 * When an enemy is bubbled, the enemy will still be stopped by a floor unit above it.
 * </p>
 * @see FloorCollide
 */
public class FloorUnit extends MapObject {
	/**
	 * The constructor
	 * @param world indicates where to add the object
	 * @param colNum indicates the unit horizontal coordination
	 * @param rowNum indicates the unit vertical coordination
	 */
	public FloorUnit(InteractableWorld world, int colNum, int rowNum) {
		super(world, colNum, rowNum);
		image = new Image("file:src/main/resources/picture/floor.png");
		this.setCollideBehavior(new FloorCollide(this));
	}
}
