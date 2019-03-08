package com.mgsoft.cannonvszoombie.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class LinearTransition {

	private double distance;
	private Node node;
	private Eixo eixo;
	private Duration duration;
	private Timeline timeline;

	public LinearTransition(Node node, Eixo eixo, double distance, Duration duration) {
		this.node = node;
		this.distance = distance;
		this.duration = duration;
		this.eixo = eixo;

		initialize();
	}

	public void play() {
		timeline.play();
	}

	private void initialize() {
		timeline = new Timeline();
		timeline.setCycleCount(1);
		KeyValue keyValue = eixo == Eixo.X ? new KeyValue(node.translateXProperty(), distance, Interpolator.EASE_BOTH)
				: new KeyValue(node.translateYProperty(), distance, Interpolator.EASE_BOTH);
		KeyFrame keyFrame = new KeyFrame(duration, keyValue);
		timeline.getKeyFrames().add(keyFrame);
	}

	public enum Eixo {
		X, Y
	}
}
