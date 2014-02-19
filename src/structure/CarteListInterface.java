package structure;

import java.util.Iterator;

public interface CarteListInterface 
{
	//Rajoute une carte à l'ensemble
	public void ajoute(CarteInterface carte); 
	
	//Vérifie si la carte est dans l'ensemble
	public boolean contain(CarteInterface carte);
	
	//Renvoi les point de l'ensemble
	public int getPoint();
	
	//Renvoi le nombre de carte dans l'ensemble
	public int size();
	
	//Renvoi la carte maitresse
	public CarteInterface getPlusFort();
	
	//Renvoi la couleur de la premiere carte poser
	public String getFirstCardSuit();

	public Iterator<CarteInterface> iterator();
}
