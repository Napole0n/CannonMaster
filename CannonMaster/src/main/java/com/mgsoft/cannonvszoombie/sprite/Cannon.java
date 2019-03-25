package com.mgsoft.cannonvszoombie.sprite;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cannon extends Sprite {

	public Cannon(int xPos, int yPos) {
		setNode(new Rectangle(80, 30, Color.BLACK));
		getNode().setTranslateX(xPos);
		getNode().setTranslateY(yPos);
		getNode().setRotate(135);
		setHitBox(new Rectangle(80, 20));
	}
	

}
