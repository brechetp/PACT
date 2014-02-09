package structure;

public interface CarteListInterface 
{
	//Rajoute une carte � l'ensemble
	public void ajoute(CarteInterface carte); 
	
	//V�rifie si la carte est dans l'ensemble
	public boolean contain(CarteInterface carte);
	
	//Renvoi les point de l'ensemble
	public int getPoint();
}
