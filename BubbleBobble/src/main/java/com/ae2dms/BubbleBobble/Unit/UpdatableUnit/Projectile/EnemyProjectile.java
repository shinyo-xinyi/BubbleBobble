package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile;

import com.ae2dms.BubbleBobble.UnitBehavior.ProjectileCollideBehavior.EnemyProjectileCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile}
 * extends {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject},
 * and it handles the specificities with the projectile being shot from an enemy.
 * For example, the enemy's projectile has a different color than the projectile of a hero.
 * It also can only hurt a hero.
 */
public class EnemyProjectile extends ProjectileObject {

	/**
	 * The constructor
	 * @param world indicate the position
	 * @param x indicates the horizontal coordination
	 * @param y indicates the vertical coordination
	 * @param  direction indicates the moving direction
	 */
	public EnemyProjectile(InteractableWorld world, int x, int y, int direction) {
		super(world,x,y,direction);
		image = new Image("file:src/main/resources/picture/poison1.png");
		transparentImage = new Image("file:src/main/resources/picture/poison2.png");
		collideBehavior = new EnemyProjectileCollide(this);
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
