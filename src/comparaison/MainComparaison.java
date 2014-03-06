package comparaison;

public class MainComparaison {

	public static void main(String[] args) {
		
		
	
		
		int [][] grayMatrix = 
		
		int [][] tab0 =    
		int [][] tab1 =
		int [][] tab2 =
		int [][] tab3 = 
		int [][] tab4 =
		
		BaseDonneesCartes baseDonneesCartes = new BaseDonneesCartes(tab0, tab1, tab2, tab3, tab4);
		Comparaison imageTest = new Comparaison (grayMatrix);	
	    System.out.println(imageTest.getCardValue(baseDonneesCartes));
	    //for (int i=0; i<5; i++) {System.out.println(imageTest.matchTable[i]);}
	    //for (int i=0; i<5; i++) { System.out.println(imageTest.compare(baseDonneesCartes,i));}
	}

}
