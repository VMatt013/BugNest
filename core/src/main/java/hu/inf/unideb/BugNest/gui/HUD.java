package hu.inf.unideb.BugNest.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import hu.inf.unideb.BugNest.entities.Player;
import com.badlogic.gdx.graphics.Color;

public class HUD {
    private Player player;
    private BitmapFont font;

    public HUD(Player player) {
        this.player = player;
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    public void render(SpriteBatch batch) {
        font.draw(batch, "Level: " + player.getLevel(), 10, Gdx.graphics.getHeight() - 525);
    }

    public void dispose() {
        font.dispose();
    }
}
