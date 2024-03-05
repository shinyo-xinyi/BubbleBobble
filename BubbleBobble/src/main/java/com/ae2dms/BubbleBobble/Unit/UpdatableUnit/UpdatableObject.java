package com.ae2dms.BubbleBobble.Unit.UpdatableUnit;

import com.ae2dms.BubbleBobble.UnitBehavior.DropCollide;
import com.ae2dms.BubbleBobble.World.GameObject;
import com.ae2dms.BubbleBobble.World.InteractableWorld;

/**
 * Every {@link UpdatableObject} has a velocity, acceleration and direction.
 * @see com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Creature
 * @see com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit
 * @see Bubble
 */
public abstract class UpdatableObject extends GameObject implements Updatable{
    /**
     * velocity in x coordination
     */
    protected double xVelocity;
    /**
     * velocity in y coordination
     */
    protected double yVelocity;
    /**
     * acceleration in x coordination
     */
    protected double xAccel;
    /**
     * acceleration in y coordination
     */
    protected double yAccel;
    /**
     * terminal velocity in x coordination
     */
    protected int terminal_xVelocity;
    /**
     * terminal velocity in y coordination
     */
    protected int terminal_yVelocity;
    /**
     *
     */
    protected static final int TERMINAL_VELOCITY_Y = 5;
    /**
     * whether it need to be remove from the world
     * @see InteractableWorld #remove(GameObject)
     */
    protected boolean canRemove;
    /**
     * @see com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile#isActive
     * @see com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile#isActive
     */
    protected boolean isActive;
    /**
     * the moving direction of the updatable object
     */
    protected int direction;
    
    /**
     * The constructor to initialize the unit
     * @param world indicates where to add the object
     * @param colNum indicates the unit horizontal coordination
     * @param rowNum indicates the unit vertical coordination
     * @param width indicates the horizontal length
     * @param height indicates the vertical length
     */
    public UpdatableObject(InteractableWorld world, int colNum, int rowNum, int width, int height) {
        super(world, colNum, rowNum, width, height);
        xVelocity = 0;
        yVelocity = 0;
        xAccel = 0;
        yAccel = GRAVITY;
        terminal_xVelocity = 0;
        terminal_yVelocity = TERMINAL_FALL_SPEED;
        canRemove = false;
        direction = -1;
    }

    /**
     * The constructor to add a unit into map
     * @param world indicate the position
     * @param x indicates the horizontal coordination
     * @param y indicates the vertical coordination
     * @param width indicates the horizontal length
     * @param height indicates the vertical length
     */
    public UpdatableObject(int x, int y, int width, int height, InteractableWorld world) {
        super(x, y, width, height, world);
        xVelocity = 0;
        yVelocity = 0;
        xAccel = 0;
        yAccel = GRAVITY;
        terminal_xVelocity = 0;
        terminal_yVelocity = TERMINAL_FALL_SPEED;
        canRemove = false;
        direction = -1;
    }

    /**
     * @return whether the object can be removed
     * @see InteractableWorld
     * @see DropCollide#collideWithFloor()
     */
    public boolean isCanRemove() {
        return canRemove;
    }

    /**
     * @param yAccel indicates the acceleration in y coordination
     * @see com.ae2dms.BubbleBobble.UnitBehavior.DropCollide
     */
    public void setyAccel(double yAccel) {
        this.yAccel = yAccel;
    }

    /**
     * @param yVelocity indicates the velocity in y coordination
     */
    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * @return the velocity in y coordination
     */
    public double getyVelocity() {
        return yVelocity;
    }

    /**
     * getter of isActive
     * @return isActive
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * setter of xVelocity
     * @param xVelocity indicates the velocity in x coordination
     */
    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * getter of direction
     * @return the direction of the uodatable object
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * update the updatable object
     */
    @Override
    public void update(){
        if (Math.abs(xVelocity) < terminal_xVelocity) {
            xVelocity += xAccel;
        }
        if (Math.abs(xVelocity) > STATIC_FRICTION) {
            if (xVelocity < 0) {
                xVelocity += 1;
            } else {
                xVelocity -= 1;
            }
            x += xVelocity;
        }
        if (yVelocity < terminal_yVelocity) {
            yVelocity += yAccel;
        }
        y += yVelocity;
        isOffScreen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markToRemove() {
        canRemove = true;
    }

    /**
     * handles isOffScreen problem
     * replace temporary with query :delete xLow,xHigh,yLow,yHigh
     * and move dealing functions out
     * @see #update()
     */
    @Override
    public void isOffScreen() {
        if (x + width < 0) {
        //xLow
            x = (int) world.getCanvas().getWidth();
        } else if (x > world.getCanvas().getWidth()) {
        //xHigh
            x = 0;
        } else if (y + height < 0) {
        //yLow
            y = (int) world.getCanvas().getHeight();
        } else if (y > world.getCanvas().getHeight()) {
        //yHigh
            y = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reverseDirection(){
        xAccel *= -1;
        direction *= -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateVelocity() {
        if (xVelocity > 0) {
            xVelocity -= 0.25;
        } else {
            xVelocity = 0;
        }
        if (Math.abs(yVelocity) < TERMINAL_VELOCITY_Y && !isActive) {
            yVelocity -= 0.1;
        }
    }
}
