package com.ae2dms.BubbleBobble;

import com.ae2dms.BubbleBobble.Controller.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

/**
 * {@link Main} creates a JavaFX application stage and adds a scene into that stage.
 * The size,themeColor of each scene is determined here.
 * @see com.ae2dms.BubbleBobble.Controller.GameController
 * @see com.ae2dms.BubbleBobble.Controller.Info1Controller
 * @see com.ae2dms.BubbleBobble.Controller.Info2Controller
 * @see com.ae2dms.BubbleBobble.Controller.Info3Controller
 * @see com.ae2dms.BubbleBobble.Controller.InfoController
 * @see com.ae2dms.BubbleBobble.Controller.SettingsController
 * @see com.ae2dms.BubbleBobble.Controller.StartController
 */
public class Main extends Application{
	/**
	 * the unit size
	 */
	public static final int UNIT_SIZE = 20;
	/**
	 * the width of the scene
	 */
	public static final int WIDTH = 40;
	/**
	 * the height of the scene
	 */
	public static final int HEIGHT = 34;
	/**
	 * the loader to load fxml files to application
	 */
	private static FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/start.fxml"));
	/**
	 * the theme css file used to decorate the scene in Main
	 * @see #setThemeColor(String)
	 */
	private static File theme = new File(Paths.get(System.getProperty("user.dir"))+"/src/main/resources/css/Default.css");
	/**
	 * the stage of the application
	 * @see StartController#onExitButtonClick()
	 */
	private static Stage stage;
	/**
	 * the scene of the application
	 */
	private static Scene scene;

	/**
	 * The start method for Application to create stage and scene
	 * @param primaryStage indicates the window of JavaFX application
	 * @throws IOException when the route of fxml or css file does not exist
	 * @exception IOException if scene or theme is null
	 */
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setResizable(false);
		scene= new Scene(fxmlLoader.load(),WIDTH*UNIT_SIZE,HEIGHT*UNIT_SIZE);
		scene.getStylesheets().add(theme.toURI().toURL().toExternalForm());
		stage=primaryStage;
		primaryStage.setTitle("BubbleBobble");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * The method to run JavaFX application
	 * @param args indicates parameters from the console
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * @return the stage of the application
	 * @see StartController#onExitButtonClick()
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * @param scene indicates the name of the fxml file
	 * @throws IOException when the route for fxml file does not exist
	 * @exception IOException if fxmlLoader is null
	 */
	public static void setScene(String scene) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/" + scene + ".fxml"));
		Main.scene.setRoot(fxmlLoader.load());
	}

	/**
	 * @param themeColor indicates the name of css file
	 * @throws MalformedURLException indicates the potential exception in communication protocol
	 */
	public static void setThemeColor(String themeColor) throws MalformedURLException {
		scene.getStylesheets().clear();
		theme = new File(Paths.get(System.getProperty("user.dir"))+"/src/main/resources/css/"+themeColor+".css");
		scene.getStylesheets().add(theme.toURI().toURL().toExternalForm());
	}
}
