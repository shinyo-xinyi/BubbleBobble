package com.ae2dms.BubbleBobble.Controller;

import com.ae2dms.BubbleBobble.Database.DataManager;
import com.ae2dms.BubbleBobble.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * The {@link com.ae2dms.BubbleBobble.Controller.SettingsController} class is a controller class for settings.fxml file to achieve MVC pattern
 * <p>
 * {@code SettingsController} controls the background music
 * and theme color of {@link Main#setThemeColor(String)}
 * </p>
 */
public class SettingsController {
    /**
     * displays the volume value
     */
    @FXML private Text volumeNumber;
    /**
     * the slider to control the volume
     */
    @FXML private Slider slider;
    /**
     * the choice box to change theme color of the scene
     * @see Main#setThemeColor(String)
     */
    @FXML private ChoiceBox<String> choiceBox;
    /**
     * plays the BGM
     * @see #onButtonClick()
     */
    @FXML private CheckBox musicBox;
    /**
     * the music
     */
    private Media media;
    /**
     * the music player
     */
    private MediaPlayer mediaPlayer;
    /**
     * default colume
     */
    private int volume=60;
    /**
     * does not play the music by default
     */
    private boolean playMusic=false;

    /**
     * The initialize method of settings page
     */
    @FXML
    private void initialize(){
        ObservableList<String> colors = FXCollections.observableArrayList("Green","Yellow","Pink","Orange","Darkblue","Purple","Lightblue","Black");
        choiceBox.setItems(colors);
        choiceBox.setValue("Theme Color");
        choiceBox.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                Main.setThemeColor(newValue);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        File f = new File(System.getProperty("user.dir")+"/src/main/resources/music/bgm.mp3");
        media = new Media(f.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        slider.setValue(volume);
        volumeNumber.setText(String.valueOf(volume));
        slider.valueProperty().addListener((observableValue, oldValue, newValue)
                -> {
                    volumeNumber.setText(String.valueOf((int) (slider.getValue())));
                }
        );
        mediaPlayer.volumeProperty().bind(slider.valueProperty().divide(100));
        musicBox.setSelected(playMusic);
        musicBox.setOnMouseClicked(e->
            {
                if(musicBox.isSelected()){
                    playMusic=true;
                    mediaPlayer.play();
                }
                else{
                    playMusic=false;
                    mediaPlayer.stop();
                }
            }
        );
    }

    /**
     * The action event to change the scene into the strat page
     * @throws IOException when it fails to set scene in {@link Main}
     */
    @FXML
    protected void onButtonClick() throws Exception {
        Main.setScene("start");
    }
}
