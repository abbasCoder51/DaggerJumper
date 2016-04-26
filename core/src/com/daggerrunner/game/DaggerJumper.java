package com.daggerrunner.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daggerrunner.game.states.GameStateManager;
import com.daggerrunner.game.states.MenuState;

public class DaggerJumper extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 400;
	public static final String TITLE = "Dagger Runner";

	private GameStateManager gsm;

	// only one of these are needs, passed to
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
