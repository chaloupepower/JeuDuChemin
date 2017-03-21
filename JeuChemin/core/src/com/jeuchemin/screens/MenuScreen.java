package com.jeuchemin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jeuchemin.JeuChemin;
import com.jeuchemin.utils.Constants;

public class MenuScreen extends DefaultScreen{
	
	Stage menuStage;


	public MenuScreen(JeuChemin _game) {
		super(_game);
		viewport = new FitViewport(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		menuStage = new Stage(viewport);
		Gdx.input.setInputProcessor(menuStage);
		prepareMainMenu();
	}
	
	//MAIN MENU
	
	void prepareMainMenu() {
		
		menuStage = renewStage(menuStage, viewport);
		
	    TextButton startBtn = new TextButton("NOUVELLE PARTIE", skin);
	    startBtn.setWidth(250f);
	    startBtn.setHeight(30f);
	    startBtn.setPosition((menuStage.getWidth() - startBtn.getWidth()) / 2, 4 * menuStage.getHeight() / 6);
	    startBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	            prepareLevelMenu();
	        }
	    });
	    
	    TextButton rulesBtn = new TextButton("REGLES", skin);
	    rulesBtn.setWidth(250f);
	    rulesBtn.setHeight(30f);
	    rulesBtn.setPosition((menuStage.getWidth() - rulesBtn.getWidth()) / 2, 3 * menuStage.getHeight() / 6);
	    rulesBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	            Gdx.net.openURI("http://www-cours.tem-tsp.eu/~simatic/Enseignement/Pro3600/JeuDuChemin/reglesJeuChemin.pdf");
	        }
	    });
	    
	    TextButton exitBtn = new TextButton("QUITTER", skin);
	    exitBtn.setWidth(250f);
	    exitBtn.setHeight(30f);
	    exitBtn.setPosition((menuStage.getWidth() - exitBtn.getWidth()) / 2, 2 * menuStage.getHeight() / 6);
	    exitBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	            dispose();
	            Gdx.app.exit();
	        }
	    });
	 
	    menuStage.addActor(startBtn);
	    menuStage.addActor(exitBtn);
	    menuStage.addActor(rulesBtn);
	    
	}
	
	//LEVEL MENU
	
	void prepareLevelMenu()

	{
	    menuStage = renewStage(menuStage, viewport);
	    
	    TextButton easyBtn = new TextButton("Debutant : 2D", skin);
	    easyBtn.setWidth(250f);
	    easyBtn.setHeight(30f);
	    easyBtn.setPosition((menuStage.getWidth() - easyBtn.getWidth()) / 2, 3 * menuStage.getHeight() / 4);
	    easyBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	            game.setScreen(new D2GameScreen(game));
	        }
	        
	    });
	    
	    TextButton mediumBtn = new TextButton("Confirme : 3D", skin);
	    mediumBtn.setWidth(250f);
	    mediumBtn.setHeight(30f);
	    mediumBtn.setPosition((menuStage.getWidth() - mediumBtn.getWidth()) / 2, 2 * menuStage.getHeight() / 4);
	    mediumBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	            game.setScreen(new D3GameScreen(game));
	        }
	    });
	    
	    TextButton hardBtn = new TextButton("Expert : 4D", skin);
	    hardBtn.setWidth(250f);
	    hardBtn.setHeight(30f);
	    hardBtn.setPosition((menuStage.getWidth() - hardBtn.getWidth()) / 2, menuStage.getHeight() / 4);
	    hardBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	            game.setScreen(new D4GameScreen(game));
	        }
	    });
	 
	    menuStage.addActor(easyBtn);
	    menuStage.addActor(mediumBtn);
	    menuStage.addActor(hardBtn);
	    
	}
	
	
	@Override
	public void render(float delta) {
		 Gdx.gl.glClearColor(0, 0, 0, 1);
		 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 menuStage.act(delta);
		 menuStage.draw();
	}
	
	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
		menuStage.dispose();
		super.dispose();
	}
}
