
package hu.inf.unideb.BugNest.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class XPObject {
    private Texture texture;
    private Rectangle bounds;

    public XPObject(float x, float y) {
        texture = new Texture("xp.png");
        bounds = new Rectangle(x, y, 32, 32);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
