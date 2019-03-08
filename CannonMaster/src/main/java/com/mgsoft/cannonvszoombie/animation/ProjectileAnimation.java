package com.mgsoft.cannonvszoombie.animation;

import java.util.concurrent.TimeUnit;

import com.mgsoft.cannonvszoombie.sprite.Sprite;

import javafx.geometry.Dimension2D;

public class ProjectileAnimation extends NodeAnimation {

	private double angle;
	private double velocidade;
	private final double gravidade = -9.81;
	private long timeInit;
	private boolean initialized = false;
	private boolean running = false;
	private double x0;
	private double y0;
	private Dimension2D dim;
	private Sprite sprite;

	double x, y = 0;

	public ProjectileAnimation(Sprite projectileToLaunch, double angle, double velocity, double fromX, double fromY,
			Dimension2D dim) {
		super(projectileToLaunch.getNode());
		this.sprite = projectileToLaunch;
		this.angle = angle;
		this.velocidade = velocity;
		this.y0 = fromY;
		this.x0 = fromX;
		this.dim = dim;

	}

	@Override
	protected void move(long time) {

		if (!initialized) {
			timeInit = TimeUnit.MILLISECONDS.convert(System.nanoTime(), TimeUnit.NANOSECONDS);
			initialized = true;
		}
		double t0;
		double vX = velocidade * (Math.cos(Math.toRadians(angle)));
		double vY = velocidade * (Math.sin(Math.toRadians(angle)));

		t0 = (((TimeUnit.MILLISECONDS.convert(time, TimeUnit.NANOSECONDS)) - timeInit) * 0.008 );
		x = x0 + vX * t0;
		y = dim.getHeight() - (y0 + vY * t0 + 0.5 * gravidade * Math.pow(t0, 2));
		nodeToMove.setTranslateX(x);
		nodeToMove.setTranslateY(y);

		if (y > dim.getHeight() && running) {
			stop();
			sprite.setDead(true);
		}

		if (!running) {
			running = true;
		}

	}

}
