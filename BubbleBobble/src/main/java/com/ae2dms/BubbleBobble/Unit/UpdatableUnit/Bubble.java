package com.ae2dms.BubbleBobble.Unit.UpdatableUnit;

import com.ae2dms.BubbleBobble.UnitBehavior.BubbleCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Bubble} class extends {@link UpdatableObject},
 * and it handles everything with the {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero}'s
 * special ability, named the bubble.
 * <p>
 * It begins at the hero, and grows covering the whole screen.
 * Once it collides with an enemy, that enemy is bubbled.
 * </p>
 * @see BubbleCollide
 */
public class Bubble extends UpdatableObject {
	private int accel;

	/**
	 * The constructor
	 * @param world indicates where to add the object
	 * @param x indicates the horizontal coordination
	 * @param y indicates the vertical coordination
	 */
	public Bubble(InteractableWorld world, int x, int y) {
		super(x, y, 0, 0, world);
		accel = 1;
		this.setCollideBehavior(new BubbleCollide(this));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		if (width >= 2500) {
			markToRemove();
		}
		x -= accel / 2;
		y -= accel / 2;
		width += accel;
		height += accel;
		accel += 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawOn(GraphicsContext g) {
		if (width <= 2500) {
			g.setFill(new Color(1, 0.8, 0.4, 1 - width *(1/(double)2500)));
		} else {
			g.setFill(new Color(1, 0.8, 0.4, 0));
		}
		g.fillOval(x, y, width, height);
		g.setFill(Color.BLACK);
	}
}
