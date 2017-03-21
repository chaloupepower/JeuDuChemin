package com.jeuchemin.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jeuchemin.JeuChemin;

public class D3GameScreen extends GameScreen {

	public D3GameScreen(JeuChemin _game) {
		super(_game);
		Label title = new Label("3D\nChoix de la position de depart :", skin);
		title.setPosition((gameStage.getWidth() - title.getWidth()) / 2, 3 * gameStage.getHeight() / 4);
		title.setAlignment(1);
		gameStage.addActor(title);
	}
	
	//3D GAME INIT
	
	void gameStart (boolean randomize) {

		super.gameStart(randomize);
		Essai.setParam(4, 3, 3, 0);
		Essai.shuffle(ListIni);
		Essai.ListInitial(ListIni);
		
		if(randomize) {
			depart = Essai.start(2, ListIni);
		}
		else {
			depart = Essai.start(1, ListIni);
		}
		
		tempsDebut = System.currentTimeMillis();
		symbole = Essai.choisirSymbole();
	}
	
	//3D MAIN GAME
	
	void gameLoop() {
		Essai.Symbole2 = symbole;
		int[] arrive = Essai.chercheSymbole(symbole, ListIni);
		Essai.chemin(depart[0], depart[1],depart[2], arrive[0], arrive[1],arrive[2]);
		depart = arrive;
		Essai.choisirSymbole();
	}
}
