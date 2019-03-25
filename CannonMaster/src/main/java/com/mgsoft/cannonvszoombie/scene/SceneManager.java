package com.mgsoft.cannonvszoombie.scene;

import java.util.ArrayList;
import java.util.List;

import com.mgsoft.cannonvszoombie.animation.AnimationManager;
import com.mgsoft.cannonvszoombie.animation.LinearTransition;
import com.mgsoft.cannonvszoombie.animation.LinearTransition.Eixo;
import com.mgsoft.cannonvszoombie.sprite.AnimatedSprite;
import com.mgsoft.cannonvszoombie.sprite.Bullet;
import com.mgsoft.cannonvszoombie.sprite.BulletFactory;
import com.mgsoft.cannonvszoombie.sprite.BulletFactory.BulletType;
import com.mgsoft.cannonvszoombie.sprite.Cannon;
import com.mgsoft.cannonvszoombie.sprite.Sprite;
import com.mgsoft.cannonvszoombie.util.Util;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SceneManager {

	private Dimension2D dim;
	private Pane root;
	private Scene scene;
	private HBox hudBox;

	private AnimationManager animationManager;
	private int shootAngleAcc = 0;
	private Bullet actualBullet;
	private boolean canShoot = true;

	private List<Sprite> sprites = new ArrayList<>();
	private List<Sprite> particles = new ArrayList<>();
	private List<Sprite> bullets = new ArrayList<>();
	private SpriteCleaner cleaner;
	private ProgressBar bulletProgressBar = new ProgressBar(0);

	private Sprite player;

	public SceneManager(Dimension2D screenDimension) {
		this.dim = screenDimension;
		root = new Pane();
		hudBox = new HBox();
		hudBox.setPrefSize(screenDimension.getWidth(), screenDimension.getHeight());
		root.setPrefSize(screenDimension.getWidth(), screenDimension.getHeight());
		root.getChildren().add(hudBox);
		root.setBackground(new Background(
				new BackgroundImage(new Image(Util.getResource("background.png")), BackgroundRepeat.REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
		scene = new Scene(root, Color.FIREBRICK);
		cleaner = new SpriteCleaner(root);
		animationManager = new AnimationManager(this);
		actualBullet = BulletFactory.getBullet(BulletType.SIMPLE);

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

		bulletProgressBar.setScaleX(0.8);
		bulletProgressBar.setTranslateX(player.getNode().getTranslateX()+20);
		bulletProgressBar.setTranslateY(player.getNode().getTranslateY()-40);
		hudBox.getChildren().add(bulletProgressBar);
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

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public AnimationManager getAnimationManager() {
		return animationManager;
	}

	public void setAnimationManager(AnimationManager animationManager) {
		this.animationManager = animationManager;
	}

	public int getShootAngleAcc() {
		return shootAngleAcc;
	}

	public void setShootAngleAcc(int shootAngleAcc) {
		this.shootAngleAcc = shootAngleAcc;
	}

	public Bullet getActualBullet() {
		return actualBullet;
	}

	public void setActualBullet(Bullet actualBullet) {
		this.actualBullet = actualBullet;
	}

	public boolean isCanShoot() {
		return canShoot;
	}

	public void setCanShoot(boolean canShoot) {
		this.canShoot = canShoot;
	}

	public SpriteCleaner getCleaner() {
		return cleaner;
	}

	public void setCleaner(SpriteCleaner cleaner) {
		this.cleaner = cleaner;
	}

	public ProgressBar getBulletProgressBar() {
		return bulletProgressBar;
	}

	public void setBulletProgressBar(ProgressBar bulletProgressBar) {
		this.bulletProgressBar = bulletProgressBar;
	}

	public void setSprites(List<Sprite> sprites) {
		this.sprites = sprites;
	}

	public void setParticles(List<Sprite> particles) {
		this.particles = particles;
	}

	public void setBullets(List<Sprite> bullets) {
		this.bullets = bullets;
	}

}
