package com.ae2dms.BubbleBobble.Controller;

import com.ae2dms.BubbleBobble.Database.DataManager;
import com.ae2dms.BubbleBobble.Main;
import com.ae2dms.BubbleBobble.Database.Record;
import com.ae2dms.BubbleBobble.Database.SoundEffect;
import com.ae2dms.BubbleBobble.World.InteractableWorld;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.AccessibleRole;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * The {@link com.ae2dms.BubbleBobble.Controller.GameController} class is a controller class for game.fxml file to achieve MVC pattern.
 * <p>
 * The {@code GameController} let the player to choose the hero appearance and store it in {@link DataManager#setHeroImage(Image)},
 * displays the {@link InteractableWorld} in {@link #gamePane},
 * add timeline to {@link InteractableWorld#updateWorld()} to show animation,
 * controls the keyboard events of {@link com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero},
 * and pop-up a high score board when {@link DataManager#getLives()} returns 0.
 * </p>
 * @author Chengyue Pan
 */
public class GameController {
    /**
     * the button to go back to start scene
     */
    @FXML Button homeButton;
    /**
     * the button to pause the animation
     */
    @FXML Button pauseButton;
    /**
     * the button to start the animation
     */
    @FXML Button playButton;
    /**
     * the button to mute/stop muting the sound effect
     */
    @FXML Button soundButton;
    /**
     * the button to add new record on score board
     */
    @FXML Button doneButton;
    /**
     * the button to end tha gme
     */
    @FXML Button endButton;
    /**
     * the game panel
     */
    @FXML StackPane gamePane;
    /**
     * the score label display the total score
     * @see DataManager#getScore()
     */
    @FXML Label score;
    /**
     * the level label display the current level
     * @see DataManager#getLevel()
     */
    @FXML Label level;
    /**
     * the text filed to enter the player's name
     */
    @FXML TextField name;
    /**
     * The panel to choose hero character when game starts
     */
    @FXML AnchorPane heroPane;
    /**
     * the panel to pop up a high score board
     */
    @FXML AnchorPane scorePane;
    /**
     * the score board read records in records.txt and add new record when player click {@code #donebutton}
     * @see #onDoneClick()
     */
    @FXML TableView<Record> scoreBoard;
    /**
     * the table column to store the score
     */
    @FXML TableColumn<Record, Number> tableColumnScore;
    /**
     * the table column to store the level
     */
    @FXML TableColumn<Record, Number> tableColumnLevel;
    /**
     * the table column to store the name
     */
    @FXML TableColumn<Record, String> tableColumnName;
    /**
     * the imageView to store the images of hero
     */
    @FXML ImageView imageview;
    /**
     * the progressBar to display the chargeTime
     * @see InteractableWorld#getChargeTime()
     * @see com.ae2dms.BubbleBobble.Database.ChargeTime
     */
    @FXML ProgressBar progressBar;
    /**
     * the canvas to draw game objects on
     * @see InteractableWorld
     */
    @FXML Canvas canvas;
    /**
     * @see SoundEffect
     */
    private double FRUITvolumn,DEATHvolumn,SHOOTvolumn,POPvolumn,BUBBLEDvolumn,JUMPvolumn,EXPLODEvolumn,LANDvolumn;
    /**
     * whether the sound effetcs are mute
     */
    boolean soundActivate;
    /**
     * The context of the canvas
     * @see #canvas
     */
    private GraphicsContext g;
    /**
     * plays the animation
     */
    private Timeline timeline;
    /**
     * the interactableWorld dealing with the interactions of game objects
     * @see InteractableWorld
     */
    private InteractableWorld world;
    /**
     * the list to obtain the records stored in records.txt
     * @see #loadScoreBoard()
     */
    private ObservableList<Record> records;

    /**
     * The method to initialize the {@code GameController}
     * @throws IOException when game is not load successfully
     */
    @FXML
    private void initialize() throws IOException {
        soundActivate = true;
        loadGame();
        addAnimation();
        addKeyBindings();
        loadScoreBoard();
    }

    /**
     * The method to load records to the scoreBoard
     */
    private void loadScoreBoard() {
        records=getRecord();
        name.setOpacity(1);
        doneButton.setOpacity(1);
        tableColumnScore.setCellValueFactory(new PropertyValueFactory("Score"));
        tableColumnScore.setPrefWidth(scoreBoard.getPrefWidth() / 3);
        tableColumnLevel.setCellValueFactory(new PropertyValueFactory("Level"));
        tableColumnLevel.setPrefWidth(scoreBoard.getPrefWidth() / 3);
        tableColumnName.setCellValueFactory(new PropertyValueFactory("Name"));
        tableColumnName.setPrefWidth(scoreBoard.getPrefWidth() / 3);
        scoreBoard.setAccessibleRole(AccessibleRole.TABLE_VIEW);
        scoreBoard.setItems(records);
        scorePane.setOpacity(0);
        scorePane.setDisable(true);
        tableColumnScore.setSortType(TableColumn.SortType.DESCENDING);
        scoreBoard.getSortOrder().add(tableColumnScore);
        scoreBoard.setOpacity(1);
        scoreBoard.sort();
        endButton.setOpacity(0);
        endButton.setDisable(true);
    }

    /**
     * The method to load the {@link InteractableWorld} and progressBar to gamePane
     * @see InteractableWorld
     * @throws IOException when world is not started successfully
     */
    private void loadGame() throws IOException {
        world = new InteractableWorld(Main.UNIT_SIZE* Main.WIDTH,Main.UNIT_SIZE* Main.HEIGHT);
        world.startGame();
        canvas = world.getCanvas();
        g= canvas.getGraphicsContext2D();
        gamePane.getChildren().add(canvas);
        canvas.requestFocus();
        progressBar.progressProperty().bind(world.getChargeTime().chargeTimeProperty());
    }

    /**
     * The method to show animation of the changes in the {@link InteractableWorld}
     */
    private void addAnimation() {
        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        actionEvent -> {
                            try {
                                refreshScreen();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                ),
                new KeyFrame(
                        Duration.millis((double)1000/60)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * The method to refresh the whole screen
     * @throws IOException when route does not exist
     */
    private void refreshScreen() throws IOException {
        world.updateWorld();
        world.paintComponent(g);
        score.setText(String.valueOf(world.getDataManager().getScore()+world.getDataManager().getCurrentScore()));
        level.setText(String.valueOf(world.getDataManager().getLevel()));
        // stop the game when no lives for hero or game finished
        if (world.getDataManager().getLives() == 0 || world.getDataManager().isFinish()) {
            scorePane.setOpacity(1);
            scorePane.setDisable(false);
            timeline.stop();
        }
        gamePane.setStyle(
                "-fx-background-image: url('file:src/main/resources/picture/background/back"+world.getDataManager().getLevel()+".png');"
                        + "-fx-background-position: center center; "
                        + "-fx-background-repeat: stretch;"
                        + "-fx-background-color:transparent;"
        );
        imageview.setImage(new Image("file:src/main/resources/picture/heart/heart" + world.getDataManager().getLives()+ ".png"));
    }

    /**
     * The method to load old permanent records into the tableview
     * @return the former records stored in the records.txt
     * @see #loadScoreBoard()
     */
    private ObservableList<Record> getRecord() {
        ObservableList<Record> records = FXCollections.observableArrayList();
        InputStream input = GameController.class.getResourceAsStream("/records.txt");
        if(input==null){
            try {
                throw new IOException("The input is invalid!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Scanner scanner = new Scanner(Objects.requireNonNull(input));
        String currentLine;
        while(scanner.hasNextLine()){
            currentLine=scanner.nextLine();
            String[] record = currentLine.split(",");
            records.add(new Record(Integer.parseInt(record[0]),Integer.parseInt(record[1]),record[2]));
        }
        scanner.close();
        return records;
    }

    /**
     * The method to store the new record into records.txt when the player clicked the done button
     * @see #onDoneClick()
     * @throws IOException when file route is null
     */
    private void storeRecord() throws IOException {
        FileWriter fw = null;
        try {
            File f=new File(System.getProperty("user.dir")+"/src/main/resources/records.txt");
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(fw).write(score.getText()+","+level.getText()+","+name.getText()+"\r\n");
        Objects.requireNonNull(fw).flush();
        fw.close();
    }

    /**
     * The method to add keyboard bindings of the world to control the hero attributes
     * @see com.ae2dms.BubbleBobble.Unit.UpdatableUnit.Creature.Hero
     */
    @FXML
    protected void addKeyBindings(){
        canvas.addEventFilter(MouseEvent.ANY, (event) -> canvas.requestFocus());
        canvas.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                world.getHeroes().get(0).toMoveRight();
            }
            if (event.getCode() == KeyCode.LEFT) {
                world.getHeroes().get(0).toMoveLeft();
            }
            if (event.getCode() == KeyCode.UP) {
                world.getHeroes().get(0).toJump();
            }
            if (event.getCode() == KeyCode.SPACE) {
                world.getHeroes().get(0).toDash();
            }
            if (event.getCode() == KeyCode.E) {
                world.getHeroes().get(0).toShoot();
            }
            if (event.getCode() == KeyCode.Q) {
                world.getHeroes().get(0).toShield();
            }
            if (event.getCode() == KeyCode.W) {
                world.getHeroes().get(0).toCharge();
            }

        });
        canvas.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                world.getHeroes().get(0).stopMove();
            }
            if (event.getCode() == KeyCode.LEFT) {
                world.getHeroes().get(0).stopMove();
            }
            if (event.getCode() == KeyCode.SPACE) {
                world.getHeroes().get(0).stopDash();
            }
            if (event.getCode() == KeyCode.E) {
                world.getHeroes().get(0).stopShoot();
            }
            if (event.getCode() == KeyCode.Q) {
                world.getHeroes().get(0).stopShield();
            }
        });
    }

    /**
     * The action event to set the hero image1
     */
    @FXML
    protected void onHero1Click(){
        world.getDataManager().setHeroImage(new Image("file:src/main/resources/picture/hero1.png"));
        startGame();
    }

    /**
     * The action event to set the hero image2
     */
    @FXML
    protected void onHero2Click(){
        world.getDataManager().setHeroImage(new Image("file:src/main/resources/picture/hero2.png"));
        startGame();
    }

    /**
     * The action event to set the hero image3
     */
    @FXML
    protected void onHero3Click(){
        world.getDataManager().setHeroImage(new Image("file:src/main/resources/picture/hero3.png"));
        startGame();
    }

    /**
     * The method to hide the hero pane and start the animation
     */
    private void startGame(){
        timeline.play();
        heroPane.setOpacity(0);
        heroPane.setDisable(true);
        canvas.requestFocus();
    }

    /**
     * The action event to change the scene into the start page
     * @throws IOException when it fails to change the root of the scene in {@link Main}
     */
    @FXML
    protected void onHomeButtonClick() throws IOException {
        timeline.stop();
        Main.setScene("start");
    }

    /**
     * The action event to pause the animation
     */
    @FXML
    protected void onPauseButtonClick(){
        timeline.pause();
        canvas.requestFocus();
    }

    /**
     * The action event to continue the animation
     */
    @FXML
    protected void onPlayButtonClick(){
        timeline.play();
        canvas.requestFocus();
    }

    /**
     * The method to mute/stop mute the sound effect
     * @see SoundEffect
     */
    @FXML
    protected void onSoundButtonClick(){
        if(!soundActivate) {
            SoundEffect.FRUIT.setVolume(FRUITvolumn);
            SoundEffect.DEATH.setVolume(DEATHvolumn);
            SoundEffect.SHOOT.setVolume(SHOOTvolumn);
            SoundEffect.POP.setVolume(POPvolumn);
            SoundEffect.BUBBLED.setVolume(BUBBLEDvolumn);
            SoundEffect.JUMP.setVolume(JUMPvolumn);
            SoundEffect.EXPLODE.setVolume(EXPLODEvolumn);
            SoundEffect.LAND.setVolume(LANDvolumn);
            soundButton.setStyle("-fx-background-image: url('file:src/main/resources/picture/Button/b_Sound.png');" );
            soundActivate=true;
        }
        else{
            FRUITvolumn = SoundEffect.FRUIT.getVolume();
            DEATHvolumn= SoundEffect.DEATH.getVolume();
            SHOOTvolumn= SoundEffect.SHOOT.getVolume();
            POPvolumn= SoundEffect.POP.getVolume();
            BUBBLEDvolumn= SoundEffect.BUBBLED.getVolume();
            JUMPvolumn= SoundEffect.JUMP.getVolume();
            EXPLODEvolumn= SoundEffect.EXPLODE.getVolume();
            LANDvolumn= SoundEffect.LAND.getVolume();
            SoundEffect.FRUIT.mute();
            SoundEffect.DEATH.mute();
            SoundEffect.SHOOT.mute();
            SoundEffect.POP.mute();
            SoundEffect.BUBBLED.mute();
            SoundEffect.JUMP.mute();
            SoundEffect.EXPLODE.mute();
            SoundEffect.LAND.mute();
            soundButton.setStyle("-fx-background-image: url('file:src/main/resources/picture/Button/b_Sound_Inactive.png');" );
            soundActivate=false;
        }
        canvas.requestFocus();
    }

    /**
     * The method to add the current record to the high score board
     * @throws IOException when record route is null
     */
    @FXML
    protected void onDoneClick() throws IOException {
        scoreBoard.getItems().add(new Record(Integer.parseInt(score.getText()),Integer.parseInt(level.getText()),name.getText()));
        scoreBoard.sort();
        storeRecord();
        doneButton.setOpacity(0);
        doneButton.setDisable(true);
        endButton.setOpacity(1);
        endButton.setDisable(false);
    }

    /**
     * The action event to reset the game and change the scene into the start page
     * @throws IOException when fxml route is null
     */
    @FXML
    protected void onEndClick() throws IOException {
        heroPane.setOpacity(0);
        heroPane.setDisable(true);
        canvas.requestFocus();
        scoreBoard.setOpacity(0);
        world.getDataManager().restart();
        world.markToReset();
        Main.setScene("start");
    }
}
