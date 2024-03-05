<h1>AE2DMS-CW-20214701</h1>



## Swing -> JavaFX

#### 1.JFrame -> Application

add stage and scene 

in **com.ae2dms.BubbleBobble.Main** 



#### 2.JComponent -> Canvas

(1) add canvas 

(2) using drawOn() method 

see  **com.ae2dms.BubbleBobble.World.InteractableWorld**



#### 3.GamePanel -> GameController 

Jpanel -> StackPane gamePane 

in **com.ae2dms.BubbleBobble.Controller.GameController**



#### 4.Clip -> AutoClip 

(1) change volume value between 0-1 

(2) modify setLoop and setLoud  

in **com.ae2dms.BubbleBobble.Database.SoundEffect**



#### 5.Graphics2D -> GraphicsContext

(1) Point2D.Double -> Point2D

(2) Rectangle2D.Double -> Rectangle2D

(3) setColor -> setFill 

in **com.ae2dms.BubbleBobble.World.GameObject**

(4) xxx.getHitbox().getCenterX() -> xxx.getHitbox().getMaxX()-xxx.getHitbox().getWidth()/2 

in **com.ae2dms.BubbleBobble.UnitBehavior.MapCollideBehavior.WallCollide#collide**



## Refactoring

#### 1. Replace magic number with symbolic content

（1）stunTimer = 250; -> stunTimer = STUNNED_TIME; 

see **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero**

（2）activeFrames  -> (global constant) ACTIVE_FRAMES = 200;

see **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject**



#### 2. Extract method from a larger block of code based on comments

(1) rename method updatePosition() -> updateWorld() and split it into sub methods	

see **com.ae2dms.BubbleBobble.World.InteractableWorld#updateWorld()**

(2) split initiateCollisions()  into sub methods	

see **com.ae2dms.BubbleBobble.World.InteractableWorld#initiateCollisions()**		



#### 3. Single responsibility with extract class

(1) move add methods into **com.ae2dms.BubbleBobble.Factory.UnitFactoryImpl** 

(2) move data into **com.ae2dms.BubbleBobble.Database.DataManager**



#### 4. Type embedded in name 

merge add methods into addUnit() 

 see **com.ae2dms.BubbleBobble.Factory.UnitFactoryImpl** 



#### 5.Encapsulate fields 

 （1）all variables : public -> protected/private

in **com.ae2dms.BubbleBobble.World.GameObject** and its sub classes

（2）volume : public -> private 

in **com.ae2dms.BubbleBobble.Database.SoundEffect**

（3）add getter and setter in **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject**

and in **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy**



#### 6.Access constants outside class

private static final int xxx-> public ...HEIGHT,WIDTH in **com.ae2dms.BubbleBobble.Main**



#### 7.Pull up methods

(1) Enemy & Hero -> **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Creature**

(2) CeilingUnit, FloorUnit & WallUnit -> **com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject**

(3) EnemyProjectile & HeroProjectile  -> **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.ProjectileObject**

(4) Creature, Projectile & Fruit -> **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject**



#### 8.Extract Interface

(1) **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Collectable**

(2) **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Alive**

(3) **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Updatable**



#### 9.Replace temporary with query

delete xLow,xHigh,yLow,yHigh and modify the method

see **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.UpdatableObject #isOffScreen()**



#### 10.Remove duplicates

private static final int WIDTH/HEIGHT = Main.UNIT_SIZE + 10; -> public static final int SIZE = 30;

in **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy**



#### 11.Extends existing code

 add the shootProjectile method in **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy**



#### 12.Rename variables to achieve self documentation

 WALK/RUN -> WALK_SPEE/RUN_SPEED in **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero**



## Design Pattern

#### 1. Strategy

add CollideBehavior in **com.ae2dms.BubbleBobble.UnitBehavior**

* game objects exhibit various collide behaviours with each other.

  

#### 2. Singleton

**com.ae2dms.BubbleBobble.Database.DataManager**

* The project only requires one instance of DataManager.

  

#### 3. Factory Method

 **com.ae2dms.BubbleBobble.Factory**

* hide the creation logic of the classes from the caller. 



#### 4. State

 **com.ae2dms.BubbleBobble.UnitState**

* Integrate states and transitions of Hero.



#### 5. Decorator

 **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.DropDecorator**

* combinatorial explosion of subclasses of Fruit. 



#### 6. MVC

(1) addKeyBindings() -> **com.ae2dms.BubbleBobble.Controller.GameController#addKeyBindings()** 

* keyboard inputs from "model" to “controller”

(2) GamePanel -> StackPane in **src/main/resources/fxml/game.fxml**

* pane display in "view"

