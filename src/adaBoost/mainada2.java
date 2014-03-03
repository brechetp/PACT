package adaBoost;

import java.util.ArrayList;


public class mainada2 {

	public mainada2() {
		// TODO Auto-generated constructor stub
	}

	public void main(double[][] X, double[][] X_test) 
	{
		int nbGeste = 5;
		int[] Y = { 
				0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
				1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
				3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,
				4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4
					} ;
		
		int[] Y_test = {
					0,0,0,0,0,0,0,0,0,0,
					1,1,1,1,1,1,1,1,1,1,1,1,
					2,2,2,2,2,2,2,2,2,
					3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,
					4,4,4,4,4,4,4,4,4,4,4,4
						} ;
		
		int T=200;
		ClassiFinal[] classi = new ClassiFinal[nbGeste];
		
		//creation des classi
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
		
		int[] Ysortie = new int[62];
		for(int j=0;j<Y_test.length;j++)
		{
			int indiceMax=-1;
			double resultMax =-1;
			for(int k=0;k<nbGeste;k++)
			{
				double result = classi[k].result(X_test[j]);
				if (result>resultMax)
				{
					indiceMax=k;
					resultMax=result;
				}
			}
			if (resultMax<0)
				Ysortie[j]=42;
			else
				Ysortie[j]=indiceMax;
			
		}
		
		for(int j=0;j<Y_test.length;j++)
		{
			System.out.print("Ytest ="+Y_test[j]+"   ");
			System.out.println("Ysortie ="+Ysortie[j]);
		}
	}
}
