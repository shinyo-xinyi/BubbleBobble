package com.ae2dms.BubbleBobble.Unit.SpecialUnit;

import com.ae2dms.BubbleBobble.UnitBehavior.DoorCollide;
import com.ae2dms.BubbleBobble.World.GameObject;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.SpecialUnit.DoorUnit} is a special {@link GameObject}.
 * <p>
 * Only by defeating all enemies on the map, the portal will open.
 * The hero will be transported to the next level by approaching an open portal.
 * </p>
 * @see DoorCollide
 */
public class DoorUnit extends GameObject {
    private boolean isOpen;
    private static final Image close_door = new Image("file:src/main/resources/picture/door_close.png");
    private static final Image open_door = new Image("file:src/main/resources/picture/door_open.png");
    protected static final int SIZE = 40;

    /**
     * The constructor
     * @param world indicates where to add the object
     * @param colNum indicates the unit horizontal coordination
     * @param rowNum indicates the unit vertical coordination
     */
    public DoorUnit(InteractableWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum,SIZE,SIZE);
        isOpen=false;
        image = close_door;
        collideBehavior = new DoorCollide(this);
    }

    /**
     * changes the close door to be an open door when all the enemies in current level died
     * @see InteractableWorld #updateData()
     */
    public void openDoor() {
        isOpen=true;
        image = open_door;
    }

    /**
     * getter of the world
     * @return world
     */
    public InteractableWorld getworld(){
        return world;
    }

    /**
     * getter of isOpen
     * @return isOpen
     */
    public boolean isOpen(){
        return isOpen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawOn(GraphicsContext g) {
        g.drawImage(image,x,y,width,height);
    }
}
