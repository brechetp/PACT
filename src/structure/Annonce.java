package structure;

public class Annonce implements AnnonceInterface
{
	private String suit;
	private int value;
	private int team; 
	
	public int getValue() 
	{
		return value;
	}

	public String getAtout() 
	{
		return suit;
	}

	public int getTeam() {
		return team;
	}
	
}
