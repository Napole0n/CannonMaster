package com.mgsoft.cannonvszoombie.animation;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public abstract class NodeAnimation extends AnimationTimer {

	protected Node nodeToMove;

	public NodeAnimation(Node node) {
		nodeToMove = node;
	}

	@Override
	public void handle(long now) {
		move(now);
	}

	// TODO method
	protected abstract void move(long timeNow);

	public Node getNode() {
		return nodeToMove;
	}

}
