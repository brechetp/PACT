package comparaison;

public class Symbol {
	
	private int[][] doubleTab; // initialisée avec une image à une composante connexe
	private int size1;
	private int size2;
	private int perimeter;
	private double[] signature;
	private double average;
	private double sigma;
	double [] matchTable = new double [2];
	double [] translaTable = new double [perimeter];
	
	public Symbol (int[][] binaryMatrix){
		this.size1 = 185;
		this.size2 = 185;
		this.perimeter = 1000;
		
		this.doubleTab = new int[size2][size1];
		this.doubleTab = binaryMatrix;
		
		/*this.flagTab = new int[size2][size1];
		for (int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				flagTab[i][j]=0;
			}
		}*/
		
		this.signature = new double[perimeter];
		for (int i =0; i<perimeter; i++)
		{
				signature[i]=0;
		} 
		
	}
	
	public double[] getSignature(){
		
		//détermination du barycentre
		int xb = 0;
		int yb =0;
		for (int i =0; i<size2; i++)
		{
			for (int j=0; j<size1; j++)
			{
				xb += xb+j*doubleTab[i][j];
				yb += yb+i*doubleTab[i][j];
			}
		}
		xb=xb/perimeter;
		yb=yb/perimeter;
		
		
		//remplissage de la table
		for (int s=0 ; s<perimeter ; s++){
			int t =0;
			if (doubleTab[(int) Math.floor(t*Math.cos(s))+xb][(int) Math.floor(t*Math.sin(s))+yb]==0 ){
				signature[s]=Math.sqrt( Math.pow(t*Math.cos(s),2) + Math.pow(t*Math.sin(s),2));
			} else {
				t++;
			}
		}
				
		return signature;
	}
	
	
	
	public int getCardValue (CornerDatabase baseDonneesCoin){
			getSignature();
		
		  // Calcul de average et sigma 
			average = 0; double variance = 0;
			for (int i =0; i<perimeter; i++){	
					average += signature[i];
			}	
			average = average/(perimeter);	
			
			for (int i =0; i<perimeter; i++){
					variance += Math.pow(signature[i]-average,2);
				}
			sigma = Math.sqrt(variance/(perimeter)); //sigma=1; average = 0;
			
		  // comparaison avec chaque carte de la base de donnée	
			for (int i=0 ; i < 2 ; i++)
			{
				matchTable[i] = compare(baseDonneesCoin, i);
			}
			
		  // détermination du meilleur match	
		    double max = 0;
		    int imax = 0;
		    
		    for (int i=0 ; i < 2 ; i++)
		    {
				if (max<matchTable[i]) 
				{
					imax = i; max = matchTable[i];
				}
			}
			return imax;
		}
		
		public double compare(CornerDatabase baseDonnesCoin, int i ){
			
			double rep;
			for (int j=0 ; j<perimeter ; i++){
				rep = 0;
				for (int k =0; k<perimeter; k++){
					double pixel = (signature[k+j]-average)/sigma;
					rep = rep + pixel*(baseDonnesCoin.getSign(i,k)-baseDonnesCoin.getSignAverage(i))/baseDonnesCoin.getSignSigma(i) ;
					}
				translaTable[j] = rep;
			}
			// détermination de la translation la plus proche
			rep = 0;
			int imax = 0;
		    for (int k=0 ; k < perimeter ; k++)
		    {
				if (rep<translaTable[k]) 
				{
					imax = i; rep = translaTable[k];
				}
			}
			return rep;
		}

	
	
}

