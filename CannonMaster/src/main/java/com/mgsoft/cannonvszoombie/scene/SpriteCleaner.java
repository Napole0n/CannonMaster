package com.mgsoft.cannonvszoombie.scene;

import java.util.List;

import com.mgsoft.cannonvszoombie.sprite.Sprite;
import com.mgsoft.cannonvszoombie.util.Util;

import javafx.concurrent.Task;
import javafx.scene.layout.Pane;

public class SpriteCleaner {

	private Pane root;

	public SpriteCleaner(Pane rootPane) {
		this.root = rootPane;
	}

	public void clean(List<Sprite> sprites, int millis) {

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
}
