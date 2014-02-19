package structure;

import java.util.Iterator;

public class Team implements TeamInterface
{
	private CarteListInterface CarteJoueur1;
	private CarteListInterface CarteJoueur2;
	private CarteListInterface CarteRemporter;
	
	public void ajoute(CarteInterface carte, int i) 
	{
		if (i<3)
			CarteJoueur1.ajoute(carte);
		else
			CarteJoueur2.ajoute(carte);
		
	}

	public int getPoint() 
	{
		return CarteRemporter.getPoint();
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
}
