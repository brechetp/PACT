package iug;

public class ViewController implements ViewControllerInterface
{
	
	Fenetre fen = new Fenetre();
	
	public void effacerCartes(){
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
		
		fen.getPan().setI(k);
		fen.repaint();
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
		fen.repaint();
	}
	
	public void modeAnnonce(){
		fen.getPan().setH(1);
		fen.repaint();
	}
	
	public void modeJeu(){
		fen.getPan().setH(0);
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
		fen.repaint();
	}
	
	public void valideValeurAnnonce(){
		if(fen.getPan().getY()==1){
			fen.getPan().setY(2);
		}
		
		if(fen.getPan().getY()==2){
			fen.getPan().setY(2);
		}
	}
	
	public void effaceAnnonce(){
		fen.getPan().setY(0);
		fen.repaint();
	}

}
