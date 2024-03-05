package com.ae2dms.BubbleBobble.World;

import com.ae2dms.BubbleBobble.Controller.GameController;
import com.ae2dms.BubbleBobble.Database.ChargeTime;
import com.ae2dms.BubbleBobble.Database.DataManager;
import com.ae2dms.BubbleBobble.Database.RandomDrop;
import com.ae2dms.BubbleBobble.Unit.SpecialUnit.DoorUnit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Bubble;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.Main;
import com.ae2dms.BubbleBobble.Unit.MapUnit.CeilingUnit;
import com.ae2dms.BubbleBobble.Unit.MapUnit.FloorUnit;
import com.ae2dms.BubbleBobble.Unit.MapUnit.WallUnit;
import com.ae2dms.BubbleBobble.Factory.UnitFactory;
import com.ae2dms.BubbleBobble.Factory.UnitFactoryImpl;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@link InteractableWorld} handles all of the game's operations:
 * updating positions, checking for collisions, and removing objects.
 * @see GameController
 */
public class InteractableWorld{
	/**
	 * the ceilingUnits in the world
	 */
	private ArrayList<CeilingUnit> ceilingUnits;
	/**
	 * the floorUnits in the world
	 */
	private ArrayList<FloorUnit> floorUnits;
	/**
	 * the wallUnits in the world
	 */
	private ArrayList<WallUnit> wallUnits;
	/**
	 * the heroes in the world
	 */
	private ArrayList<Hero> heroes;
	/**
	 * the enemies in the world
	 */
	private ArrayList<Enemy> enemies;
	/**
	 * the heroProjectiles in the world
	 */
	private ArrayList<HeroProjectile> heroProjectiles;
	/**
	 * the enemyProjectiles in the world
	 */
	private ArrayList<EnemyProjectile> enemyProjectiles;
	/**
	 * the fruits in the world
	 */
	private ArrayList<Fruit> fruits;
	/**
	 * the game objects need to be removed
	 * @see #removeObjects()
	 */
	private ArrayList<GameObject> toBeRemoved;
	/**
	 * the bubbles in the world
	 */
	private ArrayList<Bubble> bubbles;
	/**
	 * the doorUnits in the world
	 */
	private ArrayList<DoorUnit> doorUnits;
	/**
	 * randomDrop task to set fruit periodically in the map in level 3
	 * @see DataManager#nextLevel()
	 */
	private RandomDrop randomDrop;
	/**
	 * the unitFactory to add objects into world
	 */
	private UnitFactory unitFactory;
	/**
	 * the dataManager to deal with the data in world
	 */
	private DataManager dataManager;
	/**
	 * the chargeTime for {@link Hero} to set {@link Bubble}
	 * it is bind with {@link GameController #progressBar}
	 */
	private ChargeTime chargeTime;
	/**
	 * the canvas to draw the objects in panel
	 * @see GameController
	 */
	private Canvas canvas;
	/**
	 * whether the world is ready to reset
	 */
	private boolean readyToReset;

