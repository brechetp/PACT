package structure;

public interface CarteInterface 
{
	//Renvoi le rang de la carte
	public String getLabelNum();
	
	//Renvoi la couleur de la carte
	public String getSuit();
	
	//Renvoi true si la cartes est a l'atout
	public boolean isAtout();
	
	//Renvoi la valeur de la carte
	public int getValue();

	//Compare les deux CarteInterface. Renvoi -1 si "carte" est plus fort
	public int compare(CarteInterface carte);

	//donne le rang de la carte en fonction de sa valeur
	public int getRang();
}
