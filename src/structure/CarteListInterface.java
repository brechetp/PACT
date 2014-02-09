package structure;

public interface CarteListInterface 
{
	//Rajoute une carte à l'ensemble
	public void ajoute(CarteInterface carte); 
	
	//Vérifie si la carte est dans l'ensemble
	public boolean contain(CarteInterface carte);
	
	//Renvoi les point de l'ensemble
	public int getPoint();
}
