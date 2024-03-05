package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature;

import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.EnemyCollide;
import com.ae2dms.BubbleBobble.World.GameObject;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import com.ae2dms.BubbleBobble.Database.SoundEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * A {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy} is a non-controllable
 * {@link GameObject} that kills the {@link Hero} whenever it or its projectile comes in contact.
 * <p>
 * Enemies are able to be bubbled and free themselves from these bubbles after a period of time.
 * Enemies change direction at random intervals, when hitting a wall, and when hitting the {@link Hero}'s shield.
 * Enemies jump at random intervals as well.
 * </p>
 * @see EnemyCollide
 */
public class Enemy extends Creature {
	public static final int JUMP_SPEED = 20;
	public static final int TERMINAL_VELOCITY_X = 4;
	public static final int BUBBLED_FRAMES = 300;
	public static final double CHANGE_MOVEMENT_CHANCE = 0.01;
	public static final double CHANGE_SHOOT_CHANCE =0.002;
	public static final int SIZE = 30;
	private int type; // the type of the enemy
	protected boolean isBubbled;
	protected int timer;
	protected int pointValue;
	protected boolean turningAwayFromShield;
	protected int turningAwayCount;

	/**
	 * The constructor to initialize the Enemy
	 * @param world indicates where to add the object
	 * @param colNum indicates the unit horizontal coordination
	 * @param rowNum indicates the unit vertical coordination
	 */
	public Enemy(InteractableWorld world, int colNum, int rowNum) {
		super(world, colNum, rowNum);
		isOnAPlatform = false;
		jumpSpeed = JUMP_SPEED;
		terminal_xVelocity = TERMINAL_VELOCITY_X;
		
		xAccel = 1.5;
		direction = 1;
		if (Math.random() < 0.5) {
			reverseDirection();
		}

		isBubbled = false;
		timer = BUBBLED_FRAMES;
		pointValue = 150;
		turningAwayFromShield = false;
		turningAwayCount = 10;

		type= (int) (Math.random()*8+1);
		image = new Image("file:src/main/resources/picture/enemies/left"+String.valueOf(type)+".gif");
		reverseImage = new Image("file:src/main/resources/picture/enemies/right"+String.valueOf(type)+".gif");
		collideBehavior = new EnemyCollide(this);
	}

	/**
	 * draws mook
	 */
	@Override
	public void drawOn(GraphicsContext g) {
		if (direction==-1) {
			g.drawImage(image, x, y, SIZE, SIZE);
		}
		else {
			g.drawImage(reverseImage, x, y, SIZE, SIZE);
		}

		if (isBubbled) {
			g.setFill(new Color(0, 0.5, 0.9, timer*((double)1/300)));
			g.fillOval(x - 5, y - 5, SIZE+10, SIZE+10);
		}
	}

	/**
	 * updates enemy, handling movement
	 * @see InteractableWorld
	 */
	@Override
	public void update() {
		super.update();
		if (isBubbled) {
			timer -= 1;
			if (timer <= 0) {
				isBubbled = false;
				timer = BUBBLED_FRAMES;
				xAccel = 1.5;
				direction = 1;
				if (Math.random() < 0.5) {
					reverseDirection();
				}
				yAccel = GameObject.GRAVITY;
			}
		} else {
			if (Math.random() < CHANGE_MOVEMENT_CHANCE) {
				jump();
			}
			if (Math.random() < CHANGE_MOVEMENT_CHANCE) {
				reverseDirection();
			}
			if (Math.random() < CHANGE_SHOOT_CHANCE) {
				shootProjectile();
			}
		}
	}

	/**
	 * handles what to do on death
	 * @see EnemyCollide#collideWith(Hero)
	 */
	@Override
	public void die(){
		world.getUnitFactory().addUnit("Fruit",x, y); // using Factory Method pattern
		markToRemove();
	}

	/**
	 * handles jumping
	 * @see #update()
	 */
	@Override
	public void jump() {
		if (isOnAPlatform) {
			y -= 1;
			yVelocity = -jumpSpeed;
			isOnAPlatform = false;
		}
	}

	/**
	 * handles shooting projectile
	 * @see #update()
	 */
	@Override
	public void shootProjectile() {
		SoundEffect.SHOOT.play();
		world.getUnitFactory().addUnit("EnemyProjectile", x, y, direction);
	}

	/**
	 * getter of turningAwayCount
	 * @return turningAwayCount
	 * @see EnemyCollide#collideWith(Hero)
	 */
	public int getTurningAwayCount() {
		return turningAwayCount;
	}

	/**
	 * setter of turningAwayCount
	 * @param turningAwayCount indicates the count of turning away
	 * @see EnemyCollide#collideWith(Hero)
	 */
	public void setTurningAwayCount(int turningAwayCount) {
		this.turningAwayCount = turningAwayCount;
	}

	/**
	 * getter of isBubbled
	 * @return isBubbled
	 * @see EnemyCollide#collideWith(MapObject)
	 * @see EnemyCollide#collideWithProjectile()
	 * @see EnemyCollide#collideWith(Hero)
	 */
	public boolean isBubbled() {
		return isBubbled;
	}

	/**
	 * setter of enemy
	 * @param bubbled indicates that the enemy is bubbled
	 * @see EnemyCollide#collideWithProjectile()
	 */
	public void setBubbled(boolean bubbled) {
		isBubbled = bubbled;
	}

	/**
	 * getter of turningAwayFromShield
	 * @return turningAwayFromShield
	 * @see EnemyCollide#collideWith(Hero)
	 */
	public boolean isTurningAwayFromShield() {
		return turningAwayFromShield;
	}

	/**
	 * setter of turningAwayFromShield
	 * @param turningAwayFromShield indicates the enemy collides with shielding hero
	 * @see EnemyCollide#collideWith(Hero)
	 */
	public void setTurningAwayFromShield(boolean turningAwayFromShield) {
		this.turningAwayFromShield = turningAwayFromShield;
	}
}

