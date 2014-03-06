package comparaison;

public class MainComparaison {

	public static void main(String[] args) {
		int [][] grayMatrix = new int [1920][1080];
		
		for (int k =0; k<1920; k++)
		{
			for (int j=0; j<1080; j++)
			{
				grayMatrix[k][j]=0;
			}
		}
		
		grayMatrix[22][22]=1;
		
		
		BaseDonneesCartes baseDonneesCartes = new BaseDonneesCartes(grayMatrix);
		Comparaison imageTest = new Comparaison (grayMatrix);	
		
		
	    System.out.println(imageTest.getCardValue(baseDonneesCartes));
	    //for (int i=0; i<5; i++) {System.out.println(imageTest.matchTable[i]);}
	    //for (int i=0; i<5; i++) { System.out.println(imageTest.compare(baseDonneesCartes,i));}
	}

}
