package com.mgsoft.cannonvszoombie.animation;

import java.util.ArrayList;
import java.util.List;

import com.mgsoft.cannonvszoombie.animation.LinearTransition.Eixo;
import com.mgsoft.cannonvszoombie.scene.SceneManager;
import com.mgsoft.cannonvszoombie.sprite.Sprite;
import com.mgsoft.cannonvszoombie.util.Util;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class AnimationManager {

	private SceneManager sceneManager;
	private Dimension2D screenDimension;

	public AnimationManager(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
		this.screenDimension = sceneManager.getDim();

	}

	public void runBloodAnimation(Point2D where, int bloodParticles) {
		List<Sprite> particles = new ArrayList<>();
		for (int i = 0; i < bloodParticles; i++) {
			Color c = Math.random() * 5 < 2 ? Color.DARKRED : Color.RED;
			Circle circle = new Circle(Math.random() * 3, c);
			Sprite sprite = new Sprite(circle, new Rectangle());
			particles.add(sprite);
		}
		sceneManager.addParticles(particles);
		ProjectileAnimation anim;
		for (Sprite s : particles) {
			anim = new ProjectileAnimation(s, Math.random() * 360, Math.random() * 50, where.getX(), where.getY(),
					screenDimension);
			anim.start();
		}

	}

	public void walk(final Node node, final Eixo eixo,final double toX,final Duration duration) {
		Util.runLater(new Runnable() {
			@Override
			public void run() {
				new LinearTransition(node, eixo, toX, duration).play();
			}
		});

	}

	public void runFireAnimation(final Point2D from, final double angle, final double power) {
		Util.runLater(new Runnable() {
			@Override
			public void run() {
				Sprite bullet = new Sprite(new Circle(5, Color.BLACK), new Rectangle(10, 10));
				sceneManager.addBullet(bullet);
				ProjectileAnimation anim = new ProjectileAnimation(bullet, angle, power, from.getX(), from.getY(),
						screenDimension);
				anim.start();

			}
		});
	}

}
