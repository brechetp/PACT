package structure;

public interface AnnonceInterface 
{
	
	//Renvoi le rang
	public int getValue();
	
	//renvoi la couleur de l'atout
	public String getAtout();
	
	public int getTeam();
	
	//Augmente la valeur de l'annonce
	public void valueUp();
	
	//Passe a la couleur suivante
	public void nextSuit();

	//Setter pour l'atribut team
	public void setTeam(int i);
	
	//Baisse la valeur de l'annonce
	public void valueDown();
	
}
