package com.mgsoft.cannonvszoombie.sprite;

import com.mgsoft.cannonvszoombie.animation.NodeAnimation;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Sprite {

	private Node node;
	private Rectangle hitBox;
	private AnimationTimer animation;
	private boolean dead = false;
 

	public Sprite(Node nodeIn, Rectangle hitBox) {
		this.node = nodeIn;
		this.hitBox = hitBox;
	}

	public Sprite() {

	}

	public void setAnimation(NodeAnimation animation) {
		this.animation = animation;
	}

	public void startAnimation() {
		animation.start();
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node n) {
		node = n;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
