package com.ae2dms.BubbleBobble.Database;

import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * The {@link com.ae2dms.BubbleBobble.Database.ChargeTime} class stores the chargeTime
 * for {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero} to set
 * {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Bubble} in {@link InteractableWorld}
 * and it is bind with {@link com.ae2dms.BubbleBobble.Controller.GameController #progressBar}
 */
public class ChargeTime {
    /**
     * @see InteractableWorld {@link #chargeTime}
     */
    private DoubleProperty chargeTime;

    /**
     * The constructor
     * @param chargeTime indicates the value of charging time
     */
    public ChargeTime(double chargeTime) {
        setChargeTime(chargeTime);
    }

    /**
     * gets the charge time value
     * @return the charge time value
     */
    public double getChargeTime(){
        return chargeTimeUpdator.get();
    }

    /**
     * get the charge time property
     * @return the charge time property
     */
    public DoubleProperty chargeTimeProperty(){
        return chargeTimeUpdator;
    }

    /**
     * sets the charge time property
     * @param chargeTime indicates the charge time property
     */
    public void setChargeTime (double chargeTime){
        this.chargeTimeUpdator.set(chargeTime);
    }

    /**
     * change the charge time value to property
     */
    private final DoubleProperty chargeTimeUpdator = new SimpleDoubleProperty();
}
