package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit;

import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import com.ae2dms.BubbleBobble.UnitBehavior.DropCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import com.ae2dms.BubbleBobble.Database.SoundEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit} class extends {@link UpdatableObject} and implements {@link Collectable} interface.
 * <p>
 * It handles how the fruit is created and interacts with the hero.
 * The fruits are created after a bubble containing an enemy is popped.
 * When it is collected by hero, the player will gain 20 scores.
 * </p>
 * @see DropCollide
 */
public class Fruit extends UpdatableObject implements Collectable{
	/**
	 * the size of the fruit is 40
	 */
	protected static final int SIZE = 40;
	/**
	 * The terminal velocity in y coordination
	 */
	protected static final int TERMINAL_VELOCITY_Y = 10;
	/**
	 * the ability whether it could be collected
	 */
	protected boolean readyToCollect;
	/**
	 * the score for each fruit
	 */
	protected static final int score=20;
	/**
	 * the image type of the fruit
	 */
	private int type;

	/**
	 * constructor
	 * @param world indicate the position
	 * @param x indicates the horizontal coordination
	 * @param y indicates the vertical coordination
	 */
	public Fruit(InteractableWorld world,int x, int y) {
		super(x, y, SIZE, SIZE, world);
		terminal_yVelocity = TERMINAL_VELOCITY_Y;
		readyToCollect = false;
		this.setCollideBehavior(new DropCollide(this));
		type= (int) (Math.random()*10+1); // generate fruit randomly
		image = new Image("file:src/main/resources/picture/fruits/fruit"+ type +".png");
		collideBehavior = new DropCollide(this);
	}

	/**
	 * getter of readyToCollect
	 * @return readyToCollect
	 */
	public boolean isReadyToCollect() {
		return readyToCollect;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawOn(GraphicsContext g) {
		g.drawImage(image,x,y,SIZE,SIZE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setReadyToCollect(boolean readyToCollect) {
		this.readyToCollect = readyToCollect;
	}

	/**
	 * Collide effect of Fruit
	 */
	@Override
	public void collideEffect(Hero hero){
		SoundEffect.FRUIT.setToLoud();
		SoundEffect.FRUIT.play();
		SoundEffect.FRUIT.setToLoop();
		world.getDataManager().setCurrentScore(world.getDataManager().getCurrentScore()+score);// the score is added by 20
	}
}
