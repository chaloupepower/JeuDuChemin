package com.jeuchemin;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.Scanner;

import com.jeuchemin.screens.GameScreen;
import com.jeuchemin.screens.MenuScreen;

public class JeuChemin extends Game {
	
	int colonne;
	int ligne;					//PARAMETRE
	int profondeur;
	int qdim;
	int d;
	int nbSymbole = 36;
	int tailleListe;
	
	ArrayList<String> ListeSymbole;
	ArrayList<String> ListeReponse;		//LISTES DE JEU
	
	int nbCoup;				//SCORES
	double score = 0;
	
	public Object Symbole1;
	public Object Symbole2;
	
	GameScreen gameScreen;
	
	@Override
	public void create () {
		setScreen(new MenuScreen(this));
	}
	
	public JeuChemin (){
		ListeSymbole = new ArrayList<String>();								//Constructeur
		ListeReponse = new ArrayList<String>();
	}
	
	public void setParam(int pColonne, int pLigne, int pProfondeur, int pQdim){
		colonne = pColonne;
		ligne = pLigne;
		profondeur = pProfondeur;
		qdim = pQdim;
		d = Math.max(Math.max(colonne, ligne), Math.max(profondeur, qdim));
	}
	
										//ACCESSEUR
	
	public int nbSymb(){
		return nbSymbole;
	}
		
										//MELANGER LA LISTE
	
	public void shuffle(ArrayList<?> pList){
		
		if(d==6){
			for(int i=0; i<36;i++){
				int hasard = (int) (Math.random() * (36 - i) );
				ListeSymbole.add(pList.get(hasard).toString());
				pList.remove(hasard);
				ListeReponse.add("_");
			}
			tailleListe = 36;
		}
		else if(d==4){
			for(int i=0; i <3;i++){
				for(int j=0;j<12;j++){
					int hasard = (int) (Math.random() * (36 - j - 12*i) );
					ListeSymbole.add(pList.get(hasard).toString());
					pList.remove(hasard);
					ListeReponse.add("_");
				}
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			}
			tailleListe = 48;
		}
		else if (d==3){
			for(int i=0; i <2;i++){
				for(int j=0;j<18;j++){
					int hasard = (int) (Math.random() * (36 - j - 18*i) );
					ListeSymbole.add(pList.get(hasard).toString());
					pList.remove(hasard);
					ListeReponse.add("_");
				}
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			ListeSymbole.add("_");ListeReponse.add("_");
			}
			tailleListe = 54;
		}
		
		
	}
	
											//POINT DE DEPART
	
	public int[] start(int pStart, ArrayList<?> pList){
		
		int[] position = null;
		if(pStart==1){
			if(d==6){
				gameScreen.displayText("Vous commencez en (1,1) avec le symbole "+ListeSymbole.get(0),7);
				ListeReponse.set(0, ListeSymbole.get(0));
				position = new int[2]; position[0] = 0; position[1] = 0;
			}
			else if(d==4){
				gameScreen.displayText("Vous commencez en (1,1,1) avec le symbole "+ListeSymbole.get(0),7);
				ListeReponse.set(0, ListeSymbole.get(0));
				position = new int[3]; position[0] = 0; position[1] = 0; position[2] = 0;
			}
			else if(d==3){
				gameScreen.displayText("Vous commencez en (1,1,1,1) avec le symbole "+ListeSymbole.get(0),7);
				ListeReponse.set(0, ListeSymbole.get(0));
				position = new int[4]; position[0] = 0; position[1] = 0; position[2] = 0; position[3] = 0;
			}
			Symbole1 = ListeSymbole.get(0);
		}
		else if(pStart==2){
			int k = 0;
			if(d==6){
				k = (int) (Math.random()*17);
				int x = k%6 + 1;
				int y = k/6 + 1;
				gameScreen.displayText("Vous commencez en ("+x+","+y+") avec le symbole "+ListeSymbole.get(k),7);
				ListeReponse.set(x-1 + 6*(y-1), ListeSymbole.get(k));
				position = new int[2]; position[0] = x-1; position[1] = y-1;
			}
			else if(d==4){
				int hasard = (int) (Math.random()*3);
				if(hasard==0 || hasard==1){
					k = (int) (Math.random()*12);
					int x = k%4 + 1;
					int y = k/4 + 1;
					gameScreen.displayText("Vous commencez en ("+x+","+y+",1) avec le symbole "+ListeSymbole.get(k),7);
					ListeReponse.set(x-1 + 4*(y-1) , ListeSymbole.get(k));
					position = new int[3]; position[0] = x-1; position[1] = y-1; position[2] = 0;
				}
				else{
					k = (int) (Math.random()*6) + 16;
					int x = (k%16)%4 + 1;
					int y = (k%16)/4 + 1;
					int z = k/16 + 1;
					gameScreen.displayText("Vous commencez en ("+x+","+y+","+z+") avec le symbole "+ListeSymbole.get(k),7);
					ListeReponse.set(x-1 + 4*(y-1) + 4*(z-1), ListeSymbole.get(k));
					position = new int[3]; position[0] = x-1; position[1] = y-1; position[2] = z-1;
				}
				
			
			}
			else if (d==3){
				k = (int) (Math.random()*18);
				int x = (k%9)%3 + 1;
				int y = (k%9)/3 + 1;
				int z = k/9 + 1;
				gameScreen.displayText("Vous commencez en ("+x+","+y+","+z+",1) avec le symbole "+ListeSymbole.get(k),7);
				ListeReponse.set(x-1 + 3*(y-1) + 9*(z-1), ListeSymbole.get(k));
				position = new int[4]; position[0] = x-1; position[1] = y-1; position[2] = z-1; position[3] = 0;
			}
			Symbole1 = ListeSymbole.get(k);
		}
		nbSymbole--;
		pList.remove(Symbole1);
		return position;
	}
	
	
	
	
	
