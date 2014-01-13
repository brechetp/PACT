package structure;

public interface CarteListInterface 
{
	//Rajoute une carte à l'ensemble
	public void add(Carte carte); 
	
	//Enleve une carte à l'ensemble
	public void remove(Carte carte);
	
	//Vérifie si la carte est dans l'ensemble
	public boolean contains(Carte carte);
	
	//Renvoi les point de l'ensemble
	public int getPoint();
}
