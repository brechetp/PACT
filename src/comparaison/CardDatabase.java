package comparaison;

import camera.Card;
import camera.Image;


public class CardDatabase {
	
	private Card[] tab;
	private double[][] averageTab;
	private double[][] sigmaTab;
	private int size;
	
	public CardDatabase (int debut, int fin, String source){
		
		size = fin-debut+1;
		averageTab = new double[size][3];
		sigmaTab = new double[size][3];
		
		tab = new Card[size];
		
		for(int k = 0; k<size; k++){
			
			try {
				tab[k] = new Card(source+(k+1)+".jpg");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			averageTab[k] = tab[k].getAverage();
			sigmaTab[k] = tab[k].getSigma();
		}
	}
		
	public CardDatabase (Card[] tab){
		
		size = tab.length;
		averageTab = new double[size][3];
		sigmaTab = new double[size][3];
		this.tab = new Card[size];
		for(int k = 0; k<size; k++){
			
			try {
				this.tab[k] = tab[k];
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			averageTab[k] = tab[k].getAverage();
			sigmaTab[k] = tab[k].getSigma();
		}
		
		
	}
	
	


	public Card getCard(int k){
		
		return tab[k];
	}
 
	public double[] getAverage(int i){
		return averageTab[i];
	}
	
   
	public double[] getSigma(int i){
		return sigmaTab[i];
		
	}


	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	
}