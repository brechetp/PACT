package structure;

import java.util.Iterator;

public class Team implements TeamInterface
{
	private CarteListInterface CarteJoueur1;
	private CarteListInterface CarteJoueur2;
	private CarteListInterface CarteRemporter;
	private int dixDeDer = 0;
	
	public Team()
	{
		this.CarteJoueur1 = new CarteList();
		this.CarteJoueur2 = new CarteList();
		this.CarteRemporter = new CarteList();
	}
	
	public void ajoute(CarteInterface carte, int i) 
	{
		if (i<3)
			CarteJoueur1.ajoute(carte);
		else
			CarteJoueur2.ajoute(carte);
		
	}

	public int getPoint() 
	{
		return CarteRemporter.getPoint()+dixDeDer;
	}

	public void remporte(CarteListInterface pli) 
	{
		Iterator<CarteInterface> iterator = pli.iterator();
		while (iterator.hasNext())
		{
			CarteRemporter.ajoute(iterator.next());
		}
		
	}

	public boolean jouerParTeam(CarteInterface carte) 
	{
		return CarteJoueur1.contain(carte)||CarteJoueur2.contain(carte);
	}

	@Override
	public int jouerParJoueur(CarteInterface carte) 
	{
		if(CarteJoueur1.contain(carte))
			return 1;
		else if (CarteJoueur2.contain(carte))
			return 2;
		else return 0;
	}

	@Override
	public void dernierPli() 
	{
		this.dixDeDer=10;
		
	}

	@Override
	public CarteInterface getCartes(int numJoueur) 
	{
		if (numJoueur<3)
			return CarteJoueur1.getPlusFort();
		else return CarteJoueur2.getPlusFort();
	}
}
