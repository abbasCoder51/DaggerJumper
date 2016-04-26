package com.daggerrunner.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daggerrunner.game.DaggerJumper;

public class MenuState extends State{

    private Texture background;
    private Texture playBtn;
    private Texture settingsBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, DaggerJumper.WIDTH / 2, DaggerJumper.HEIGHT / 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
        settingsBtn = new Texture("settingsBtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2, cam.position.y);
        sb.draw(settingsBtn, cam.position.x - settingsBtn.getWidth() / 2, cam.position.y - settingsBtn.getHeight());
//        sb.draw(settingsBtn, (DaggerJumper.WIDTH /2)- (playBtn.getWidth() / 2), (DaggerJumper.HEIGHT/2) - settingsBtn.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        settingsBtn.dispose();
    }
}