											//CHOISIR UN SYMBOLE
	
	public String choisirSymbole(){
		gameScreen.displayText("Sur quel symbole souhaitez vous allez ?", 2);
		String symbole = new String();
		symbole=gameScreen.getInput();
		return symbole;
	}
	
											//TROUVER UN SYMBOLE
	
	public int[] chercheSymbole(String obj, ArrayList<?> pList){
		
		int k = ListeSymbole.indexOf(obj);
		int[] position = null;
		if(d==6){
			position = new int[2];
			int x = k%6 +1;
			int y =(int) k/6 + 1;
			gameScreen.displayText("Les coordonnees de "+obj+" sont ("+x+","+y+")",3);
			ListeReponse.set(x-1 + (y-1)*6,obj);
			position[0] = x - 1; position[1] = y - 1;
			nbCoup++;
		}
		else if(d==4){
			position = new int[3];
			int x =(k%16)%4 + 1;
			int y =(int) (k%16)/4 + 1;
			int z =(int) k/16 + 1;
			gameScreen.displayText("Les coordonnees de "+obj+" sont ("+x+","+y+","+z+")",3);
			ListeReponse.set(x-1 + (y-1)*4 + (z-1)*4, obj);
			position[0] = x -1 ; position[1] = y - 1; position[2] = z -1;
			nbCoup++;
		}
		else{
			position = new int[4];
			int x =((k%27)%9)%3 + 1;
			int y =(int) ((k%27)%9)/3 + 1;
			int z =(int) (k%27)/9 + 1;
			int q =(int) k/27 + 1;
			gameScreen.displayText("Les coordonnees de "+obj+" sont ("+x+","+y+","+z+","+q+")",3);
			ListeReponse.set(x-1 + (y-1)*3 + (z-1)*9 + (q-1)*27, obj);
			position[0] = x -1 ; position[1] = y - 1; position[2] = z -1; position [3] = q - 1;
			nbCoup++;
		}
		if(pList.contains(obj)){
			nbSymbole--;
			pList.remove(obj);
		}
		return position;
	}
								
												//CHEMIN
	
	
	public void chemin(int x1, int y1, int x2, int y2){
		int mouvement = Math.abs(x1 - x2) + Math.abs(y1 - y2) - 1;
		String path = "";
		if(mouvement==0) {
			gameScreen.displayText("Les symboles "+Symbole1+" et "+Symbole2+" sont contigus !",7);
		}
		else {
			gameScreen.displayText("Un chemin reliant les symboles "+Symbole1+" et "+Symbole2+" est :",7);
		}
		while (mouvement > 0){
			int hasard =(int) (Math.random()*2);
			if(hasard == 0){
				if(x1 < x2){
					x1 = x1 +1;
					path = path + ListeSymbole.get(x1 + 6*y1) + " ";
					mouvement--;
				}
				else if (x1 > x2){
					x1 = x1 -1;
					path = path + ListeSymbole.get(x1 + 6*y1) + " ";
					mouvement--;
				}
				else{}
			}
			else{
				if(y1 < y2){
					y1 = y1 +1;
					path = path + ListeSymbole.get(x1 + 6*y1) + " ";
					mouvement--;
				}
				else if (y1 > y2){
					y1 = y1 -1;
					path = path + ListeSymbole.get(x1 + 6*y1) + " ";
					mouvement--;
				}
				else{}
			}
			Symbole1 = Symbole2;
		}
		gameScreen.displayText(path, 6);
	}
	