(3) Create fxml files with Controllers in **com.ae2dms.BubbleBobble.Controller**

* using   **com.ae2dms.BubbleBobble.Main#setScene() ** to change the root of the scene



## Additions

1. Add hero appearance choose.

   see **com.ae2dms.BubbleBobble.Controller.GameController.onHero1/2/3 Click()**

2. Add task for fruit periodically generation in level3.

    see **com.ae2dms.BubbleBobble.Database.TaskService** 

3. Add portal for level change.

    see **com.ae2dms.BubbleBobble.Unit.SpecialUnit.DoorUnit**

4. Set five initial health points to heroes, health drops when they die, and health can be restored by potions and a progress bar to see the charging process condition of hero.

5. Add home, pause and play button.

   see  **com.ae2dms.BubbleBobble.Controller.GameController.onHome/Pause/PlayButtonClick()** 

6. Add mute sound effect function.

   see **com.ae2dms.BubbleBobble.Controller.GameController.onSoundButtonClick()** 

7. Add BGM & volume control.

   see **com.ae2dms.BubbleBobble.Controller.SettingController**
   
7. Add special fruits - treasure,star and portion that will drop sometimes.

   see **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit**
   
7. Add special enemy - boss.

   see **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Boss**

​	10.Add image for each game object.

​		see **com.ae2dms.BubbleBobble.World.GameObject**

11.  Generate enemies and fruits randomly, and image changes direction when game objects change direction.

    see **com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy**,**com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit**



## Testing 

#### 1.Class: UnitFactoryImplTest

| Test                          | Details                                | Inputs                   | Expected Outcome                            | Test Outcome                                |
| ----------------------------- | -------------------------------------- | ------------------------ | ------------------------------------------- | ------------------------------------------- |
| test_addUnit                  | test adding bubble unit into the world | null                     | world.getBubbles().get(0) instanceof Bubble | world.getBubbles().get(0) instanceof Bubble |
| test_addUnit_IllegalDirection | test invalid direction argument        | int direction = 0        | "The direction value is invalid!"           | "The direction value is invalid!"           |
| test_addUnit_IllegalDirection | test invalid direction argument        | int direction = 6        | "The direction value is invalid!"           | "The direction value is invalid!"           |
| test_addUnit_IllegalType      | test invalid unit type argumen         | String type = Projectile | "The type is invalid!"                      | "The type is invalid!"                      |
| test_addUnit_IllegalColNum    | test invalid colNum argument           | int colNum = -5          | "The position is invalid!"                  | "The position is invalid!"                  |
| test_addUnit_IllegalRowNum    | test invalid rowNum argument           | int rowNum = -1          | "The position is invalid!"                  | "The position is invalid!"                  |



#### 2.Class: CollideBehaviorTest

| Test                    | Details                                                | Inputs | Expected Outcome                   | Test Outcome                       |
| ----------------------- | ------------------------------------------------------ | ------ | ---------------------------------- | ---------------------------------- |
| test_collideWithFloor   | test the yVelocity after hero collide with floor       | null   | hero.getyVelocity()=0              | hero.getyVelocity()=0              |
| test_collideWithCeiling | test the yVelocity after hero collide with ceiling     | null   | hero.getyVelocity()=0              | hero.getyVelocity()=0              |
| test_collideWithWall    | test the direction change after hero collide with wall | null   | hero.getDirection()=direction*(-1) | hero.getDirection()=direction*(-1) |



#### 3.Class: DataManagerTest

| Test                         | Details                                    | Inputs                     | Expected Outcome              | Test Outcome                  |
| ---------------------------- | ------------------------------------------ | -------------------------- | ----------------------------- | ----------------------------- |
| test_getLevel                | test getLevel to get initial level         | null                       | 1                             | 2                             |
| test_getScore                | test getLevel to get initial score         | null                       | 0                             | 0                             |
| test_getLives                | test getLevel to get initial lives         | null                       | 5                             | 5                             |
| test_getCurrentScore         | test getLevel to get initial current score | null                       | 0                             | 0                             |
| test_setLives                | test setLives with valid value             | int lives = 4              | 4                             | 4                             |
| test_setLives_Invalid        | test setLives with invalid value           | int lives = 6              | "The lives value is invalid!" | "The lives value is invalid!" |
| test_setCurrentScore         | test setCurrentScorewith valid value       | int score = 100            | 100                           | 100                           |
| test_setCurrentScore_Invalid | test setCurrentScore with negative value   | int score = -4             | "The score value is invalid!" | "The score value is invalid!" |
| test_setCurrentScore_Invalid | test setCurrentScore with large value      | int score = 56512135451220 | "The score value is invalid!" | "The score value is invalid!" |



