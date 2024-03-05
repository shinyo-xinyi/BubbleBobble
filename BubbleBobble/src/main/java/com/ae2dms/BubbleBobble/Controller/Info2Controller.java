package com.ae2dms.BubbleBobble.Controller;

import com.ae2dms.BubbleBobble.Main;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The {@link com.ae2dms.BubbleBobble.Controller.Info2Controller} class is a controller class for start.fxml file to achieve MVC pattern.
 * <p>
 * {@code Info2Controller} displays the information of {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Enemy}
 * and {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Boss}
 * </p>
 */
public class Info2Controller {
    /**
     * The action event to change the scene into the start page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onHomeButtonClick() throws IOException {
        Main.setScene("start");
    }

    /**
     * The action event to change the scene into the info1 page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onFormerButtonClick() throws IOException {
        Main.setScene("info1");
    }

    /**
     * The action event to change the scene into the info3 page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onNextButtonClick() throws IOException {
        Main.setScene("info3");
    }
}
