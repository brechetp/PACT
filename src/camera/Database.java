package camera;

import comparaison.CardDatabase;

public class Database 
{
	public static CardDatabase[][] database = new CardDatabase[2][6];
	
	public Database()
	{
		for(int i =0; i< 2; i++){
			for(int j =0; j< 5; j++){
				database[i][j] = new CardDatabase((32*i)+(4*j+1),(32*i)+(4*j+4), "data/database/database4/carte");
			}
			for(int j =5; j< 6; j++){
				database[i][j] = new CardDatabase((32*i)+(4*j+1),(32*i)+(4*j+12), "data/database/database4/carte");
			}
		}
			
	}
}