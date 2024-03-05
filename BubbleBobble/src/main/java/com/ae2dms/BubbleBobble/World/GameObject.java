package com.ae2dms.BubbleBobble.World;
import com.ae2dms.BubbleBobble.Main;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * {@link GameObject} are the objects on the {@link InteractableWorld} screen.
 * Every {@link GameObject} has a position and dimensions.
 * GameObjects can detect if they are overlapping another {@link GameObject} and
 * must have a {@link CollideBehavior}.
 */
public abstract class GameObject{
	/**
	 * static friction
	 */
	protected static final double STATIC_FRICTION = 0.1;
	/**
	 * simulate gravity in real world
	 * @see com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject#yAccel
	 */
	protected static final double GRAVITY = 0.98; // 1 -> 0.98
	/**
	 * terminal fall speed
	 */
	protected static final int TERMINAL_FALL_SPEED = 20;
	protected Image image;
	protected CollideBehavior collideBehavior; // strategy pattern
	protected InteractableWorld world;
	protected int x, y;
	protected int width, height;

	/**
	 * sets collide behavior for the object with other objects
	 * @param collideBehavior indicates what will happen when it collides other objects
	 */
	public void setCollideBehavior(CollideBehavior collideBehavior) {
		this.collideBehavior = collideBehavior;
	}

	/**
	 * gets collide behavior for the object
	 * @return collide behavior
	 */
	public CollideBehavior getCollideBehavior() {
		return collideBehavior;
	}

	/**
	 * The constructor to initialize a unit on map
	 * @param world indicates in which canvas to draw the game object
	 * @param colNum indicates the unit horizontal coordination of the game object
	 * @param rowNum indicates the unit vertical coordination of the game object
 	 * @param width indicates the horizontal length of the game object
	 * @param height indicates the vertical dimension of the game object
	 */
	public GameObject(InteractableWorld world, int colNum, int rowNum, int width, int height) {
		this.world = world;
		x = colNum * Main.UNIT_SIZE;
		y = rowNum * Main.UNIT_SIZE;
		this.width = width;
		this.height = height;
	}

	/**
	 * The constructor to add a unit into map
	 * @param world indicate the position of the game object
	 * @param x indicates the horizontal coordination of the game object
	 * @param y indicates the vertical coordination of the game object
	 * @param width indicates the horizontal length of the game object
	 * @param height indicates the vertical dimension of the game object
	 */
	public GameObject(int x, int y, int width, int height, InteractableWorld world) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.world = world;
	}

	/**
	 * The method to draw the object on the canvas
	 * @param g indicates the canvas in the world
	 */
    public abstract void drawOn(GraphicsContext g);

	/**
	 * @return x coordinate of upper left corner
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return y coordinate of upper left corner
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @return height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * sets hitbox for each game object
	 * @see com.ae2dms.BubbleBobble.Unit.MapUnit.WallUnit
	 * @return hitbox of the object
	 */
	public Rectangle2D getHitbox(){
		return new Rectangle2D(x, y, width, height);
	}

	/**
	 * checks if two objects overlap or collide
	 * @param obj indicates the object instance
	 * @return whether two objects overlaps
	 */
	public boolean overlaps(GameObject obj) {
		return getHitbox().intersects(obj.getHitbox());
	}

	/**
	 * moves object to a point
	 * @param point indicates the position
	 * @see com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject
	 */
	public void moveTo(Point2D point) {
		x = (int) point.getX();
		y = (int) point.getY();
	}
}
