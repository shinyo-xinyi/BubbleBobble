package com.ae2dms.BubbleBobble.Controller;

import com.ae2dms.BubbleBobble.Main;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The {@link com.ae2dms.BubbleBobble.Controller.InfoController} class is a controller class for info.fxml file to achieve MVC pattern.
 * <p>
 * {@code InfoController} displays the information of {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero},
 * {@link com.ae2dms.BubbleBobble.UnitState.HeroState}
 * </p>
 */
public class InfoController {
    @FXML AnchorPane anchorPane;
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
    protected void onNextButtonClick() throws IOException {
        Main.setScene("info1");
    }
}
