package structure;

public interface CarteListInterface 
{
	//Rajoute une carte à l'ensemble
	public void add(CarteInterface carte); 
	
	//Enleve une carte à l'ensemble
	public void remove(CarteInterface carte);
	
	//Vérifie si la carte est dans l'ensemble
	public boolean contains(CarteInterface carte);
	
	//Renvoi les point de l'ensemble
	public int getPoint();
}
