package com.daggerrunner.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.daggerrunner.game.DaggerJumper;
import com.daggerrunner.game.sprites.Enemy;
import com.daggerrunner.game.sprites.Player;

public class PlayState extends State{
    private static final int ENEMY_SPACING = 150;
    private static final int ENEMY_COUNT = 4;
    private static final int PLAYER_GROUND = 20;

    private Player player;
    private Texture bg;
    private Array<Enemy> enemies;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        player = new Player(10, PLAYER_GROUND);
        bg = new Texture("bg.png");
        cam.setToOrtho(false, DaggerJumper.WIDTH/2, DaggerJumper.HEIGHT/2);

        enemies = new Array<Enemy>();

        for(int i = 1;i<=ENEMY_COUNT;i++)
        {
            enemies.add(new Enemy(i*(ENEMY_SPACING + Enemy.ENEMY_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched())
        {
            if((player.getPosition().y == PLAYER_GROUND) || (player.jumpCount() < 2))
            {
                player.jump();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        player.update(dt);
        cam.position.x = player.getPosition().x + 80;

        for(int i=0; i < enemies.size; i++)
        {
            Enemy enemy = enemies.get(i);
            if(cam.position.x - (cam.viewportWidth/2) > enemy.getPosEnemy().x + enemy.getEnemy().getWidth())
            {
                enemy.reposition(enemy.getPosEnemy().x + ((Enemy.ENEMY_WIDTH + ENEMY_SPACING) * ENEMY_COUNT));
            }

            if(enemy.collides(player.getBounds()))
            {
//                gsm.set(new PlayState(gsm));
            }
        }
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        for(Enemy enemy : enemies)
        {
            sb.draw(enemy.getEnemy(), enemy.getPosEnemy().x, enemy.getPosEnemy().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        player.dispose();
        for(Enemy enemy : enemies)
        {
            enemy.dispose();
        }
    }
}
