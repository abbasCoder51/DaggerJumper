package com.daggerrunner.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private static final int GRAVITY = -10;
    private static final int MOVEMENT = 120;
    public static final int PLAYER_GROUND = 20;
    public static final int PLAYER_JUMP_COUNT = 2;
    private Rectangle bounds;
    private Animation playerAnimation;
    private Texture animationTexture;
    private int jumpCount = 0;

    private Vector3 position;
    private Vector3 velocity;
    public boolean movePlayer = false;
    public int movePlayerX;
    public String direction;

    public Player (int x, int y)
    {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0 ,0);
        animationTexture = new Texture("playerAnimation.png");
        playerAnimation = new Animation(new TextureRegion(animationTexture), 4, 0.5f);
        bounds = new Rectangle(x, y, animationTexture.getWidth() / 4, animationTexture.getHeight());
    }

    public void update(float dt)
    {
        if(movePlayer || (position.y > PLAYER_GROUND)){
            playerAnimation.update(dt);
        }

        if(position.y > PLAYER_GROUND)
        {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);

        if(movePlayer || (position.y > PLAYER_GROUND)) {
            if(direction == "right")
            {
                position.add(MOVEMENT * dt, velocity.y, 0);
            }
            else if(direction == "left")
            {
                position.sub(MOVEMENT * dt, -velocity.y, 0);
            }
        }

        if(position.y < PLAYER_GROUND)
        {
            position.y = PLAYER_GROUND;
            jumpCount = 0;
        }

        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public TextureRegion getTexture() {
        return playerAnimation.getFrame();
    }

    public Texture getAnimation(){
        return animationTexture;
    }

    public void jump(){
        velocity.y = 250;
        jumpCount++;
    }

    public void move(boolean move, int x, String d){
        movePlayer = move;
        movePlayerX = x;
        direction = d;
    }

    public int jumpCount()
    {
        return jumpCount;
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void dispose(){
        animationTexture.dispose();
    }
}
