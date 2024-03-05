package com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature;

import com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.BossCollide;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * The {@code com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Boss} is a special {@link Enemy}.
 * <p>
 * It could not be bubbled, and should be shot several times to die.
 * After it die, it will generate a star to let the hero become invincible for a while.
 * </p>
 * @see BossCollide
 */
public class Boss extends Enemy{
    protected static final int SIZE = 50;
    protected static final Image HPimage1 = new Image("file:src/main/resources/picture/enemies/HP1.png");
    protected static final Image HPimage2 = new Image("file:src/main/resources/picture/enemies/HP2.png");
    protected static final Image HPimage3 = new Image("file:src/main/resources/picture/enemies/HP3.png");
    protected static final Image image = new Image("file:src/main/resources/picture/enemies/leftBoss.gif");
    private int lives;
    private Image HPimage;

    /**
     * The constructor to initialize the Boss
     * @param world indicates where to add the object
     * @param colNum indicates the unit horizontal coordination
     * @param rowNum indicates the unit vertical coordination
     */
    public Boss(InteractableWorld world, int colNum, int rowNum) {
        super(world, colNum, rowNum);
        lives=6;
        HPimage=HPimage3;
        reverseImage = new Image("file:src/main/resources/picture/enemies/rightBoss.gif");
        collideBehavior = new BossCollide(this);
    }

    /**
     * @return lives of boss
     */
    public int getLives() {
        return lives;
    }

    /**
     * @param lives indicates the reaming chance to be attacked by {@link Hero}
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * updates boss
     */
    @Override
    public void update() {
        super.update();
        if (lives==0) {
            die();
        }else if (lives==2){
            HPimage=HPimage1;
        }else if(lives==4){
            HPimage=HPimage2;
        }else if (lives==6){
            HPimage=HPimage3;
        }
    }

    /**
     * {@inheritDoc}
     * It will not be bubbled
     */
    @Override
    public void drawOn(GraphicsContext g) {
        g.drawImage(HPimage,x,y-50,30,30);
        g.drawImage(image,x,y,SIZE,SIZE);
    }

    /**
     * generate a star
     */
    @Override
    public void die(){
        world.getUnitFactory().addUnit("Star",x,y);
        markToRemove();
    }
}
