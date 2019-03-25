package com.mgsoft.cannonvszoombie.main;

import com.mgsoft.cannonvszoombie.game.CannonVsZoombie;

import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

public class App extends Application {

	private CannonVsZoombie theGame = new CannonVsZoombie(new Dimension2D(800, 600));

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setScene(theGame.getSceneManager().getScene());
		primaryStage.setResizable(false);
		primaryStage.show();

	}

}
