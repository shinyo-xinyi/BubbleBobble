package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.image.Image;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject}'s velocity will update as time goes
 * @see EnemyProjectile
 * @see HeroProjectile
 */
public abstract class ProjectileObject extends UpdatableObject {
    /**
     * the speed of the projectile
     */
    protected static final int SPEED = 12;
    /**
     * the size of the projectile
     */
    protected static final int SIZE = 40;
    /**
     * the active frames
     */
    protected static final int ACTIVE_FRAMES = 200; // Replace magic number with symbolic content
    /**
     * the timer for projectile to be remove away
     */
    protected int timer;
    /**
     * the image for bubble to become transparent
     */
    protected Image transparentImage;

    /**
     * The constructor
     * @param world indicate the position
     * @param x indicates the horizontal coordination
     * @param y indicates the vertical coordination
     * @param  direction indicates the moving direction
     */
    public ProjectileObject(InteractableWorld world, int x, int y, int direction) {
        super(x, y,SIZE, SIZE,world);
        this.direction = direction;
        xVelocity = SPEED;
        yAccel = 0;
        isActive = true;
        timer = ACTIVE_FRAMES; // Replace magic number with symbolic content
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        y += yVelocity;
        x += xVelocity * direction;
        updateVelocity();
        if(y < 25) {
            y = 25;
        }
        if (timer < 0) {
            isActive = false;
        }
        if (timer < -200) {
            markToRemove();
        }
        timer -= 1;
    }
}
