package structure;

public class Annonce implements AnnonceInterface
{
	private String suit;
	private int value;
	private int team; 
	
	public Annonce(String suit,int value,int team)
	{
		this.suit=suit;
		this.value=value;
		this.team=team;
	}
	
	public int getValue() 
	{
		return value;
	}

	public String getAtout() 
	{
		return suit;
	}

	public int getTeam() 
	{
		return team;
	}

	public void valueUp() 
	{
		if (value==250)
			value=500;
		else if (value==150)
			value=250;
		else if (value<150)
			value=value+10;
	}

	public void nextSuit() 
	{
		if (suit=="carreau")
			suit="coeur";
		else if (suit=="coeur")
			suit="pique";
		else if (suit=="pique")
			suit="trefle";
		else if (suit=="trefle")
			suit="carreau";
	}

	@Override
	public void setTeam(int i) 
	{
		this.team=i;
	}
	
	public void valueDown() 
	{
		if (value==500)
			value=250;
		else if (value==250)
			value=160;
		else if (value<=160)
			value=value-10;
	}
}
