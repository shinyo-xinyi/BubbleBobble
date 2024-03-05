package com.ae2dms.BubbleBobble.Controller;
import com.ae2dms.BubbleBobble.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * The {@link com.ae2dms.BubbleBobble.Controller.StartController} class is a controller class for start.fxml file to achieve MVC pattern
 * {@code StartController}
 */
public class StartController {
    /**
     * the choice box to change theme color of the scene
     * @see Main#setThemeColor(String)
     */
    @FXML private ChoiceBox<String> choiceBox;
    /**
     * the colors that can be chosen as the theme color
     */
    ObservableList<String> colors;

    /**
     * The initialize method of start page
     */
    @FXML
    private void initialize(){
        colors = FXCollections.observableArrayList("Green","Yellow","Pink","Orange","Darkblue","Purple","Lightblue","Black");
        choiceBox.setItems(colors);
        choiceBox.setValue("Theme Color");
        choiceBox.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                Main.setThemeColor(newValue);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The action event to change the scene into the game page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onStartButtonClick() throws IOException {
        Main.setScene("game");
    }

    /**
     * The action event to change the scene into the settings page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onSettingsButtonClick() throws IOException {
        Main.setScene("settings");
    }

    /**
     * The action event to change the scene into the info page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    public void onInfoButtonClick() throws IOException {
        Main.setScene("info");
    }

    /**
     * The action event to close the stage
     */
    @FXML
    public void onExitButtonClick() {
        Main.getStage().close(); // exit the game
    }
}