	public void chemin(int x1, int y1, int z1, int x2, int y2, int z2){
		int mouvement = Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2) - 1;
		String path = "";
		if(mouvement==0) {
			gameScreen.displayText("Les symboles "+Symbole1+" et "+Symbole2+" sont contigus !",7);
		}
		else {
			gameScreen.displayText("Un chemin reliant les symboles "+Symbole1+" et "+Symbole2+" est :",7);
		}
		while (mouvement > 0){
			int hasard =(int) (Math.random()*3);
			if(hasard == 0){
				if(x1 < x2){
					x1 = x1 +1;
					path = path + ListeSymbole.get(x1 + 4*y1 + 16*z1) + " ";
					mouvement--;
				}
				else if (x1 > x2){
					x1 = x1 -1;
					path = path + ListeSymbole.get(x1 + 4*y1 + 16*z1) + " ";
					mouvement--;
				}
				else{}
			}
			else if(hasard==1){
				if(y1 < y2){
					y1 = y1 +1;
					path = path + ListeSymbole.get(x1 + 4*y1 + 16*z1) + " ";
					mouvement--;
					
				}
				else if (y1 > y2){
					y1 = y1 -1;
					path = path + ListeSymbole.get(x1 + 4*y1 + 16*z1) + " ";
					mouvement--;
				}
				else{}
			}
			else{
				if(z1 < z2){
					z1 = z1 +1;
					path = path + ListeSymbole.get(x1 + 4*y1 + 16*z1) + " ";
					mouvement--;
					
				}
				else if (z1 > z2){
					z1 = z1 -1;
					path = path + ListeSymbole.get(x1 + 4*y1 + 16*z1) + " ";
					mouvement--;
				}
				else{}
			}
			Symbole1 = Symbole2;
		}
		gameScreen.displayText(path, 6);
	}
	
	public void chemin(int x1, int y1, int z1, int w1, int x2, int y2, int z2, int w2){
		int mouvement = Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2) + Math.abs(w1 - w2) - 1;
		String path = "";
		if(mouvement==0) {
			gameScreen.displayText("Les symboles "+Symbole1+" et "+Symbole2+" sont contigus !",7);
		}
		else {
			gameScreen.displayText("Un chemin reliant les symboles "+Symbole1+" et "+Symbole2+" est :",7);
		}
		while (mouvement > 0){
			int hasard =(int) (Math.random()*4);
			if(hasard == 0){
				if(x1 < x2){
					x1 = x1 +1;
					path = path + ListeSymbole.get(x1 + 3*y1 + 9*z1 + 27*w1) + " ";
					mouvement--;
				}
				else if (x1 > x2){
					x1 = x1 -1;
					path = path + ListeSymbole.get(x1 + 3*y1 + 9*z1 + 27*w1) + " ";
					mouvement--;
				}
				else{}
			}
			else if(hasard==1){
				if(y1 < y2){
					y1 = y1 +1;
					path = path + ListeSymbole.get(x1 + 3*y1 + 9*z1 + 27*w1) + " ";
					mouvement--;
					
				}
				else if (y1 > y2){
					y1 = y1 -1;
					path = path + ListeSymbole.get(x1 + 3*y1 + 9*z1 + 27*w1) + " ";
					mouvement--;
				}
				else{}
			}
			else if (hasard == 2){
				if(z1 < z2){
					z1 = z1 +1;
					path = path + ListeSymbole.get(x1 + 3*y1 + 9*z1 + 27*w1) + " ";
					mouvement--;
					
				}
				else if (z1 > z2){
					z1 = z1 -1;
					path = path + ListeSymbole.get(x1 + 3*y1 + 9*z1 + 27*w1) + " ";
					mouvement--;
				}
				else{}
			}
			else{
				if(w1 < w2){
					w1 = w1 +1;
					path = path + ListeSymbole.get(x1 + 3*y1 + 9*z1 + 27*w1) + " ";
					mouvement--;
					
				}
				else if (w1 > w2){
					w1 = w1 -1;
					path = path + ListeSymbole.get(x1 + 3*y1 + 9*z1 + 27*w1) + " ";
					mouvement--;
				}
				else{}
			}
			Symbole1 = Symbole2;
		}
		gameScreen.displayText(path, 6);
	}

										//DEDUCTION DE SYMBOLE

	public void deduire(ArrayList<?> pList){
		
		if(nbSymbole > 0){
			gameScreen.displayText("Pensez vous avoir trouve la position d'un symbole ? O/N",1);
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			char reponse = str.charAt(0);
			while(reponse =='O'){
				String symbole = new String();
				int colonne;
				int ligne;
				int profondeur;
				int qDim;
				gameScreen.displayText("Quel symbole pensez vous avoir trouve ?",6);
				sc = new Scanner(System.in);
				symbole=sc.next();
				gameScreen.displayText("Veuillez entrer le numero de sa colonne :",6);
				sc = new Scanner(System.in);
				colonne = sc.nextInt();
				gameScreen.displayText("Veuillez entrer le numero de sa ligne :",5);
				sc = new Scanner(System.in);
				ligne = sc.nextInt();
				
				if(pList.contains(symbole)){
					nbSymbole--;
					pList.remove(symbole);
				}
				else{
					nbCoup++;
				}
				
				
				if(d==6){
					ListeReponse.set(colonne-1 + (ligne-1)*6, symbole);
				}
				else{
					gameScreen.displayText("Veuillez entrer le numero de sa troisième dimension :",4);
					sc = new Scanner(System.in);
					profondeur = sc.nextInt();
					
					if(d==4){
						ListeReponse.set(colonne-1 + (ligne-1)*4 + (profondeur-1)*4, symbole);
					}
					else{
						gameScreen.displayText("Veuillez entrer le numero de sa quatrième dimension :",3);
						sc = new Scanner(System.in);
						qDim = sc.nextInt();
						ListeReponse.set(colonne-1 + (ligne-1)*3 + (profondeur-1)*9 + (qDim-1)*27, symbole);
					}
				}
				
				if(nbSymbole > 0){
					gameScreen.displayText("Pensez vous avoir trouve la position d'un autre symbole ? O/N",5);
					sc = new Scanner(System.in);
					str = sc.nextLine();
					reponse = str.charAt(0);
				}
				else{
					reponse = 'N';
				}
			}
		}
	}
	
									//VICTOIRE
	
	public void victoire(float pTemps){
		int victoire=0;
		for(int i = 0; i < tailleListe; i++){
			if(ListeReponse.get(i).equals(ListeSymbole.get(i))){
				victoire++;
			}
			else{
				gameScreen.displayText("Desole vous avez fait une erreur !",4);
			}
		}
		if(victoire==tailleListe){
			if(d==6){
				score = Math.exp((8-nbCoup)/8)*Math.exp((210-pTemps)/420)*10000;
			}
			else if(d==4){
				score = Math.exp((10-nbCoup)/9)*Math.exp((240-pTemps)/480)*10000;
			}
			else if(d==3){
				score = Math.exp((12-nbCoup)/10)*Math.exp((270-pTemps)/540)*10000;
			}
			score = (int) score;
			int minutes =(int) pTemps/60;
			int secondes =(int) pTemps%60;
			
			gameScreen.displayText("		 Félicitations, vous avez gagné !",5);
			gameScreen.displayText("			Voici vos statistiques :",5);
			gameScreen.displayText("Temps : "+minutes+" minutes et "+secondes+" secondes",5);
			gameScreen.displayText("Nombre de coup joué : "+nbCoup,5);
			gameScreen.displayText("Vous obtenez un score de : "+score+"/10000",5);
		}
		else{
			for(int i = 0; i < tailleListe; i++){
				gameScreen.displayText(ListeReponse.get(i)+" et "+ListeSymbole.get(i),4);
			}
		}
	}
	
	public void ListInitial(ArrayList<String> pList){
		pList.add("a");pList.add("b");pList.add("c");pList.add("d");pList.add("e");pList.add("f");
		pList.add("g");pList.add("h");pList.add("i");pList.add("j");pList.add("k");pList.add("l");
		pList.add("m");pList.add("n");pList.add("o");pList.add("p");pList.add("q");pList.add("r");		//Liste de
		pList.add("s");pList.add("t");pList.add("u");pList.add("v");pList.add("w");pList.add("x");		//tout les symboles
		pList.add("y");pList.add("z");pList.add("0");pList.add("1");pList.add("2");pList.add("3");pList.add("4");
		pList.add("5");pList.add("6");pList.add("7");pList.add("8");pList.add("9");	
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
}