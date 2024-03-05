package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile;

import com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior.HeroProjectileCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile} class
 * extends {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject},
 * and it handles the specificities with the projectile being shot from a hero.
 * For example, the hero's projectile has a different color than the projectile of an enemy.
 * It also can only hurt an enemy.
 */
public class HeroProjectile extends ProjectileObject {

	/**
	 * The constructor
	 * @param world indicate the position
	 * @param x indicates the horizontal coordination
	 * @param y indicates the vertical coordination
	 * @param  direction indicates the moving direction
	 */
	public HeroProjectile(InteractableWorld world, int x, int y, int direction) {
		super(world,x,y,direction);
		image = new Image("file:src/main/resources/picture/heroProjectile1.png");
		transparentImage = new Image("file:src/main/resources/picture/heroProjectile2.png");
		collideBehavior = new HeroProjectileCollide(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawOn(GraphicsContext g) {
		if (isActive) {
			g.drawImage(image,x,y,SIZE,SIZE);
		} else {
			g.drawImage(transparentImage,x,y,SIZE,SIZE);
		}
	}
}
