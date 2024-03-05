package com.ae2dms.BubbleBobble.Unit.MapUnit;

import com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.WallCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.MapUnit.WallUnit} class extends{@link MapObject}.
 * It creates wall units to be used for the world.
 * <p>
 * A wall unit is an unit shaped like a square that is treated as a wall,
 * with collision on all four sides.
 * The wall collides with any kind of game object.
 * </p>
 * @see WallCollide
 */
public class WallUnit extends MapObject {
	/**
	 * The constructor
	 * @param world indicates where to add the object
	 * @param colNum indicates the unit horizontal coordination
	 * @param rowNum indicates the unit vertical coordination
	 */
	public WallUnit(InteractableWorld world, int colNum, int rowNum) {
		super(world,colNum,rowNum);
		image = new Image("file:src/main/resources/picture/wall.png");
		this.setCollideBehavior(new WallCollide(this));
	}
}
