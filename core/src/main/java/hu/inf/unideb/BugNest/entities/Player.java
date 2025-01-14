package hu.inf.unideb.BugNest.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import hu.inf.unideb.BugNest.util.Constants;

public class Player {
    private Texture texture;
    private Rectangle bounds;
    private int level;
    private int xp;

    public Player() {
        texture = new Texture("player.png");
        bounds = new Rectangle(400, 300, 32, 32);
        level = 1;
        xp = 0;
    }

    public void update(float delta) {
        float speed = Constants.PLAYER_SPEED * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            bounds.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            bounds.y -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bounds.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bounds.x += speed;
        }

        bounds.x = Math.max(0, Math.min(bounds.x, 2000 - bounds.width));
        bounds.y = Math.max(0, Math.min(bounds.y, 2000 - bounds.height));
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void addXP(int amount) {
        xp += amount;
        if (xp >= level * Constants.XP_PER_LEVEL) {
            xp -= level * Constants.XP_PER_LEVEL;
            level++;
        }
    }

    public int getLevel() {
        return level;
    }

    public float getX() {
        return bounds.x;
    }

    public float getY() {
        return bounds.y;
    }
    public void dispose() {
        texture.dispose();
    }
}
