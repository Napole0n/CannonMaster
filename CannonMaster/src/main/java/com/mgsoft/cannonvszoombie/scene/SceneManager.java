package com.mgsoft.cannonvszoombie.scene;

import java.util.ArrayList;
import java.util.List;

import com.mgsoft.cannonvszoombie.animation.LinearTransition;
import com.mgsoft.cannonvszoombie.animation.LinearTransition.Eixo;
import com.mgsoft.cannonvszoombie.sprite.Cannon;
import com.mgsoft.cannonvszoombie.sprite.Sprite;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SceneManager {

	private Dimension2D dim;
	private Pane root;
	private List<Sprite> sprites = new ArrayList<>();
	private List<Sprite> particles = new ArrayList<>();
	private List<Sprite> bullets = new ArrayList<>();
	private SpriteCleaner cleaner;

	private Sprite player;

	public SceneManager(Dimension2D screenDimension, Pane rootPane) {
		this.dim = screenDimension;
		root = rootPane;
		cleaner = new SpriteCleaner(rootPane);
		generateEntities();

	}

	public void addNode(Node n) {
		root.getChildren().add(n);
	}

	public void checkForEntitiesOutOfScreen() {
		List<Sprite> removed = cleaner.cleanOutOfScreen(sprites);
		this.sprites.removeAll(removed);
	}

	private void generateEntities() {
		player = new Cannon(-25, (int) dim.getHeight() - 20);
		root.getChildren().add(player.getNode());
	}

	public List<Node> getAllNodes() {
		List<Node> toReturn = new ArrayList<>();
		toReturn.addAll(fromSpriteToNode(sprites));
		return toReturn;
	}

	public void removeSprite(Sprite s) {
		sprites.remove(s);
		root.getChildren().remove(s.getNode());
	}

	public void removeBullet(Sprite bullet) {
		bullets.remove(bullet);
		root.getChildren().remove(bullet.getNode());
	}

	public void addBullet(Sprite bulletIn) {
		bullets.add(bulletIn);
		bulletIn.getNode().setId("bullet");
		root.getChildren().add(bulletIn.getNode());
	}

	public List<Sprite> getSprites() {
		return sprites;
	}

	public void addSprite(Sprite s) {
		sprites.add(s);
		root.getChildren().add(s.getNode());
	}

	public List<Sprite> getBullets() {
		return bullets;
	}

	public List<Node> fromSpriteToNode(List<Sprite> sprites) {
		List<Node> nodes = new ArrayList<>();
		for (Sprite s : sprites) {
			nodes.add(s.getNode());
		}
		return nodes;
	}

	public SpriteCleaner getSpriteCleaner() {
		return cleaner;
	}

	public Sprite getPlayer() {
		return player;
	}

	public void setPlayer(Sprite player) {
		this.player = player;
	}

	public Dimension2D getDim() {
		return dim;
	}

	public void setDim(Dimension2D dim) {
		this.dim = dim;
	}

	public List<Sprite> getParticles() {
		return particles;
	}

	public void addParticles(List<Sprite> particles) {
		this.particles = particles;
		List<Node> nodes = new ArrayList<>();
		for (Sprite s : particles) {
			nodes.add(s.getNode());
		}
		root.getChildren().addAll(nodes);
		cleaner.cleanParticles(particles, 2000);
	}

}
