package comparaison;


public class BaseDonneesCartes {
	
	private int[][][] tab;
	private double[] averageTab;
	private double[] sigmaTab;
	private int size1=1080;
	private int size2=1920;
	
	public BaseDonneesCartes (int[][] grayMatrix){
		
		tab = new int[5][1920][1080];
		averageTab = new double[5];
		sigmaTab = new double[5];
		
		for (int i=0; i<5; i++){
			for (int k =0; k<size2; k++)
			{
				for (int j=0; j<size1; j++)
			    {
				tab[i][k][j]=0;
			    }
			}
		}
		
		for (int k =0; k<size2; k++)
		{
			for (int j=0; j<size1; j++)
		    {
			tab[3][k][j]=0;
		    }
		}
		
		tab[3][22][22]=1;
	
		/*for (int k=0 ; k<5 ; k++ ){
			double average = 0;
			for (int i =0; i<size2; i++)
			{
				for (int j=0; j<size1; j++)
				{
					average += tab[k][i][j];
				}
			}	
			averageTab[k] = average/(size2*size1);
		 
			double variance = 0;
			for (int i =0; i<size2; i++)
			{
				for (int j=0; j<size1; j++)
				{
					variance += Math.pow(tab[k][i][j]-average,2);
				}
			}
			sigmaTab[k] = Math.sqrt(variance/(size2*size1));
			
		}*/
		
		
	}

	public double getValue(int i, int k, int j){
	return tab[i][k][j];
	}
 
	public double getAverage(int i){
		//return averageTab[i];
		return 0;
	}
	
   
	public double getSigma(int i){
		//return sigmaTab[i];
		return 1;
	}
	
}