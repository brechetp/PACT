package structure;

public interface CarteInterface 
{
	//Renvoi le rang de la carte
	public String getLabelNum();
	
	//Renvoi la couleur de la carte
	public String getSuit();
	
	//Renvoi la position sur la table de la carte
	public Integer[] getPos();
	
	//Renvoi true si la cartes est a l'atout
	public boolean isAtout();
	
	//Renvoi la valeur de la carte
	public int getValue();
}
