package com.mgsoft.cannonvszoombie.main;

import com.mgsoft.cannonvszoombie.animation.AnimatedSprite;
import com.mgsoft.cannonvszoombie.animation.LinearTransition;
import com.mgsoft.cannonvszoombie.animation.LinearTransition.Eixo;
import com.mgsoft.cannonvszoombie.util.Util;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Test extends Application {

	 private static final Image IMAGE = new Image(Util.getResource("sprites.png"));

	    private static final int COLUMNS  =   6;
	    private static final int COUNT    =  6;
	    private static final int OFFSET_X =  89;
	    private static final int OFFSET_Y =  0;
	    private static final int WIDTH    = 93;
	    private static final int HEIGHT   = 100;

	    public static void main(String[] args) {
	        launch(args);
	    }

	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("The Horse in Motion");

	        final ImageView imageView = new ImageView(IMAGE);
	        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

	        final Animation animation = new AnimatedSprite(
	                imageView,
	                Duration.millis(900),
	                COUNT, COLUMNS,
	                OFFSET_X, OFFSET_Y,
	                WIDTH, HEIGHT
	        );
	        animation.setCycleCount(Animation.INDEFINITE);
	        animation.play();

	        primaryStage.setScene(new Scene(new Group(imageView)));
	        primaryStage.show();
	    }}