	/**
	 * The constructor of {@code InteractableWorld} to initializes the interactableworld
	 * @param width indicates the horizontal length
	 * @param height indicates the vertical length
	 */
	public InteractableWorld(int width,int height) {
		ceilingUnits = new ArrayList<>();
		floorUnits = new ArrayList<>();
		wallUnits = new ArrayList<>();
		heroes = new ArrayList<>();
		enemies = new ArrayList<>();
		heroProjectiles = new ArrayList<>();
		enemyProjectiles = new ArrayList<>();
		fruits = new ArrayList<>();
		doorUnits = new ArrayList<>();
		toBeRemoved = new ArrayList<>();
		bubbles = new ArrayList<>();
		readyToReset = false;
		DataManager.init(this);
		dataManager = DataManager.getInstance();
		unitFactory = new UnitFactoryImpl(this);
		canvas = new Canvas(width, height);
		randomDrop = new RandomDrop("randomDrop");
		randomDrop.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent t) {
				unitFactory.addUnit("Fruit", (int)(Math.random()*width), (int)(Math.random()*height));
				randomDrop.restart();
			}
		});
		chargeTime = new ChargeTime(0);
	}

	/**
	 * gets heroProjectiles
	 * @return heroProjectiles
	 */
	public ArrayList<HeroProjectile> getHeroProjectiles() {
		return heroProjectiles;
	}

	/**
	 * gets enemyProjectiles
	 * @return enemyProjectiles
	 */
	public ArrayList<EnemyProjectile> getEnemyProjectiles() {
		return enemyProjectiles;
	}

	/**
	 * gets fruits
	 * @return fruits
	 */
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}

	/**
	 * gets bubbles
	 * @return bubbles
	 */
	public ArrayList<Bubble> getBubbles() {
		return bubbles;
	}

	/**
	 * gets ceilingUnits
	 * @return ceilingUnits
	 */
	public ArrayList<CeilingUnit> getCeilingUnits() {
		return ceilingUnits;
	}

	/**
	 * gets floorUnits
	 * @return floorUnits
	 */
	public ArrayList<FloorUnit> getFloorUnits() {
		return floorUnits;
	}

	/**
	 * gets wallUnits
	 * @return wallUnits
	 */
	public ArrayList<WallUnit> getWallUnits() {
		return wallUnits;
	}

	/**
	 * gets heroes
	 * @return heroes
	 */
	public ArrayList<Hero> getHeroes() {
		return heroes;
	}

	/**
	 * gets enemies
	 * @return enemies
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * gets doorUnits
	 * @return doorUnits
	 */
	public ArrayList<DoorUnit> getDoors() {
		return doorUnits;
	}

	/**
	 * gets chargeTime
	 * @return chargeTime
	 */
	public ChargeTime getChargeTime() {
		return chargeTime;
	}

	/**
	 * gets dataManager
	 * @return dataManager
	 */
	public DataManager getDataManager() {
		return dataManager;
	}

	/**
	 * gets unitFactory
	 * @return unitFactory
	 */
	public UnitFactory getUnitFactory() {
		return unitFactory;
	}

	/**
	 * set the task to randomly generate fruits on map
	 * @see DataManager#nextLevel()
	 */
	public void setRandomDrop() {
		randomDrop.start();
	}

	/**
	 * stop the task to randomly generate fruits on map
	 * @see #updateData()
	 */
	public void stopRandomDrop() {
		randomDrop.cancel();
	}

	/**
	 * gets canvas
	 * @return canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * sets boolean to make sure the world is ready to be reset
	 */
	public void markToReset() {
		readyToReset = true;
	}

	/**
	 * add all the units to the world
	 * @throws IOException when input is null
	 */
	public void startGame() throws IOException{
		InputStream input = InteractableWorld.class.getResourceAsStream("/" + "world/World"+String.valueOf(dataManager.getLevel())+".txt");
		if(input==null){
			throw new IOException("The input is invalid!");
		}
		Scanner scanner = new Scanner(input);
		clearContents();
		for (int row = 0; row < Main.HEIGHT; row++) {
			String currentLine = scanner.next();
			for (int col = 0; col < Main.WIDTH; col++) {
				if (currentLine.charAt(col) == '*') {
					unitFactory.addUnit("FloorUnit", col, row);
				} else if (currentLine.charAt(col) == 'H') {
					unitFactory.addUnit("Hero", col, row);
				} else if (currentLine.charAt(col) == '|') {
					unitFactory.addUnit("WallUnit", col, row);
				} else if (currentLine.charAt(col) == '_') {
					unitFactory.addUnit("CeilingUnit", col, row);
				} else if (currentLine.charAt(col) == 'M') {
					unitFactory.addUnit("Enemy", col, row);
				} else if (currentLine.charAt(col) == 'D') {
					unitFactory.addUnit("DoorUnit", col, row);
				}else if (currentLine.charAt(col) == 'B') {
					unitFactory.addUnit("Boss", col, row);
				}else if (currentLine.charAt(col) == 'T') {
					unitFactory.addUnit("Treasure", col, row);
				}
			}
		}
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		}
		scanner.close();
		readyToReset = false;
	}

	/**
	 * The method to paints everything on the world
	 * @param g indicates the context of canvas
	 */
	public void paintComponent(GraphicsContext g) {
		g.clearRect(0,0, Main.UNIT_SIZE* Main.WIDTH,Main.UNIT_SIZE* Main.HEIGHT);// clear the whole canvas

		for (CeilingUnit ceilingUnit : ceilingUnits) {
			ceilingUnit.drawOn(g);
		}
		for (FloorUnit floorUnit : floorUnits) {
			floorUnit.drawOn(g);
		}
		for (WallUnit wallUnit : wallUnits) {
			wallUnit.drawOn(g);
		}
		for (DoorUnit doorUnit : doorUnits) {
			doorUnit.drawOn(g);
		}
		for (Hero hero : heroes) {
			hero.drawOn(g);
		}
		for (Enemy enemy : enemies) {
			enemy.drawOn(g);
		}
		for (EnemyProjectile enemyProjectile : enemyProjectiles) {
			enemyProjectile.drawOn(g);
		}
		for (HeroProjectile heroProjectile : heroProjectiles) {
			heroProjectile.drawOn(g);
		}
		for (Fruit fruit : fruits) {
			fruit.drawOn(g);
		}
		for (Bubble bubble : bubbles) {
			bubble.drawOn(g);
		}

	}

	/**
	 * The method to clears everything from the screen
	 * @see InteractableWorld#startGame()
	 */
	private void clearContents() {
		ceilingUnits.removeAll(ceilingUnits);
		floorUnits.removeAll(floorUnits);
		wallUnits.removeAll(wallUnits);
		heroes.removeAll(heroes);
		enemies.removeAll(enemies);
		enemyProjectiles.removeAll(enemyProjectiles);
		heroProjectiles.removeAll(heroProjectiles);
		fruits.removeAll(fruits);
		doorUnits.removeAll(doorUnits);
	}

	/**
	 * The method to handle all the changes in the world
	 */
	public void updateWorld(){
		updatePosition();
		initiateCollisions();
		removeObjects();
		updateData();
	}

	/**
	 * The method to update positions of updatable objects on screen
	 * @see UpdatableObject#update()
	 */
	private void updatePosition(){
		for (Hero hero : heroes) {
			hero.update();
		}
		for (Enemy enemy : enemies) {
			enemy.update();
			if(enemy.isCanRemove()) {
				toBeRemoved.add(enemy);
			}
		}
		for (EnemyProjectile enemyProjectile : enemyProjectiles) {
			enemyProjectile.update();
			if (enemyProjectile.isCanRemove()) {
				toBeRemoved.add(enemyProjectile);
			}
		}
		for (HeroProjectile heroProjectile : heroProjectiles) {
			heroProjectile.update();
			if (heroProjectile.isCanRemove()) {
				toBeRemoved.add(heroProjectile);
			}
		}
		for (Fruit fruit : fruits) {
			fruit.update();
			if (fruit.isCanRemove()) {
				toBeRemoved.add(fruit);
			}
		}
		for (Bubble bubble : bubbles) {
			bubble.update();
			if (bubble.isCanRemove()) {
				toBeRemoved.add(bubble);
			}
		}
	}

	/**
	 * The method to initiate collision states of units
	 * @see com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior
	 */
	private void initiateCollisions(){
		mapUnitCollisions();
		specialUnitCollisions();
		creatureCollisions();
		projectileCollisions();
	}

	/**
	 * Projectiles initiate collisions with Heroes and Enemies
	 */
	private void projectileCollisions() {
		for (HeroProjectile heroProjectile : heroProjectiles) {
			for (Hero hero : heroes) {
				heroProjectile.getCollideBehavior().collideWith(hero);
			}
			for (Enemy enemy : enemies) {
				heroProjectile.getCollideBehavior().collideWith(enemy);
			}
		}
		for (EnemyProjectile enemyProjectile  : enemyProjectiles) {
			for (Hero hero : heroes) {
				enemyProjectile.getCollideBehavior().collideWith(hero);
			}
			for (Enemy enemy : enemies) {
				enemyProjectile.getCollideBehavior().collideWith(enemy);
			}
		}
	}

	/**
	 * Enemies initiate collisions with Heroes
	 */
	private void creatureCollisions() {
		for (Enemy enemy : enemies) {
			for (Hero hero : heroes) {
				enemy.getCollideBehavior().collideWith(hero);
			}
			for (HeroProjectile heroProjectile : heroProjectiles) {
				enemy.getCollideBehavior().collideWith(heroProjectile);
			}
		}
	}

	/**
	 * Fruits,Bubbles,Doors initiate collisions
	 */
	private void specialUnitCollisions() {
		for (Fruit fruit : fruits) {
			for (Hero hero : heroes) {
				fruit.getCollideBehavior().collideWith(hero);
			}
		}
		for (Bubble bubble : bubbles) {
			for (Enemy enemy : enemies) {
				bubble.getCollideBehavior().collideWith(enemy);
			}
		}
		for (DoorUnit doorUnit : doorUnits) {
			for (Hero hero : heroes) {
				doorUnit.getCollideBehavior().collideWith(hero);
			}
		}
	}

	/**
	 * MapUnits initiate collisions with Heroes, Enemies, and Fruits
	 */
	private void mapUnitCollisions() {
		for (CeilingUnit ceilingUnit : ceilingUnits) {
			for (Hero hero : heroes) {
				ceilingUnit.getCollideBehavior().collideWith(hero);
			}
			for (Enemy enemy : enemies) {
				ceilingUnit.getCollideBehavior().collideWith(enemy);
				enemy.getCollideBehavior().collideWith(ceilingUnit);
			}
			for (Fruit fruit : fruits) {
				ceilingUnit.getCollideBehavior().collideWith(fruit);
			}
			for (EnemyProjectile enemyProjectile : enemyProjectiles) {
				ceilingUnit.getCollideBehavior().collideWith(enemyProjectile);
			}
			for (HeroProjectile heroProjectile : heroProjectiles) {
				ceilingUnit.getCollideBehavior().collideWith(heroProjectile);
			}
		}
		for (FloorUnit floorUnit: floorUnits) {
			for (Hero hero : heroes) {
				floorUnit.getCollideBehavior().collideWith(hero);
			}
			for (Enemy enemy : enemies) {
				floorUnit.getCollideBehavior().collideWith(enemy);
				enemy.getCollideBehavior().collideWith(floorUnit);
			}
			for (Fruit fruit : fruits) {
				floorUnit.getCollideBehavior().collideWith(fruit);
			}
			for (EnemyProjectile enemyProjectile : enemyProjectiles) {
				floorUnit.getCollideBehavior().collideWith(enemyProjectile);
			}
			for (HeroProjectile heroProjectile : heroProjectiles) {
				floorUnit.getCollideBehavior().collideWith(heroProjectile);
			}
		}
		for (WallUnit wallUnit : wallUnits) {
			for (Hero hero : heroes) {
				wallUnit.getCollideBehavior().collideWith(hero);
			}
			for (Enemy enemy : enemies) {
				wallUnit.getCollideBehavior().collideWith(enemy);
				enemy.getCollideBehavior().collideWith(wallUnit);
			}
			for (Fruit fruit : fruits) {
				wallUnit.getCollideBehavior().collideWith(fruit);
			}
			for (EnemyProjectile enemyProjectile : enemyProjectiles) {
				wallUnit.getCollideBehavior().collideWith(enemyProjectile);
			}
			for (HeroProjectile heroProjectile : heroProjectiles) {
				wallUnit.getCollideBehavior().collideWith(heroProjectile);
			}
		}
	}

	/**
	 * removes all the objects need to be removed
	 */
	private void removeObjects(){
		for (GameObject obj : toBeRemoved) {
			remove(obj);
		}
		toBeRemoved.removeAll(toBeRemoved);
	}

	/**
	 * The method to update the charge time, drop condition and door station
	 */
	private void updateData() {
		chargeTime.setChargeTime(heroes.get(0).getChargeTimer()/500);
		if(enemies.size()==0){
			doorUnits.get(0).openDoor();
		}
		if (readyToReset) {
			try {
				startGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(dataManager.getLevel()==4&&enemies.size()==0) {
			stopRandomDrop();
		}
	}

	/**
	 * The method to remove a single object from the screen
	 * @param obj indicates the unit in the world that should be removed
	 * @see InteractableWorld#removeObjects()
	 */
	private void remove(GameObject obj) {
		ceilingUnits.remove(obj);
		floorUnits.remove(obj);
		wallUnits.remove(obj);
		heroes.remove(obj);
		enemies.remove(obj);
		enemyProjectiles.remove(obj);
		heroProjectiles.remove(obj);
		fruits.remove(obj);
		bubbles.remove(obj);
		doorUnits.remove(obj);
	}
}