package com.mgsoft.cannonvszoombie.sprite;

import com.mgsoft.cannonvszoombie.animation.NodeAnimation;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Sprite {

	private Node node;
	private Shape hitBox;
	private AnimationTimer animation;
	private boolean dead = false;
	private int life;
	
 

	public Sprite(Node nodeIn, Shape hitBox) {
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

	public Shape getHitBox() {
		return hitBox;
	}

	public void setHitBox(Shape hitBox) {
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
