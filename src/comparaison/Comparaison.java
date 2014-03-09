package comparaison;

public class Comparaison {

	private int[][] doubleTab;
	private int size1;
	private int size2;
	double [] matchTable = new double [5];
	private double average;
	private double sigma;
	
	public Comparaison (int[][] grayMatrix){
		this.size1 = 635;
		this.size2 = 889;
		this.doubleTab = new int[size2][size1];
		this.doubleTab = grayMatrix;
	}
	
	
	public int getCardValue (CardDatabase baseDonneesCartes){
		
	  // Calcul de average et sigma 
		average = 0; double variance = 0;
		for (int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				average += doubleTab[i][j];
			}
		}	
		average = average/(size1*size2);	
		
		for (int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				variance += Math.pow(doubleTab[i][j]-average,2);
			}
		}
		sigma = Math.sqrt(variance/(size1*size2)); sigma=1; average = 0; // A enlever si niveaux de gris
		
	  // comparaison avec chaque carte de la base de donnŽe	
		for (int i=0 ; i < 5 ; i++)
		{
			matchTable[i] = compare(baseDonneesCartes, i);
		}
		
	  // dŽtermination du meilleur match	
	    double max = 0;
	    int imax = 0;
	    
	    for (int i=0 ; i < 5 ; i++)
	    {
			if (max<matchTable[i]) 
			{
				imax = i; max = matchTable[i];
			}
		}
		return imax;
	}
	
	public double compare(BaseDonneesCartes baseDonneesCartes, int i ){
		
		double rep;
		rep = 0;
		for (int k =0; k<size2; k++)
		{
			for (int j=0; j<size1; j++)
			{
				double pixel = (doubleTab[k][j]-average)/sigma;
				rep = rep + pixel*voisin(baseDonneesCartes,i,k,j,pixel) ;
			}
		}	
		//System.out.println(rep);
		return rep;
		
	}

	public double voisin(BaseDonneesCartes baseDonneesCartes, int i,int k, int j, double pixel){ // retourne le pixel voisin de (i,j)
		
		double distance = 0 , distanceMin = Integer.MAX_VALUE; int kmin = 0 ; int jmin=0;
		for(int n = Math.max(0, k-1); n <= Math.min(size2-1, k+1); n++){
			for(int p = Math.max(0, j-1); p <= Math.min(size1-1,  j+1); p++){
				
				distance = 0;
				
				distance = Math.abs((baseDonneesCartes.getValue(i,n,p)-baseDonneesCartes.getAverage(i))/baseDonneesCartes.getSigma(i) - pixel);
				
				if (distance < distanceMin){
					kmin = n;
					jmin =p;
				}
			}
		}
		//prendre garde ˆ rajouter kmin et jmin pour grdes images
		return (baseDonneesCartes.getValue(i,k,j)-baseDonneesCartes.getAverage(i))/baseDonneesCartes.getSigma(i) ;
	}
}


