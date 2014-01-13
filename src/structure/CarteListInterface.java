package structure;

public interface CarteListInterface 
{
	//Rajoute une carte � l'ensemble
	public void add(Carte carte); 
	
	//Enleve une carte � l'ensemble
	public void remove(Carte carte);
	
	//V�rifie si la carte est dans l'ensemble
	public boolean contains(Carte carte);
	
	//Renvoi les point de l'ensemble
	public int getPoint();
}
