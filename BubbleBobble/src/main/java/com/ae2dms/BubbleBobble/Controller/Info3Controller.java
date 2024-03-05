package com.ae2dms.BubbleBobble.Controller;

import com.ae2dms.BubbleBobble.Main;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The {@link com.ae2dms.BubbleBobble.Controller.Info3Controller} class is a controller class for start.fxml file to achieve MVC pattern
 * <p>
 * {@code Info3Controller} displays the information of {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.CollectableUnit}
 * and {@link com.ae2dms.BubbleBobble.Unit.SpecialUnit.DoorUnit}
 * </p>
 */
public class Info3Controller {
    /**
     * The action event to change the scene into the start page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onHomeButtonClick() throws IOException {
        Main.setScene("start");
    }

    /**
     * The action event to change the scene into the info2 page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onFormerButtonClick() throws IOException {
        Main.setScene("info2");
    }
}
