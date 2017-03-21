package com.jeuchemin.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.jeuchemin.JeuChemin;

public class D2GameScreen extends GameScreen{

	public D2GameScreen(JeuChemin _game) {
		super(_game);
		Label title = new Label("2D\nChoix de la position de depart :", skin);
		title.setPosition((gameStage.getWidth() - title.getWidth()) / 2, 3 * gameStage.getHeight() / 4);
		title.setAlignment(1);
		gameStage.addActor(title);
	}
	
	//2D GAME INIT
	
	void gameStart (boolean randomize) {

		super.gameStart(randomize);
		Essai.setParam(6, 6, 0, 0);
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
	
	//2D MAIN GAME
	
	void gameLoop() {
		Essai.Symbole2 = symbole;
		int[] arrive = Essai.chercheSymbole(symbole, ListIni);
		Essai.chemin(depart[0], depart[1], arrive[0], arrive[1]);
		depart = arrive;
		//Essai.deduire(ListIni);
		Essai.choisirSymbole();
	}
}		
		
//		int[] arrive = Essai.chercheSymbole(symbole, ListIni);
//		Essai.chemin(depart[0], depart[1], arrive[0], arrive[1]);
//		depart = arrive;
	
//		Essai.deduire(ListIni);
		
//		while(Essai.nbSymb() > 0) {
//			Object symbole = Essai.choisirSymbole();
//			int[] arrive = Essai.chercheSymbole(symbole, ListIni);
//			
//			Essai.chemin(depart[0], depart[1], arrive[0], arrive[1]);
//			depart = arrive;
//		
//			Essai.deduire(ListIni);
//			}
//		
//		long tempsFin = System.currentTimeMillis();
//		float temps = (tempsFin - tempsDebut) / 1000F;
//		
//		Essai.victoire(temps);
