package com.mgsoft.cannonvszoombie.scene;

import java.util.ArrayList;
import java.util.List;

import com.mgsoft.cannonvszoombie.sprite.Sprite;
import com.mgsoft.cannonvszoombie.util.Util;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class SpriteCleaner {

	private Pane root;

	public SpriteCleaner(Pane rootPane) {
		this.root = rootPane;
	}

	public void cleanParticles(List<Sprite> sprites, int millis) {

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				Thread.sleep(1000);
				for (Sprite s : sprites) {
					Util.runLater(new Runnable() {
						@Override
						public void run() {
							root.getChildren().remove(s.getNode());
						}
					});
				}
				return null;
			}
		};
		new Thread(task).start();
	}

	public List<Sprite> cleanOutOfScreen(List<Sprite> sprites) {

		List<Sprite> removedSprites = new ArrayList<>();

		Util.runLater(new Runnable() {
			@Override
			public void run() {
				for (Sprite s : sprites) {
					if (!root.contains(new Point2D(s.getNode().getTranslateX(), s.getNode().getTranslateY()))) {
						removedSprites.add(s);
						System.out.println("Putz");
					}
				}

			}
		});
		System.out.println("Lol");
		return removedSprites;
	}
}
