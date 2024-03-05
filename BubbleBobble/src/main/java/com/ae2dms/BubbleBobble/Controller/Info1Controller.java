package com.ae2dms.BubbleBobble.Controller;

import com.ae2dms.BubbleBobble.Main;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * The {@link com.ae2dms.BubbleBobble.Controller.Info1Controller} class is a controller class for start.fxml file to achieve MVC pattern
 * <p>
 * {@code Info1Controller} displays the information of {@link GameController#addKeyBindings()}
 * </p>
 */
public class Info1Controller {
    /**
     * The action event to change the scene into the start page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onHomeButtonClick() throws IOException {
        Main.setScene("start");
    }

    /**
     * The action event to change the scene into the info page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onFormerButtonClick() throws IOException {
        Main.setScene("info");
    }

    /**
     * The action event to change the scene into the info2 page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onNextButtonClick() throws IOException {
        Main.setScene("info2");
    }
}
