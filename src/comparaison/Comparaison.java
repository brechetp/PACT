package comparaison;

public class Comparaison {

	private int[][] doubleTab;
	private int size1;
	private int size2;
	private double [] matchTable = new double [32];
	private double average;
	private double sigma;
	
	public Comparaison (int[][] grayMatrix){
		this.doubleTab = grayMatrix;
		this.size1 = 382;
		this.size2 = 273;
	}
	
	
	public int getCardValue (BaseDonneesCartes baseDonneesCartes){
		
	  // Calcul de average et sigma 
		average = 0;
		for (int i =0; i<size1; i++)
		{
			for (int j=0; j<size2; j++)
			{
				average = average + doubleTab[i][j];
			}
		}	
		average = average/(size1*size2);	
		
	  // comparaison avec chaque carte de la base de donnŽe	
		for (int i=0 ; i < 32 ; i++)
		{
			matchTable[i] = compare(baseDonneesCartes, i); 	
		}
		
	  // dŽtermination du meilleur match	
	    double max = 0;
	    int imax = 0;
	    
	    for (int i=0 ; i < 32 ; i++)
	    {
			if (max>matchTable[i]) 
			{
				imax = i; max = matchTable[i];
			}
		}
		return imax;
	}
	
	public double compare(BaseDonneesCartes baseDonneesCartes, int i ){
		
		return 0;
	}
	
}


