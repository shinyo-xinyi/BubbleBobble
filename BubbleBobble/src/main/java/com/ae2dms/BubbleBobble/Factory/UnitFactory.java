package com.ae2dms.BubbleBobble.Factory;

/**
 * The {@link com.ae2dms.BubbleBobble.Factory.UnitFactory} interface uses factory method pattern
 * It adds {@link com.ae2dms.BubbleBobble.World.GameObject} into {@link com.ae2dms.BubbleBobble.World.InteractableWorld}
 * @see UnitFactoryImpl
 */
public interface UnitFactory {
    /**
     * create units that do not have initial direction
     * @param type indicates what kind of Unit will be added into the world
     * @param colNum indicates the unit horizontal coordination
     * @param rowNum indicates the unit vertical coordination
     */
    void addUnit(String type,int colNum, int rowNum);

    /**
     * create units that have initial direction
     * @param type indicates what kind of directional unit will be added into the world
     * @param x indicates the horizontal coordination
     * @param y indicates the vertical coordination
     * @param direction indicates the direction of the unit
     */
    void addUnit(String type,int x, int y, int direction);
}
