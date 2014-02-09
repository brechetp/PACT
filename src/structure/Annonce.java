package structure;

public class Annonce implements AnnonceInterface
{
	private String suit;
	private int value;
	private int team; 
	
	@Override
	public int getValue() 
	{
		return value;
	}

	@Override
	public String getAtout() 
	{
		return suit;
	}

	public int getTeam() {
		return team;
	}
	
}
