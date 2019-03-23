package com.mgsoft.cannonvszoombie.game;

import com.mgsoft.cannonvszoombie.animation.AnimatedSprite;
import com.mgsoft.cannonvszoombie.animation.AnimationManager;
import com.mgsoft.cannonvszoombie.animation.LinearTransition;
import com.mgsoft.cannonvszoombie.animation.LinearTransition.Eixo;
import com.mgsoft.cannonvszoombie.scene.SceneManager;
import com.mgsoft.cannonvszoombie.sprite.MobFactory;
import com.mgsoft.cannonvszoombie.sprite.Sprite;
import com.mgsoft.cannonvszoombie.util.Util;

import javafx.animation.AnimationTimer;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CannonVsZoombie {

	private final int PANE_WIDTH = 800;
	private final int PANE_HEIGHT = 600;

	private Pane root;
	private Scene scene;
	private SceneManager sceneManager;
	private AnimationManager animationManager;
	private Dimension2D screenDimension;
	private MobFactory mobFactory;
	private int PointsCount = 0;
	private int shootAngleAcc = 0;

	public CannonVsZoombie(Dimension2D screenDimension) {

		root = new Pane();
		root.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
		this.screenDimension = screenDimension;
		scene = new Scene(root, Color.WHITE);

		sceneManager = new SceneManager(screenDimension, root);
		animationManager = new AnimationManager(sceneManager);
		mobFactory = new MobFactory();

		for (Node n : sceneManager.getAllNodes()) {
			root.getChildren().add(n);
		}

		registerEventHandlers(scene);
		runLogicClock();

	}

	private void registerEventHandlers(Scene s) {

		scene.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.UP)) {
				sceneManager.getPlayer().getNode().setRotate(sceneManager.getPlayer().getNode().getRotate() - 2);
				shootAngleAcc += 2;

			} else if (e.getCode().equals(KeyCode.DOWN)) {
				sceneManager.getPlayer().getNode().setRotate(sceneManager.getPlayer().getNode().getRotate() + 2);
				shootAngleAcc -= 2;
			} else if (e.getCode().equals(KeyCode.SPACE)) {

				animationManager.runFireAnimation(new Point2D(5, 5), 45 + shootAngleAcc, 100);

			}
		});

	}

	private void runLogicClock() {
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				
				MakeRandomEnemy();
				sceneManager.checkForEntitiesOutOfScreen();

				for (Sprite s : sceneManager.getSprites()) {
					for (Sprite b : sceneManager.getBullets()) {
						if (s.getNode().getBoundsInParent().intersects(b.getNode().getBoundsInParent())) {

							Util.runLater(new Runnable() {
								@Override
								public void run() {
									animationManager
											.runBloodAnimation(
													new Point2D(
															(s.getNode().getTranslateX())
																	+ s.getHitBox().getWidth() / 2,
															(s.getNode().getLayoutY()) + s.getHitBox().getHeight() / 2),
													90);
								}
							});

							Util.runLater(new Runnable() {
								public void run() {
									sceneManager.removeSprite(s);
									sceneManager.removeBullet(b);

								}
							});

						}
					}
				}
			}

		}.start();

	}

	public void MakeRandomEnemy() {
		
		if (Math.random() < 0.06) {
			AnimatedSprite man = mobFactory.getLittleMan();
			man.getImageView().setScaleX(1.4);
			man.getImageView().setScaleY(1.4);
			man.getImageView().setTranslateX(screenDimension.getWidth());
			man.getImageView().setTranslateY(screenDimension.getHeight() - 40);
			Sprite s = new Sprite(man.getImageView(), new Rectangle(80, 80));
			s.setHitBox(new Rectangle(80, 80));
			sceneManager.addSprite(s);
			man.play();
			animationManager.walk(s.getNode(), Eixo.X, -screenDimension.getWidth(), Duration.seconds(5));

		}
	}

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

	public AnimationManager getAnimationManager() {
		return animationManager;
	}

	public void setAnimationManager(AnimationManager animationManager) {
		this.animationManager = animationManager;
	}

}
