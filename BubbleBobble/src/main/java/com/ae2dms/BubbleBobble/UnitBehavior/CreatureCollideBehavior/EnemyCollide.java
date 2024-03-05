package com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior;

import com.ae2dms.BubbleBobble.Database.SoundEffect;
import com.ae2dms.BubbleBobble.Unit.MapUnit.MapObject;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit.Fruit;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.EnemyProjectile;
import com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Projectile.HeroProjectile;
import com.ae2dms.BubbleBobble.UnitBehavior.CollideBehavior;

/**
 * The {@link com.ae2dms.BubbleBobble.UnitBehavior.CreatureCollideBehavior.EnemyCollide} is a {@link CollideBehavior} of {@link Enemy}
 * @see BossCollide
 */
public class EnemyCollide extends CreatureCollide{
    /**
     * @see Enemy
     */
   private Enemy enemy;

    /**
     * The constructor of {@code EnemyCollide}
     * @param enemy indicates an enemy instance
     */
    public EnemyCollide(Enemy enemy) {
        super(enemy);
        this.enemy = enemy;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithProjectile() {
        if (!enemy.isBubbled()) {
            SoundEffect.BUBBLED.setToLoud();
            SoundEffect.BUBBLED.play();
            SoundEffect.BUBBLED.setToLoop();
            enemy.setBubbled(true);
            enemy.setyVelocity(0);
            enemy.setxVelocity(0);
            enemy.setyAccel(-0.1);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(HeroProjectile heroProjectile) {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(Enemy enemy) {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithBoss() {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(Hero hero){
        //handles collision with hero and what to do
        if (enemy.overlaps(hero)) {
            if (!enemy.isBubbled()) {
                hero.getCollideBehavior().collideWithMook();
                if (hero.getShielding() && !enemy.isTurningAwayFromShield()) {
                    enemy.setTurningAwayFromShield(true);
                    enemy.reverseDirection();
                }
            }
            else if (!enemy.isCanRemove()){
                SoundEffect.POP.play();
                enemy.die();
            }
        }
        if (enemy.isTurningAwayFromShield()) {
            if (enemy.getTurningAwayCount() <= 0) {
                enemy.setTurningAwayCount(10);
                enemy.setTurningAwayFromShield(false);
            }
            enemy.setTurningAwayCount(enemy.getTurningAwayCount()-1);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(MapObject obj) {
        if (enemy.overlaps(obj)) {
            if (enemy.isBubbled()) {
                enemy.setyVelocity(0);
                enemy.setyAccel(0);
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(EnemyProjectile enemyPrjectile) {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWith(Fruit fruit) {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithMook() {

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void collideWithStar() {

    }
}
