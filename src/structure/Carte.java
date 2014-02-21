package structure;

import logiqueDeJeux.EtatDuJeu;

public class Carte implements CarteInterface
{
	private String labelNum;
	private String suit;
	private EtatDuJeu etat;
	
	public Carte(String label, String suit, EtatDuJeu etat)
	{
		this.labelNum = label;
		this.suit = suit;
		this.etat = etat;
	}
	
	public String getLabelNum() 
	{
		return labelNum;
	}

	public int getValue() 
	{
		if (labelNum=="10")
			return 10;
		else if (labelNum=="roi")
			return 4;
		else if (labelNum=="reine")
			return 3;
		else if (labelNum=="as")
			return 11;
		else if (labelNum=="valet"&&isAtout())
			return 20;
		else if (labelNum=="valet")
			return 2;
		else if (labelNum=="9"&&isAtout())
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
	


}
