package com.jeuchemin.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jeuchemin.JeuChemin;

public class DefaultScreen implements Screen {
	
	public JeuChemin game;
	public Skin skin;
	public FitViewport viewport;
	
	public DefaultScreen(JeuChemin _game) {
		game = _game;
		skin = new Skin(Gdx.files.internal("uiskin.json"));
	}
	
	Stage renewStage (Stage stage, FitViewport viewport) {
		stage.dispose();
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		return stage;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
