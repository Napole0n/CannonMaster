package com.mgsoft.cannonvszoombie.game;


import com.mgsoft.cannonvszoombie.level.LevelController;
import com.mgsoft.cannonvszoombie.scene.SceneManager;
import com.mgsoft.cannonvszoombie.sprite.Bullet;
import com.mgsoft.cannonvszoombie.sprite.Sprite;
import com.mgsoft.cannonvszoombie.util.Util;

import javafx.animation.AnimationTimer;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;


public class CannonVsZoombie {


	private SceneManager sceneManager;
	private LevelController levelController;
	private int PointsCount = 0;

	public CannonVsZoombie(Dimension2D screenDimension) {
		sceneManager = new SceneManager(screenDimension);
		this.levelController = new LevelController(sceneManager);
		levelController.start();
		registerEventHandlers();
		runLogicClock();
	}
	
	private void registerEventHandlers() {
		sceneManager.getScene().setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.UP)) {
				sceneManager.getPlayer().getNode().setRotate(sceneManager.getPlayer().getNode().getRotate() - 2);
				sceneManager.setShootAngleAcc(sceneManager.getShootAngleAcc() + 2);

			} else if (e.getCode().equals(KeyCode.DOWN)) {
				sceneManager.getPlayer().getNode().setRotate(sceneManager.getPlayer().getNode().getRotate() + 2);
				sceneManager.setShootAngleAcc(sceneManager.getShootAngleAcc() - 2);
			} else if (e.getCode().equals(KeyCode.SPACE)&&sceneManager.isCanShoot()) {

				sceneManager.getAnimationManager().runFireAnimation(new Point2D(5, 5), 45 + sceneManager.getShootAngleAcc(), 100);
				sceneManager.setCanShoot(false);
				sceneManager.getBulletProgressBar().setProgress(0);

			}
		});
	}

	private void runLogicClock() {

		new AnimationTimer() {
			@Override
			public void handle(long now) {

				sceneManager.checkForEntitiesOutOfScreen();

				for (final Sprite s : sceneManager.getSprites()) {
					for (final Sprite b : sceneManager.getBullets()) {
						if (s.getNode().getBoundsInParent().intersects(b.getNode().getBoundsInParent())) {

							Util.runLater(new Runnable() {
								@Override
								public void run() {
									sceneManager.getAnimationManager()
											.runBloodAnimation(
													new Point2D(
															(s.getNode().getTranslateX())
																	+ 10,
															(s.getNode().getLayoutY()) +20),
													90);
								}
							});

							Util.runLater(new Runnable() {
								public void run() {
									s.setDead(true);
									sceneManager.removeSprite(s);
									sceneManager.removeBullet(b);

								}
							});

						}
					}
				}
				
				for(Sprite b : sceneManager.getBullets()) {
					if(b.isDead()) {
						Util.runLater(new Runnable() {
							
							@Override
							public void run() {
								sceneManager.removeBullet(b);								
							}
						});
						
					}
				}
				
				if(!sceneManager.isCanShoot()) {
					if(sceneManager.getBulletProgressBar().getProgress()>=1) {
						sceneManager.setCanShoot(true);
					}else {
						sceneManager.getBulletProgressBar().setProgress(sceneManager.getBulletProgressBar().getProgress()+0.03);
					}
				}
				
				
			}

		}.start();

	}

	public SceneManager getSceneManager() {
		return sceneManager;
	}

	public void setSceneManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}

}
