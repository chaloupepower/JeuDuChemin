package com.jeuchemin.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jeuchemin.*;
import com.jeuchemin.utils.Constants;

public class GameScreen extends DefaultScreen{
	
	Stage gameStage;
	JeuChemin Essai;
	ArrayList ListIni;
	Label textLabel;
	TextArea consoleScanner;
	String symbole;
	int[] depart = null;
	
	

	long tempsDebut;

	public GameScreen(JeuChemin _game) {
		super(_game);
		viewport = new FitViewport(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
	    gameStage = new Stage(viewport);
		Gdx.input.setInputProcessor(gameStage);
		startPositionMenu();
	}
	
	//STARTING POINT MENU
	
	void startPositionMenu () {
	    TextButton normalPosBtn = new TextButton("Position predefinie", skin);
	    normalPosBtn.setWidth(250f);
	    normalPosBtn.setHeight(30f);
	    normalPosBtn.setPosition((gameStage.getWidth() - normalPosBtn.getWidth()) / 2, 5 * gameStage.getHeight() / 8);
	    normalPosBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	gameStage = renewStage(gameStage, viewport);
	        	gameStart(false);
	        }
	    });
	    
	    TextButton randomPosBtn = new TextButton("Position aleatoire", skin);
	    randomPosBtn.setWidth(250f);
	    randomPosBtn.setHeight(30f);
	    randomPosBtn.setPosition((gameStage.getWidth() - randomPosBtn.getWidth()) / 2, 4 * gameStage.getHeight() / 8);
	    randomPosBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	gameStage = renewStage(gameStage, viewport);
	        	gameStart(true);
	        }
	    });
	    
	    TextButton backBtn = new TextButton("Retour Menu", skin);
	    backBtn.setWidth(250f);
	    backBtn.setHeight(30f);
	    backBtn.setPosition((gameStage.getWidth() - backBtn.getWidth()) / 2, 3 * gameStage.getHeight() / 8);
	    backBtn.addListener(new ClickListener() {
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	game.setScreen(new MenuScreen(game));
	        }
	    });
	    
	    gameStage.addActor(normalPosBtn);
	    gameStage.addActor(randomPosBtn);
	    gameStage.addActor(backBtn);
	}
	
	//GAME INITIALIZATION
	
	void gameStart (boolean randomize) {
		Essai = new JeuChemin();
		ListIni = new ArrayList();								
		Essai.ListInitial(ListIni);
		Essai.setGameScreen(this);
	}
	
	//MAIN GAME METHOD
	
	void gameLoop() {
	}
	
	//BASIC LABEL DISPLAY
	
	public void displayText(String textToDisplay, int position){
		textLabel = new Label(textToDisplay, skin);
		textLabel.setPosition((gameStage.getWidth() - textLabel.getWidth()) / 2, position * gameStage.getHeight() / 8);
		gameStage.addActor(textLabel);
	}
	
	//PROMPT FOR NEXT HOP
	
	public String getInput() {
		TextInputListener inputBox = new TextInputListener() {
			
			@Override
			public void input(String text) {
				setSymbole(text);
				gameStage.clear();
				gameLoop();
			}
			
			@Override
			public void canceled() {
			}
		};
		Gdx.input.getTextInput(inputBox, "Prochain symbole", "", "Entrez le symbole a atteindre");
		return symbole;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		gameStage.draw();
		gameStage.act();
		gameStage.getBatch().begin();
		gameStage.getBatch().end();
	}
	
	@Override
	public void show() {	
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		super.dispose();
		gameStage.dispose();
		Gdx.input.setInputProcessor(null);		
	}
	
	public String getSymbole() {
		return symbole;
	}

	public void setSymbole(String symbole) {
		this.symbole = symbole;
	}
}
