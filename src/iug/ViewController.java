package iug;

public class ViewController implements ViewControllerInterface
{

	Fenetre fen = new Fenetre();
	
	public ViewController(){
		for (int intro = 0 ; intro < 75 ; intro=intro+2){
			fen.getPan().setModeActuel(1548);
			fen.getPan().setIntro(intro);
			fen.repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fen.getPan().repaint();
	}

	public void effacerCartes(){
		fen.getPan().setYCarte(0);
		fen.getPan().setI(0);
		fen.repaint();
	}

	public void afficherCarte(String s){
		int k = 0;
		if (s.equals("7trefle")){
			k = 1;
		}
		if (s.equals("8trefle")){
			k = 2;
		}
		if (s.equals("9trefle")){
			k = 3;
		}
		if (s.equals("10trefle")){
			k = 4;
		}
		if (s.equals("valettrefle")){
			k = 5;
		}
		if (s.equals("reinetrefle")){
			k = 6;
		}
		if (s.equals("roitrefle")){
			k = 7;
		}
		if (s.equals("astrefle")){
			k = 8;
		}
		if (s.equals("7pique")){
			k = 9;
		}
		if (s.equals("8pique")){
			k = 10;
		}
		if (s.equals("9pique")){
			k = 11;
		}
		if (s.equals("10pique")){
			k = 12;
		}
		if (s.equals("valetpique")){
			k = 13;
		}
		if (s.equals("reinepique")){
			k = 14;
		}
		if (s.equals("roipique")){
			k = 15;
		}
		if (s.equals("aspique")){
			k = 16;
		}
		if (s.equals("7coeur")){
			k = 17;
		}
		if (s.equals("8coeur")){
			k = 18;
		}
		if (s.equals("9coeur")){
			k = 19;
		}
		if (s.equals("10coeur")){
			k = 20;
		}
		if (s.equals("valetcoeur")){
			k = 21;
		}
		if (s.equals("reinecoeur")){
			k = 22;
		}
		if (s.equals("roicoeur")){
			k = 23;
		}
		if (s.equals("ascoeur")){
			k = 24;
		}
		if (s.equals("7carreau")){
			k = 25;
		}
		if (s.equals("8carreau")){
			k = 26;
		}
		if (s.equals("9carreau")){
			k = 27;
		}
		if (s.equals("10carreau")){
			k = 28;
		}
		if (s.equals("valetcarreau")){
			k = 29;
		}
		if (s.equals("reinecarreau")){
			k = 30;
		}
		if (s.equals("roicarreau")){
			k = 31;
		}
		if (s.equals("ascarreau")){
			k = 32;
		}
		for (int yCarte = -fen.getPan().getCarte1().getImage().getHeight(null) ; yCarte<fen.getPan().getHeight()*0.1 ; yCarte = yCarte + 5){
			fen.getPan().setYCarte(yCarte);
			fen.getPan().setI(k);
			fen.repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void coinche(int k){
		fen.getPan().setK(k);
		fen.repaint();
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		fen.getPan().setK(0);
		fen.repaint();
	}

	public void joueurEnCours(int j){
		fen.getPan().setJ(j);
		fen.repaint();
	}

	public void partieTerminer(){
		fen.getPan().setK(666);
		fen.repaint();
	}

	public void supprimerMessage(){
		fen.getPan().setK(0);
		fen.getPan().setJ(0);
		fen.getPan().setI(0);
		fen.getPan().setY(0);
		fen.getPan().setStrings(null, null);
		fen.repaint();
	}

	public void modeAnnonce(){
		fen.getPan().setH(1);
		fen.getPan().setK(0);
		fen.repaint();
	}

	public void modeJeu(){
		fen.getPan().setH(0);
		fen.getPan().setK(11);
		fen.getPan().setJ(0);
		fen.repaint();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fen.getPan().setK(0);
		fen.repaint();
	}

	public void contreCoinche(){
		fen.getPan().setK(5);
		fen.repaint();
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		fen.getPan().setK(0);
		fen.repaint();
	}

	public void afficheAnnonce(String valeur, String couleur){
		fen.getPan().setStrings(valeur, couleur);
		fen.getPan().setY(1);
		fen.repaint();
	}

	public void actualiseAnnonce(String valeur, String couleur){
		fen.getPan().setStrings(valeur, couleur);
		fen.repaint();
	}

	public void valideValeurAnnonce(){
		if(fen.getPan().getY()==1){
			fen.getPan().setY(2);
		}

		if(fen.getPan().getY()==2){
			fen.getPan().setY(2);
		}
		fen.repaint();
	}

	public void effaceAnnonce(){
		fen.getPan().setY(0);
		fen.repaint();
	}

	public void annonceJoueurDistant(String valeur, String couleur){
		if(valeur=="" && couleur==""){
			fen.getPan().setK(7);
			fen.repaint();
			try{
				Thread.sleep(2000);
			}catch(Exception e){
				e.printStackTrace();
			}
			fen.getPan().setK(0);
			fen.repaint();
		}
		else{
			fen.getPan().setValeurCouleur(valeur, couleur);
			fen.getPan().setK(6);
			fen.repaint();
			try{
				Thread.sleep(2000);
			}catch(Exception e){
				e.printStackTrace();
			}
			fen.getPan().setK(0);
			fen.repaint();
		}

	}

	public void init(ImageMenu imageMenuGauche, ImageMenu imageMenuCentre, ImageMenu imageMenuDroite){
		fen.getPan().setImageMenuCentre(imageMenuCentre);
		fen.getPan().setImageMenuGauche(imageMenuGauche);
		fen.getPan().setImageMenuDroite(imageMenuDroite);
		fen.getPan().repaint();
	}

	public void gauche(ImageMenu imageMenuNew){
		fen.getPan().setImageMenuNew(imageMenuNew);
		fen.getPan().setPlacement(-1);
		for (int menu = 0 ; menu > (-fen.getPan().getWidth()/2)*0.7 ; menu = menu - 10){
			fen.getPan().setXMenuCentre(menu);
			fen.getPan().setYMenuCentre(menu);
			fen.getPan().setXMenuGauche(2*menu);
			fen.getPan().setXMenuDroite(menu);
			fen.getPan().setYMenuDroite(-menu);
			fen.getPan().setXMenuNew2(2*menu);
			fen.repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ImageMenu temp = fen.getPan().getImageMenuGauche();
		fen.getPan().setImageMenuGauche(fen.getPan().getImageMenuCentre());
		fen.getPan().setImageMenuCentre(fen.getPan().getImageMenuDroite());
		fen.getPan().setImageMenuDroite(fen.getPan().getImageMenuNew());
		fen.getPan().setImageMenuNew(temp);
		fen.getPan().resetCoords();
		fen.getPan().setPlacement(0);
	}

	public void droite(ImageMenu imageMenuNew){
		fen.getPan().setImageMenuNew(imageMenuNew);
		fen.getPan().setPlacement(1);
		for (int menu = 0 ; menu < (fen.getPan().getWidth()/2)*0.7 ; menu = menu + 10){
			fen.getPan().setXMenuCentre(menu);
			fen.getPan().setYMenuCentre(-menu);
			fen.getPan().setXMenuGauche(menu);
			fen.getPan().setYMenuGauche(menu);
			fen.getPan().setXMenuDroite(2*menu);
			fen.getPan().setXMenuNew1(2*menu-fen.getPan().getImageMenuNew().getImage().getWidth(null));
			fen.repaint();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ImageMenu temp = fen.getPan().getImageMenuDroite();
		fen.getPan().setImageMenuDroite(fen.getPan().getImageMenuCentre());
		fen.getPan().setImageMenuCentre(fen.getPan().getImageMenuGauche());
		fen.getPan().setImageMenuGauche(fen.getPan().getImageMenuNew());
		fen.getPan().setImageMenuNew(temp);
		fen.getPan().resetCoords();
		fen.getPan().setPlacement(0);
	}

	public void modeMenu(){
		fen.getPan().setModeActuel(0);
		fen.repaint();
	}

	public void modePartie(){
		fen.getPan().setModeActuel(1);
		fen.repaint();
	}

	public void modeOption(){
		fen.getPan().setModeActuel(2);
		fen.repaint();
	}
	
	public void modeQuitter(int sec){
		fen.getPan().setModeActuel(666);
		fen.getPan().setSec(sec);
		fen.repaint();
	}

	public void validerMenu(){
		for (int dxdy=0 ; dxdy<fen.getPan().getHeight()/2 ; dxdy=dxdy+10){
			fen.getPan().resizeImage(dxdy);
			fen.getPan().setXMenuDroite(2*dxdy);
			fen.getPan().setXMenuGauche(-2*dxdy);
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fen.repaint();
		}
		fen.getPan().resizeImage(0);
		fen.getPan().setXMenuDroite(0);
		fen.getPan().setXMenuGauche(0);
		fen.repaint();
	}

	public void option(int k){
		fen.getPan().setOptionEnCours(k);
		fen.repaint();
	}

	public void modifOption1(){

		if(fen.getPan().getOption1()==0){
			fen.getPan().setOption1(1);
			fen.repaint();
		}

		else if(fen.getPan().getOption1()==1){
			fen.getPan().setOption1(0);
			fen.repaint();
		}
	}

	public void modifOption2(){

		if(fen.getPan().getOption2()==0){
			fen.getPan().setOption2(1);
			fen.repaint();
		}

		else if(fen.getPan().getOption2()==1){
			fen.getPan().setOption2(0);
			fen.repaint();
		}
	}

	public void modifOption3(int option3){
		fen.getPan().setOption3(option3);
		fen.repaint();
	}

	public void validerOptions(){
		fen.getPan().setOptionEnCours(5);
		fen.repaint();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int optionEnCours(){
		return fen.getPan().getOptionEnCours();
	}

	public void distribution(){
		fen.getPan().setK(10);
		fen.getPan().setModeActuel(1);
		fen.repaint();
	}
	
	public void faireAnnonce(){
		fen.getPan().setK(12);
		fen.repaint();
	}
	
	public void annonceCarte(String string){
		fen.getPan().setAnnonce(string);
		fen.getPan().setK(13);
		fen.repaint();try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fen.getPan().setK(0);
		fen.repaint();
	}
	
	public void modeAPropos(){
		fen.getPan().setModeActuel(777);
		fen.repaint();
	}
	
	public void timeOutJD(int sec){
		fen.getPan().setSecJD(sec);
		fen.repaint();
	}
}
