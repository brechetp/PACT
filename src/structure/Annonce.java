package structure;

public class Annonce implements AnnonceInterface
{
	private String suit;
	private int value;
	private int team; 
	
	//pour les copys
	public Annonce(AnnonceInterface annonce)
	{
		this.suit=annonce.getAtout();
		this.value=annonce.getValue();
		this.team=annonce.getTeam();
	}
	
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
		System.out.println(suit=="coeur"+"boo");
		if (suit.equals("carreau"))
			suit="coeur";
		else if (suit.equals("coeur"))
			suit="pique";
		else if (suit.equals("pique"))
			suit="trefle";
		else if (suit.equals("trefle"))
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
