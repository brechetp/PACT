package adaBoost;

public class mainAdaVs {

	
	public ClassiFinal[] main(double[][] X) 
	{
		int nbGeste = 2;
		int[] Y = { 
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3
		};
		
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
		for (int i=1;i<4;i=i+2)
		{
			int[] Y2 = new int[Y.length];
			for(int j=0;j<Y.length;j++)
			{
				if (i==Y[j])
					Y2[j]=1;
				else Y2[j]=-1;
			}
			classi[(i-1)/2] =AdaBoost.adaBoost(X, Y2, T);
			classi[(i-1)/2].normaliser();
		}
		return classi;
	}
}