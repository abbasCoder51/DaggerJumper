package com.daggerrunner.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.daggerrunner.game.DaggerJumper;
import java.util.Random;

public class Enemy {
    private static final int UPPER_BOUNDARY = 70;
    private static final int LOWER_BOUNDARY = 15;
    public static final int ENEMY_WIDTH = 51;

    private Texture enemy;
    private Vector2 posEnemy;
    private Rectangle boundsEnemy;
    private Random rand;

    public Enemy(float x)
    {
        enemy = new Texture("enemy.png");
        rand = new Random();

        posEnemy = new Vector2(x, rand.nextInt(UPPER_BOUNDARY - LOWER_BOUNDARY) + LOWER_BOUNDARY);
        boundsEnemy = new Rectangle(posEnemy.x, posEnemy.y, enemy.getWidth(), enemy.getHeight());

    }

    public Texture getEnemy() {
        return enemy;
    }

    public Vector2 getPosEnemy() {

        return posEnemy;
    }

    public void reposition(float x)
    {
        posEnemy.set(x, rand.nextInt(UPPER_BOUNDARY - LOWER_BOUNDARY) + LOWER_BOUNDARY);
        boundsEnemy.setPosition(posEnemy.x, posEnemy.y);
    }

    public boolean collides(Rectangle player)
    {
        return player.overlaps(boundsEnemy);
    }

    public void dispose()
    {
        enemy.dispose();
    }
}
