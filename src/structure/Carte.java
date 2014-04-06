package structure;

import logiqueDeJeux.EtatDuJeuInterface;

public class Carte implements CarteInterface
{
	private String labelNum;
	private String suit;
	private EtatDuJeuInterface etat;
	
	public Carte(String label, String suit, EtatDuJeuInterface etatDuJeuInterface)
	{
		this.labelNum = label;
		this.suit = suit;
		this.etat = etatDuJeuInterface;
	}
	
	public String getLabelNum() 
	{
		return labelNum;
	}

	public int getValue() 
	{
		if (labelNum.equals("10"))
			return 10;
		else if (labelNum.equals("roi"))
			return 4;
		else if (labelNum.equals("reine"))
			return 3;
		else if (labelNum.equals("as"))
			return 11;
		else if (labelNum.equals("valet")&&isAtout())
			return 20;
		else if (labelNum.equals("valet"))
			return 2;
		else if (labelNum.equals("9")&&isAtout())
			return 14;
		else return 0;
	}


	public String getSuit() 
	{
		return suit;
	}

	@Override
	public boolean isAtout() 
	{
		return etat.isAtout(this);
	}

	public int compare(CarteInterface carte) 
	{
		if (carte.isAtout()&&!this.isAtout())
			return -1;
		else if (carte.getSuit()==this.getSuit())
			return (int) Math.signum(this.getValue()-carte.getValue());
		else return 1;
	}

	@Override
	public int getRang() 
	{
		switch(labelNum)
		{
		case "7":
			return 0;
		case "8":
			return 1;
		case "9":
			return 2;
		case "10":
			return 3;
		case "valet":
			return 4;
		case "reine":
			return 5;
		case "roi":
			return 6;
		case "as":
			return 7;
		default:
			return 0;// n'est jamais attein
		}
	}


}
