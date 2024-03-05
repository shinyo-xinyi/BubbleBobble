package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature;

import com.ae2dms.BubbleBobble.Controller.GameController;
import com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.HeroCollide;
import com.ae2dms.BubbleBobble.UnitState.*;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import com.ae2dms.BubbleBobble.Database.SoundEffect;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero} is a {@link com.ae2dms.BubbleBobble.World.GameObject} that is controllable by the player.
 * Of all the main.com.ae2dms.BubbleBobble.World.GameObject, only com.ae2dms.BubbleBobble.Map.Beings.Hero has KeyBindings.
 * com.ae2dms.BubbleBobble.Map.Beings.Hero can shoot HeroProjectiles, shield from attacks, trigger a special attack and
 * collect Fruits for points.
 * @see HeroCollide
 */
public class Hero extends Creature{
	/**
	 * the jump speed
	 */
	private static final int JUMP_SPEED = 22;
	/**
	 * the terminal velocity x
	 */
	private static final int TERMINAL_VELOCITY_X = 6;
	/**
	 * the walk speed
	 */
	private static final int WALK_SPEED = 5;
	/**
	 * the running spedd
	 */
	private static final int RUN_SPEED = 10;
	/**
	 * the running acceleration
	 */
	private static final double RUN_ACCEL = 20;
	/**
	 * @see #shieldTimer
	 */
	private static final int SHIELD_TIME = 100;
	/**
	 * @see #stunTimer
	 */
	private static final int STUNNED_TIME = 250; // Replace Magic Number with Symbolic Content
	/**
	 * @see #invincibleTimer
	 */
	private static final int INVINCIBLE_TIME = 300;
	private static final Image stateImage1 = new Image("file:src/main/resources/picture/heroStunned.png");
	private static final Image stateImage2 = new Image("file:src/main/resources/picture/heroInvincible.png");
	private int shootDelay;
	private boolean readyToCharge;
	private double chargeTimer;

	/**
	 * current state of hero
	 * add state pattern to Hero class
	 */
	private HeroState state;
	/**
	 * normal state of hero
	 */
	private HeroState normalState;
	/**
	 * shielding state of hero
	 */
	private HeroState shieldingState;
	/**
	 * stunned state of hero
	 */
	private HeroState stunnedState;
	/**
	 * invincible state of hero
	 */
	private HeroState invincibleState;
	/**
	 * shield Timer
	 */
	private int shieldTimer;
	/**
	 * stun Timer
	 */
	private int stunTimer;
	/**
	 * invincible Timer
	 */
	private int invincibleTimer;

	/**
	 * The constructor to initialize the Hero
	 * @param world indicates where to add the object
	 * @param colNum indicates the unit horizontal coordination
	 * @param rowNum indicates the unit vertical coordination
	 */
	public Hero(InteractableWorld world, int colNum, int rowNum) {
		//initializes hero
		super(world, colNum, rowNum);
		isOnAPlatform = false;

		terminal_xVelocity = TERMINAL_VELOCITY_X;
		jumpSpeed = JUMP_SPEED;

		normalState = new NormalState(this);
		shieldingState = new ShieldingState(this);
		stunnedState = new StunnedState(this);
		invincibleState = new InvincibleState(this);
		state = normalState; // initialize the HeroState to be NormalState

		shieldTimer = SHIELD_TIME;
		stunTimer = STUNNED_TIME;//Replace Magic Number with Symbolic Content
		invincibleTimer = INVINCIBLE_TIME;
		chargeTimer =0;
		shootDelay = 0;
		readyToCharge = false;
		collideBehavior = new HeroCollide(this);
	}

	/**
	 * getter of charge timer
	 * @return chargeTimer
	 */
	public double getChargeTimer() {
		return chargeTimer;
	}

	/**
	 * getter of state
	 * @return current state of Hero
	 */
	public HeroState getState() {
		return state;
	}

	/**
	 * setter of state
	 * @param state indicates the state of the hero
	 * @see HeroState
	 */
	public void setState(HeroState state) {
		this.state = state;
	}

	/**
	 * getter of normalState
	 * @return normalState
	 * @see NormalState
	 */
	public HeroState getNormalState() {
		return normalState;
	}

	/**
	 * getter of shieldingState
	 * @return shieldingState
	 * @see ShieldingState
	 */
	public HeroState getShieldingState() {
		return shieldingState;
	}

	/**
	 * getter of stunnedState
	 * @return stunnedState
	 * @see StunnedState
	 */
	public HeroState getStunnedState() {
		return stunnedState;
	}

	/**
	 * getter of invincibleState
	 * @return invincibleState
	 * @see InvincibleState
	 */
	public HeroState getInvincibleState() {
		return invincibleState;
	}

