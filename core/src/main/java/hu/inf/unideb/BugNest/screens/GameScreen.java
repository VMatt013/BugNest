package hu.inf.unideb.BugNest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import hu.inf.unideb.BugNest.Main;
import hu.inf.unideb.BugNest.entities.Player;
import hu.inf.unideb.BugNest.entities.XPObject;
import hu.inf.unideb.BugNest.gui.HUD;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class GameScreen implements Screen {
    private Main game;
    private Player player;
    private HUD hud;
    private Array<XPObject> xpObjects;
    private float spawnTimer;
    private Texture background;
    private OrthographicCamera camera;

    public GameScreen(Main game) {
        this.game = game;
        this.player = new Player();
        this.hud = new HUD(player);
        this.xpObjects = new Array<>();
        this.spawnTimer = 0;

        this.background = new Texture("bg.jpg");

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 600);
        this.camera.position.set(player.getX(), player.getY(), 0);
        this.camera.update();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        camera.position.x = Math.max(camera.viewportWidth / 2f,
            Math.min(2000 - camera.viewportWidth / 2f, player.getX()));
        camera.position.y = Math.max(camera.viewportHeight / 2f,
            Math.min(2000 - camera.viewportHeight / 2f, player.getY()));
        camera.update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, 2000, 2000);
        game.batch.end();

        game.batch.begin();
        player.render(game.batch);
        for (XPObject xp : xpObjects) {
            xp.render(game.batch);
        }
        game.batch.end();

        game.batch.setProjectionMatrix(camera.projection);
        game.batch.begin();
        hud.render(game.batch);
        game.batch.end();

        player.update(delta);
        spawnXP(delta);
        checkCollisions();
    }


    private void spawnXP(float delta) {
        spawnTimer += delta;
        if (spawnTimer > 1) {
            spawnTimer = 0;
            Random random = new Random();
            float x = random.nextInt(2000 - 16);
            float y = random.nextInt(2000 - 16);
            xpObjects.add(new XPObject(x, y));
        }
    }

    private void checkCollisions() {
        for (int i = 0; i < xpObjects.size; i++) {
            XPObject xp = xpObjects.get(i);
            if (xp.getBounds().overlaps(player.getBounds())) {
                xpObjects.removeIndex(i);
                player.addXP(1);
                System.out.println("Player Level: " + player.getLevel());
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        player.dispose();
        hud.dispose();
        background.dispose();
    }
}
