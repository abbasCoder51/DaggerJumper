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
    public Boolean directionChanged;
    public Boolean animationActive;

    public Player (int x, int y)
    {
        // TODO: Rotate player depending on direction of character

        // return direction
        direction = "right";
        animationActive = false;
        directionChanged = true;
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0 ,0);
        animationTexture = new Texture("playerAnimationRight.png");
        playerAnimation = new Animation(new TextureRegion(animationTexture), 4, 0.5f);
        bounds = new Rectangle(x, y, animationTexture.getWidth() / 4, animationTexture.getHeight());

    }

    public void update(float dt)
    {

        // Direction of character - right
        if(returnDirection().equals("right") && returnDirectionChanged()){
            animationTexture = new Texture("playerAnimationRight.png");
            playerAnimation = new Animation(new TextureRegion(animationTexture), 4, 0.5f);
            directionChanged(false);
        }

        // Direction of character - left
        if(returnDirection().equals("left") && returnDirectionChanged()){
            animationTexture = new Texture("playerAnimationLeft.png");
            playerAnimation = new Animation(new TextureRegion(animationTexture), 4, 0.5f);
            directionChanged(false);
        }

        if(position.y > PLAYER_GROUND)
        {
            velocity.add(0, GRAVITY, 0);
        }

        if(position.y < PLAYER_GROUND)
        {
            position.y = PLAYER_GROUND;
            jumpCount = 0;
        }

        if(movePlayer || (position.y > PLAYER_GROUND)) {
            if(returnDirection().equals("right"))
            {
                position.add(MOVEMENT * dt, velocity.y, 0);
            }
            else if(returnDirection().equals("left"))
            {
                position.sub(MOVEMENT * dt, -velocity.y, 0);
            }
        }

        if(animationActive){
            playerAnimation.update(dt);
        }

//        velocity.scl(dt);
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
        velocity.y = 150;
        jumpCount++;
    }

    public void move(boolean move, int x, String d, Boolean a){
        movePlayer = move;
        movePlayerX = x;
        direction = d;
        animationActive = a;
    }
    public String returnDirection(){
        return direction;
    }

    public void directionChanged(Boolean direction){
        directionChanged = direction;
    }

    public Boolean returnDirectionChanged(){
        return directionChanged;
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