	/**
	 * draws hero
	 * @param g indicates the canvas in the world
	 */
	@Override
	public void drawOn(GraphicsContext g) {
		if (state==stunnedState) {
			g.drawImage(stateImage1,x,y,SIZE,SIZE);
		} else if (state==invincibleState) {
			g.drawImage(stateImage2,x,y,SIZE,SIZE);
		}else{
			g.drawImage(world.getDataManager().getHeroImage(), x, y, SIZE, SIZE);
			if (state==shieldingState) {
				g.setFill(new Color(0, (shieldTimer * ((double)1 / SHIELD_TIME)), (shieldTimer * ((double)1/ SHIELD_TIME)), 0.19));
				g.fillOval(x - 10, y - 10, SIZE+20, SIZE+20);
			}
		}
	}

	/**
	 * updates position of hero, according to many variables
	 * including whether the hero is shielding,
	 * or if the hero is stunned
	 * @see InteractableWorld
	 */
	@Override
	public void update() {
		super.update();
		if (state==shieldingState) {
			shieldTimer -= 1;
			if (shieldTimer <= 0) {
				shieldTimer = 0;
				state.stun(); // state strategy
			}
		} else {
			if (shieldTimer < SHIELD_TIME && state!=stunnedState) {
				shieldTimer += 1;
			}
		}
		if (state==stunnedState) {
			stunTimer -= 1;
			if (stunTimer <= 0) {
				state.recover(); // state strategy
				stunTimer = STUNNED_TIME; // replace magic number with symbolic content
				shieldTimer = SHIELD_TIME;
			}
		}
		if (state==invincibleState) {
			invincibleTimer -= 1;
			if (invincibleTimer <= 0) {
				state.recover();
				invincibleTimer = INVINCIBLE_TIME;
			}
		}
		chargeTimer++;
		if(chargeTimer ==500){
			readyToCharge=true;
		}
	}

	/**
	 * handles death
	 * @see HeroCollide#collideWithMook()
	 * @see HeroCollide#collideWithProjectile()
	 */
	@Override
	public void die() {
		SoundEffect.DEATH.setToLoud();
		SoundEffect.DEATH.play();
		SoundEffect.DEATH.setToLoop();
		world.getDataManager().setLives(world.getDataManager().getLives()-1);
		chargeTimer = 0;
		state.invincible();
		//world.markToReset();
	}

	/**
	 * handles jumping
	 * @see #toJump()
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
	 * makes hero shoot projectile
	 * @see #toShoot()
	 */
	@Override
	public void shootProjectile() {
		SoundEffect.SHOOT.play();
		world.getUnitFactory().addUnit("HeroProjectile", x, y, direction);
	}

	/**
	 * gets whether the hero is shielding on this frame and returns it
	 * @return true or false
	 */
	public boolean getShielding() {
		return state==shieldingState;
	}

	/**
	 * control hero to stop moving
	 * @see GameController
	 */
	public void toMoveRight() {
		if (state == normalState || state == invincibleState) {
			xAccel = RUN_ACCEL;
			direction = 1;
		}
	}

	/**
	 * control hero to move left
	 * @see GameController
	 */
	public void toMoveLeft() {
		if (state == normalState || state == invincibleState) {
			xAccel = -RUN_ACCEL;
			direction = -1;
		}
	}

	/**
	 * control hero to jump
	 * @see GameController
	 */
	public void toJump() {
		if (state==normalState||state==invincibleState) {
			jump();
			SoundEffect.JUMP.play();
		}
	}

	/**
	 * control hero to dash
	 * @see GameController
	 */
	public void toDash() {
		terminal_xVelocity = RUN_SPEED;
	}

	/**
	 * control hero to shoot
	 * @see GameController
	 */
	public void toShoot() {
		if (state==normalState) {
			shootDelay -= 1;
			if (shootDelay <= 0) {
				shootProjectile();
				shootDelay = 2;
			}
		}
	}

	/**
	 * control hero to stop shield
	 * @see GameController
	 */
	public void toShield() {
		if (state != stunnedState) {
			xVelocity = 0;
			xAccel = 0;
			state.shield();
		}
	}

	/**
	 * control hero to charge
	 * @see GameController
	 */
	public void toCharge() {
		if (readyToCharge) {
			world.getUnitFactory().addUnit("Bubble", x, y);
			//SoundEffect.EXPLODE.setToLoud();
			SoundEffect.EXPLODE.play();
			readyToCharge = false;
			chargeTimer = 0;
		}
	}

	/**
	 * control hero to stop moving
	 * @see GameController
	 */
	public void stopMove() {
		xAccel = 0;
	}

	/**
	 * control hero to stop dash
	 * @see GameController
	 */
	public void stopDash() {
		terminal_xVelocity = WALK_SPEED;
	}

	/**
	 * control hero to stop shoot
	 * @see GameController
	 */
	public void stopShoot() {
		shootDelay = 0;
	}

	/**
	 * control hero to stop shield
	 * @see GameController
	 */
	public void stopShield() {
		if(state==shieldingState) {
			state.recover();
		}
	}
}