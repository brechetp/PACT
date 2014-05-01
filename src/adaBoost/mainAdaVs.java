package adaBoost;

public class mainAdaVs {

	
	public ClassiFinal[] main(double[][] X) 
	{
		int nbGeste = 2;
		int[] Y = { 
				0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
		/*int[] Y_test = {
					0,0,0,0,0,0,0,0,0,
					1,1,1,1,1,1,1,1,1,1,
					2,2,2,2,2,2,2,2,2,2,2,2,
					3,3,3,3,3,3,3,3,3,3,3,3
					4,4,4,4,4,4,4,4,4,4,4
						} ;*/
		
		int T=100;
		ClassiFinal[] classi = new ClassiFinal[nbGeste];
		ClassiFinauxListe tab = new ClassiFinauxListe(classi);
		
		//creation des classis
		for (int i=0;i<nbGeste;i++)
		{
			int[] Y2 = new int[Y.length];
			for(int j=0;j<Y.length;j++)
			{
				if (i==Y[j])
					Y2[j]=1;
				else Y2[j]=-1;
			}
			classi[i] =AdaBoost.adaBoost(X, Y2, T);
			classi[i].normaliser();
		}
	}
}